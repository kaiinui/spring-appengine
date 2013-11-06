package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.images.ImagesService;

/**
 * @author Marcel Overdijk
 */
public class ImagesServiceFactoryBeanTests {

    private ImagesServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new ImagesServiceFactoryBean();
    }

    @Test
    public void testTypeIsImagesService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(ImagesService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
