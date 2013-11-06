package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.channel.ChannelService;

/**
 * @author Marcel Overdijk
 */
public class ChannelServiceFactoryBeanTests {

    private ChannelServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new ChannelServiceFactoryBean();
    }

    @Test
    public void testTypeIsChannelService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(ChannelService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
