package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.users.UserService;

/**
 * @author Marcel Overdijk
 */
public class UserServiceFactoryBeanTests {

    private UserServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new UserServiceFactoryBean();
    }

    @Test
    public void testTypeIsUserService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(UserService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
