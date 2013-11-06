package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.backends.BackendService;

/**
 * @author Marcel Overdijk
 */
public class BackendServiceFactoryBeanTests {

    private BackendServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new BackendServiceFactoryBean();
    }

    @Test
    public void testTypeIsBackendService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(BackendService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
