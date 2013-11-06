package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.oauth.OAuthService;

/**
 * @author Marcel Overdijk
 */
public class OAuthServiceFactoryBeanTests {

    private OAuthServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new OAuthServiceFactoryBean();
    }

    @Test
    public void testTypeIsOAuthService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(OAuthService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
