package com.googlecode.spring.appengine.objectify;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.objectify.ObjectifyFactory;
import com.mycompany.domain.Car;
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
    public void testGetObjectWithBasePackage() throws Exception {
        factoryBean.setBasePackage("com.mycompany.domain");
        factoryBean.afterPropertiesSet();

        OfyService ofyService = factoryBean.getObject();

        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
    }

    @Test
    public void testGetObjectWithBasePackages() throws Exception {
        factoryBean.setBasePackage("com.mycompany.domain;com.mycompany.other.domain;com.mycompany.notexisting.domain");
        factoryBean.afterPropertiesSet();

        OfyService ofyService = factoryBean.getObject();

        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
        assertNotNull(ofyService.factory().getMetadata(Insurance.class));
    }

    @Test
    public void testGetObjectWithClasses() throws Exception {
        List<Class<?>> entityClasses = new ArrayList<Class<?>>();
        entityClasses.add(Car.class);
        entityClasses.add(Person.class);
        entityClasses.add(Insurance.class);

        factoryBean.setEntityClasses(entityClasses);
        factoryBean.afterPropertiesSet();

        OfyService ofyService = factoryBean.getObject();

        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
        assertNotNull(ofyService.factory().getMetadata(Insurance.class));
    }

    @Test
    public void testGetObjectWithBasePackageAndClasses() throws Exception {
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

    // TODO test it does not scan non Entities
    
    @Test
    public void testGetObjectType() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(OfyService.class));
    }

    @Test
    public void testIsSingleton() {
        assertTrue(factoryBean.isSingleton());
    }
}
