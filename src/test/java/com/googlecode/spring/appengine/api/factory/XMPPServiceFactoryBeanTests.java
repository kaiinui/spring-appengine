package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.xmpp.XMPPService;

/**
 * @author Marcel Overdijk
 */
public class XMPPServiceFactoryBeanTests {

    private XMPPServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new XMPPServiceFactoryBean();
    }

    @Test
    public void testTypeIsXMPPService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(XMPPService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
