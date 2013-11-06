package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.AsyncDatastoreService;

/**
 * @author Marcel Overdijk
 */
public class AsyncDatastoreServiceFactoryBeanTests {

    private AsyncDatastoreServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new AsyncDatastoreServiceFactoryBean();
    }

    @Test
    public void testTypeIsAsyncDatastoreService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(AsyncDatastoreService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
