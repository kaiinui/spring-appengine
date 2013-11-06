package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.mail.MailService;

/**
 * @author Marcel Overdijk
 */
public class MailServiceFactoryBeanTests {

    private MailServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new MailServiceFactoryBean();
    }

    @Test
    public void testTypeIsMailService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(MailService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
