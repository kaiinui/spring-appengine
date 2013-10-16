package com.googlecode.spring.appengine.cache.memcache;

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
import com.googlecode.spring.appengine.cache.memcache.MemcacheCache;

/**
 * @author Marcel Overdijk
 */
public class MemcacheCacheTests {

    private static final String CACHE_NAME = "testCache";

    private static final Object key = "larry";
    private static final Object value = "page";

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
    public void testPut() {
        assertNull(cache.get(key));
        cache.put(key, value);
        assertNotNull(cache.get(key).get());
    }

    @Test
    public void testGet() {
        assertNull(cache.get(key));
        cache.put(key, value);
        assertEquals(value, cache.get(key).get());
    }

    @Test
    public void testGetExpired() throws Exception {
        assertNull(cache.get(key));
        nativeCache.put(key, value, Expiration.byDeltaSeconds(3));
        assertNotNull(cache.get(key).get());
        Thread.sleep(5 * 1000);
        assertNull(cache.get(key));
    }

    @Test
    public void testEvict() {
        assertNull(cache.get(key));
        cache.put(key, value);
        assertNotNull(cache.get(key));
        cache.evict(key);
        assertNull(cache.get(key));
    }

    @Test
    public void testClear() {
        assertNull(cache.get(key));
        cache.put(key, value);
        assertNotNull(cache.get(key));
        cache.clear();
        assertNull(cache.get(key));
    }

    @Test
    public void testGetName() {
        assertEquals(CACHE_NAME, cache.getName());
    }

    @Test
    public void testGetNativeCache() {
        assertEquals(nativeCache, cache.getNativeCache());
    }
}
