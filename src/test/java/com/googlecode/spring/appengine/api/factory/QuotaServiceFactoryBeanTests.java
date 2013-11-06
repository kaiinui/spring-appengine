package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.quota.QuotaService;

/**
 * @author Marcel Overdijk
 */
public class QuotaServiceFactoryBeanTests {

    private QuotaServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new QuotaServiceFactoryBean();
    }

    @Test
    public void testTypeIsQuotaService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(QuotaService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
