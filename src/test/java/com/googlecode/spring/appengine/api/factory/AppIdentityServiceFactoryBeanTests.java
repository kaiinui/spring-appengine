package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.appidentity.AppIdentityService;

/**
 * @author Marcel Overdijk
 */
public class AppIdentityServiceFactoryBeanTests {

    private AppIdentityServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new AppIdentityServiceFactoryBean();
    }

    @Test
    public void testTypeIsAppIdentityService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(AppIdentityService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
