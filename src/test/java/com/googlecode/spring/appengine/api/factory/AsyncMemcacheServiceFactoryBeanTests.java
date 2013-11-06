package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.memcache.AsyncMemcacheService;

/**
 * @author Marcel Overdijk
 */
public class AsyncMemcacheServiceFactoryBeanTests {

    private AsyncMemcacheServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new AsyncMemcacheServiceFactoryBean();
    }

    @Test
    public void testWithNamespace() throws Exception {
        factoryBean.setNamespace("myNamespace");
        factoryBean.afterPropertiesSet();
        AsyncMemcacheService asyncMemcacheService = factoryBean.getObject();
        assertEquals("myNamespace", asyncMemcacheService.getNamespace());
    }

    @Test
    public void testWithoutNamespace() throws Exception {
        factoryBean.afterPropertiesSet();
        AsyncMemcacheService asyncMemcacheService = factoryBean.getObject();
        assertNull(asyncMemcacheService.getNamespace());
    }
    
    @Test
    public void testTypeIsAsyncMemcacheService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(AsyncMemcacheService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
