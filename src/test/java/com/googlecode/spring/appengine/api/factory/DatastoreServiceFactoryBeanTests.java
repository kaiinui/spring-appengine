package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;

/**
 * @author Marcel Overdijk
 */
public class DatastoreServiceFactoryBeanTests {

    private DatastoreServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new DatastoreServiceFactoryBean();
    }

    @Test
    public void testTypeIsDatastoreService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(DatastoreService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
