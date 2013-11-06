package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.log.LogService;

/**
 * @author Marcel Overdijk
 */
public class LogServiceFactoryBeanTests {

    private LogServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new LogServiceFactoryBean();
    }

    @Test
    public void testTypeIsLogService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(LogService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
