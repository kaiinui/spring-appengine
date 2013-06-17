package com.github.marceloverdijk.springappengine.cache.memcache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cache.Cache;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * @author Marcel Overdijk
 */
public class MemcacheCacheTests {

    private static final String CACHE_NAME = "testCache";

    private static final Object larry = "larry";
    private static final Object page = "page";
    private static final Object eric = "eric";
    private static final Object schmidt = "schmidt";

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalMemcacheServiceTestConfig());

    private MemcacheService nativeCache;

    private Cache cache;

    @Before
    public void setUp() {
        helper.setUp();
        nativeCache = MemcacheServiceFactory.getMemcacheService(CACHE_NAME);
        cache = new MemcacheCache(nativeCache);
        cache.clear();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testCachePut() {
        assertNull(cache.get(larry));
        cache.put(larry, page);
        assertEquals(page, cache.get(larry).get());
    }

    @Test
    public void testExpiration() throws Exception {
        nativeCache.put(larry, page, Expiration.byDeltaSeconds(3));
        assertEquals(page, cache.get(larry).get());
        Thread.sleep(5 * 1000);
        assertNull(cache.get(larry));
    }

    @Test
    public void testCacheRemove() {
        cache.put(eric, schmidt);
        assertNotNull(cache.get(eric));
        cache.evict(eric);
        assertNull(cache.get(eric));
    }

    @Test
    public void testCacheClear() {
        cache.put(larry, page);
        assertNotNull(cache.get(larry));
        cache.put(eric, schmidt);
        assertNotNull(cache.get(eric));
        cache.clear();
        assertNull(cache.get(larry));
        assertNull(cache.get(eric));
    }

    @Test
    public void testCacheName() {
        assertEquals(CACHE_NAME, cache.getName());
    }

    @Test
    public void testNativeCache() {
        assertEquals(nativeCache, cache.getNativeCache());
    }
}
