package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.blobstore.BlobstoreService;

/**
 * @author Marcel Overdijk
 */
public class BlobstoreServiceFactoryBeanTests {

    private BlobstoreServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new BlobstoreServiceFactoryBean();
    }

    @Test
    public void testTypeIsBlobstoreService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(BlobstoreService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
