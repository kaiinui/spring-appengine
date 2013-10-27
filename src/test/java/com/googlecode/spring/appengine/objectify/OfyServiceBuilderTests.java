package com.googlecode.spring.appengine.objectify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.mycompany.domain.Car;
import com.mycompany.domain.NonEntity;
import com.mycompany.domain.Person;
import com.mycompany.other.domain.Insurance;

/**
 * @author Marcel Overdijk
 */
public class OfyServiceBuilderTests {

    private OfyServiceBuilder builder;

    @Before
    public void setUp() {
        builder = new OfyServiceBuilder();
    }

    @Test
    public void testScansSinglePackage() {
        OfyService ofyService = builder
                .addBasePackage("com.mycompany.domain")
                .build();
        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
    }

    @Test
    public void testScansMultiplePackages() {
        OfyService ofyService = builder
                .addBasePackage("com.mycompany.domain")
                .addBasePackage("com.mycompany.other.domain")
                .addBasePackage("com.mycompany.notexisting.domain")
                .build();
        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
        assertNotNull(ofyService.factory().getMetadata(Insurance.class));
    }

    @Test
    public void testManualRegistration() {
        OfyService ofyService = builder
                .registerEntity(Car.class)
                .registerEntity(Person.class)
                .build();
        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
    }

    @Test
    public void testScansPackageAndManualRegistration() {
        OfyService ofyService = builder
                .addBasePackage("com.mycompany.domain")
                .registerEntity(Insurance.class)
                .build();
        assertNotNull(ofyService.factory().getMetadata(Car.class));
        assertNotNull(ofyService.factory().getMetadata(Person.class));
        assertNotNull(ofyService.factory().getMetadata(Insurance.class));
    }

    @Test
    public void testDoesNotScanNonEntity() {
        OfyService ofyService = builder
                .addBasePackage("com.mycompany.domain")
                .build();
        try {
            ofyService.factory().getMetadata(NonEntity.class);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("No class 'com.mycompany.domain.NonEntity' was registered", e.getMessage());
        }
    }
}
