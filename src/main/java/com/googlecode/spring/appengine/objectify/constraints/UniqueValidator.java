/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.spring.appengine.objectify.constraints;

import java.lang.reflect.Field;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.impl.KeyMetadata;
import com.googlecode.objectify.impl.TypeUtils;
import com.googlecode.spring.appengine.objectify.OfyService;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private final OfyService ofyService;
    private Unique constraintAnnotation;

    @Autowired
    public UniqueValidator(OfyService ofyService) {
        Assert.notNull(ofyService, "OfyService must not be null");
        this.ofyService = ofyService;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Class<?> entityClass = value.getClass();
        KeyMetadata<?> keyMetadata = getKeyMetadata(entityClass);
        String idFieldName = keyMetadata.getIdFieldName();
        Object id = "abc"; // TODO
        Query<?> query = ofyService.load().type(entityClass);
        for (String field : constraintAnnotation.value()) {
            query.filter(field, null); // TODO 
        }
        List<?> list = query.list();
        for (Object object : list) {
            Object otherId = "def"; // TODO
            if (!id.equals(otherId)) {
                return false;
            }
        }
        return true;
    }

    private Field getField(Class<?> entityClass, String fieldName) throws NoSuchFieldException {
        return TypeUtils.getDeclaredField(entityClass, fieldName);
    }
    
    private KeyMetadata getKeyMetadata(Class<?> clazz) {
        return ofyService.factory().getMetadata(clazz).getKeyMetadata();
    }
    
//    private Class<?> getIdFieldType(Class<?> clazz) {
//        return getMetadata(clazz).getKeyMetadata().getIdFieldType();
//    }
}