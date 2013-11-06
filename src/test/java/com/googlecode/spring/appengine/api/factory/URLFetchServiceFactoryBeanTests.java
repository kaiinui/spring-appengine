package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.urlfetch.URLFetchService;

/**
 * @author Marcel Overdijk
 */
public class URLFetchServiceFactoryBeanTests {

    private URLFetchServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new URLFetchServiceFactoryBean();
    }

    @Test
    public void testTypeIsURLFetchService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(URLFetchService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
