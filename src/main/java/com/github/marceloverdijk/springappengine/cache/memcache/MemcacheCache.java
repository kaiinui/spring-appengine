package com.github.marceloverdijk.springappengine.cache.memcache;

import org.springframework.cache.Cache;

import com.google.appengine.api.memcache.MemcacheService;

/**
 * {@link Cache} implementation backed by Google App Engine {@link MemcacheService}.
 *
 * @author Marcel Overdijk
 * @since 0.1
 */
public class MemcacheCache implements Cache {

    @Override
    public void clear() {
        // TODO
    }

    @Override
    public void evict(Object key) {
        // TODO
    }

    @Override
    public ValueWrapper get(Object key) {
        // TODO
        return null;
    }

    @Override
    public String getName() {
        // TODO
        return null;
    }

    @Override
    public Object getNativeCache() {
        // TODO
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        // TODO
    }
}
