package com.github.marceloverdijk.springappengine.samples.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.googlecode.spring.appengine.cache.memcache.MemcacheCacheManager;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public CacheManager cacheManager() {
        return new MemcacheCacheManager();
    }
}
