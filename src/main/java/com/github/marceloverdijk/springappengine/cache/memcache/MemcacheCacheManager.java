package com.github.marceloverdijk.springappengine.cache.memcache;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

public class MemcacheCacheManager extends AbstractTransactionSupportingCacheManager {

    @Override
    protected Collection<? extends Cache> loadCaches() {
        // TODO
        return null;
    }
}
