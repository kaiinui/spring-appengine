package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.capabilities.CapabilitiesService;

/**
 * @author Marcel Overdijk
 */
public class CapabilitiesServiceFactoryBeanTests {

    private CapabilitiesServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new CapabilitiesServiceFactoryBean();
    }

    @Test
    public void testTypeIsCapabilitiesService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(CapabilitiesService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
