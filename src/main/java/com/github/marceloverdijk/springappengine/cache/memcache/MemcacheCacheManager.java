package com.github.marceloverdijk.springappengine.cache.memcache;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import com.google.appengine.api.memcache.MemcacheService;

/**
 * {@link CacheManager} implementation backed by Google App Engine {@link MemcacheService}.
 * 
 * @author Marcel Overdijk
 * @since 0.1
 */
public class MemcacheCacheManager extends AbstractTransactionSupportingCacheManager {

    @Override
    protected Collection<? extends Cache> loadCaches() {
        // TODO
        return null;
    }
}
