package com.github.marceloverdijk.springappengine.cache.memcache;

import java.util.Collection;
import java.util.Collections;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 * {@link CacheManager} implementation backed by Google App Engine {@link MemcacheService}.
 * 
 * @author Marcel Overdijk
 * @since 0.1
 */
public class MemcacheCacheManager extends AbstractTransactionSupportingCacheManager {

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return Collections.emptySet();
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        if (cache == null) {
            MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService(name);
            cache = new MemcacheCache(memcacheService);
            addCache(cache);
        }
        return cache;
    }
}
