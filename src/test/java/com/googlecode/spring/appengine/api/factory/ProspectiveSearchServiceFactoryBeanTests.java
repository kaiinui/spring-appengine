package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.prospectivesearch.ProspectiveSearchService;

/**
 * @author Marcel Overdijk
 */
public class ProspectiveSearchServiceFactoryBeanTests {

    private ProspectiveSearchServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new ProspectiveSearchServiceFactoryBean();
    }

    @Test
    public void testTypeIsProspectiveSearchService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(ProspectiveSearchService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
