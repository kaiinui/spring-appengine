package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.search.SearchService;

/**
 * @author Marcel Overdijk
 */
public class SearchServiceFactoryBeanTests {

    private SearchServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new SearchServiceFactoryBean();
    }
    
    @Test
    public void testWithNamespace() throws Exception {
        factoryBean.setNamespace("myNamespace");
        factoryBean.afterPropertiesSet();
        SearchService searchService = factoryBean.getObject();
        assertEquals("myNamespace", searchService.getNamespace());
    }

    @Test
    public void testTypeIsSearchService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(SearchService.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
