package com.github.marceloverdijk.springappengine.cache.memcache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

import com.google.appengine.api.memcache.MemcacheService;

/**
 * {@link Cache} implementation backed by Google App Engine {@link MemcacheService}.
 *
 * @author Marcel Overdijk
 * @since 0.1
 */
public class MemcacheCache implements Cache {

    private final MemcacheService memcacheService;

    /**
     * Create an {@link MemcacheCache} instance.
     * @param memcacheService backing MemcacheService instance
     */
    public MemcacheCache(MemcacheService memcacheService) {
        Assert.notNull(memcacheService, "MemcacheService must not be null");
        this.memcacheService = memcacheService;
    }

    @Override
    public void put(Object key, Object value) {
        memcacheService.put(key, value);
    }

    @Override
    public ValueWrapper get(Object key) {
        Object value = memcacheService.get(key);
        return (value != null ? new SimpleValueWrapper(value) : null);
    }

    @Override
    public void evict(Object key) {
        memcacheService.delete(key);
    }

    @Override
    public void clear() {
        memcacheService.clearAll();
    }

    @Override
    public String getName() {
        return memcacheService.getNamespace();
    }

    @Override
    public MemcacheService getNativeCache() {
        return memcacheService;
    }
}
