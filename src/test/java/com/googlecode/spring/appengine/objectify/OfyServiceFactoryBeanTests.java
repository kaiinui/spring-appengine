package com.googlecode.spring.appengine.objectify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.domain.Car;
import com.mycompany.domain.NonEntity;
import com.mycompany.domain.Person;
import com.mycompany.other.domain.Insurance;

/**
 * @author Marcel Overdijk
 */
public class OfyServiceFactoryBeanTests {

    private OfyServiceFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new OfyServiceFactoryBean();
    }

    @Test
    public void testScansSinglePackage() throws Exception {
        factoryBean.setBasePackage("com.mycompany.domain");
        factoryBean.afterPropertiesSet();
        OfyService ofyService = factoryBean.getObject();
        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
    }

    @Test
    public void testScansMultiplePackages() throws Exception {
        factoryBean.setBasePackage("com.mycompany.domain;com.mycompany.other.domain;com.mycompany.notexisting.domain");
        factoryBean.afterPropertiesSet();
        OfyService ofyService = factoryBean.getObject();
        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
        assertNotNull(ofyService.factory().getMetadata(Insurance.class));
    }

    @Test
    public void testManualRegistration() throws Exception {
        List<Class<?>> entityClasses = new ArrayList<Class<?>>();
        entityClasses.add(Car.class);
        entityClasses.add(Person.class);
        factoryBean.setEntityClasses(entityClasses);
        factoryBean.afterPropertiesSet();
        OfyService ofyService = factoryBean.getObject();
        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
    }

    @Test
    public void testScansPackageAndManualRegistration() throws Exception {
        List<Class<?>> entityClasses = new ArrayList<Class<?>>();
        entityClasses.add(Insurance.class);
        factoryBean.setBasePackage("com.mycompany.domain");
        factoryBean.setEntityClasses(entityClasses);
        factoryBean.afterPropertiesSet();
        OfyService ofyService = factoryBean.getObject();
        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
        assertNotNull(ofyService.factory().getMetadata(Insurance.class));
    }

    @Test
    public void testDoesNotScanNonEntity() throws Exception {
        factoryBean.setBasePackage("com.mycompany.domain");
        factoryBean.afterPropertiesSet();
        OfyService ofyService = factoryBean.getObject();
        try {
            ofyService.factory().getMetadata(NonEntity.class);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("No class 'com.mycompany.domain.NonEntity' was registered", e.getMessage());
        }
    }
    
    @Test
    public void testTypeIsOfyService() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(OfyService.class));
    }

    @Test
    public void testIsSingleton() {
        assertTrue(factoryBean.isSingleton());
    }
}
