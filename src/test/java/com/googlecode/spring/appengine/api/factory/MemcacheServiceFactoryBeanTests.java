package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.memcache.MemcacheService;

/**
 * @author Marcel Overdijk
 */
public class MemcacheServiceFactoryBeanTests {

    private MemcacheServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new MemcacheServiceFactoryBean();
    }

    @Test
    public void testWithNamespace() throws Exception {
        factoryBean.setNamespace("myNamespace");
        factoryBean.afterPropertiesSet();
        MemcacheService memcacheService = factoryBean.getObject();
        assertEquals("myNamespace", memcacheService.getNamespace());
    }

    @Test
    public void testWithoutNamespace() throws Exception {
        factoryBean.afterPropertiesSet();
        MemcacheService memcacheService = factoryBean.getObject();
        assertNull(memcacheService.getNamespace());
    }

    @Test
    public void testTypeIsMemcacheService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(MemcacheService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
