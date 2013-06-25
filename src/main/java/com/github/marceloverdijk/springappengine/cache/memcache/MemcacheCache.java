/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.marceloverdijk.springappengine.cache.memcache;

import java.util.ArrayList;
import java.util.Collection;

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

    public MemcacheCache(MemcacheService memcacheService) {
        Assert.notNull(memcacheService, "MemcacheService must not be null");
        this.memcacheService = memcacheService;
    }

    @Override
    public void put(Object key, Object value) {
        memcacheService.put(key, toStoreValue(value));
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

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Object toStoreValue(Object value) {
        // if value to store is StreamingQueryResult then get all objects from stream and return new list (stream cannot be stored in memcache)
        try {
            Class clazz = Class.forName("com.google.appengine.datanucleus.query.StreamingQueryResult");
            if (clazz.isInstance(value)) {
                return new ArrayList((Collection) value);
            }
        }
        catch (Exception ignore) {
        }
        return value;
    }
}
