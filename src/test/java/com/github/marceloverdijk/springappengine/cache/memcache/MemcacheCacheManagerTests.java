package com.github.marceloverdijk.springappengine.cache.memcache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cache.Cache;

import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * @author Marcel Overdijk
 */
public class MemcacheCacheManagerTests {

    private static final String CACHE_NAME = "testCache";

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalMemcacheServiceTestConfig());

    private MemcacheCacheManager memcacheCacheManager;

    @Before
    public void setUp() {
        helper.setUp();
        memcacheCacheManager = new MemcacheCacheManager();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testLoadCaches() {
        assertEquals(Collections.emptySet(), memcacheCacheManager.loadCaches());
    }

    @Test
    public void testGetCache() {
        Cache cache = memcacheCacheManager.getCache(CACHE_NAME);
        assertNotNull(cache);
        assertEquals(CACHE_NAME, cache.getName());
    }
}
