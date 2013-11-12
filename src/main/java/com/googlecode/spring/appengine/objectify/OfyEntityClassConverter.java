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
package com.googlecode.spring.appengine.objectify;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.googlecode.objectify.impl.EntityMetadata;

/**
 * {@link Converter} to convert arbitrary input into domain classes.
 * The implementation uses a {@link ConversionService} to convert the source type
 * into the domain class' id type which is then converted into a domain class
 * object by executing an Objectify query via the {@link OfyService}.
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class OfyEntityClassConverter<T extends ConversionService & ConverterRegistry> implements ConditionalGenericConverter, ApplicationContextAware {

    private final T conversionService;
    private final OfyService ofyService;

    public OfyEntityClassConverter(T conversionService, OfyService ofyService) {
        Assert.notNull(conversionService, "ConversionService must not be null");
        Assert.notNull(ofyService, "OfyService must not be null");
        this.conversionService = conversionService;
        this.ofyService = ofyService;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null || !StringUtils.hasText(source.toString())) {
            return null;
        }
        Object id = conversionService.convert(source, getIdFieldType(targetType.getType()));
        if (id instanceof Long) {
            return ofyService.ofy().load().type(targetType.getType()).id((Long) id).now();
        }
        else {
            return ofyService.ofy().load().type(targetType.getType()).id((String) id).now();
        }
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        try {
            getMetadata(targetType.getType());
            return conversionService.canConvert(sourceType.getType(), getIdFieldType(targetType.getType()));
        }
        catch (IllegalArgumentException ignore) {
            // IllegalArgumentException will be thrown if target type is not registered as entity class
            // is there a safer way to this? https://groups.google.com/forum/#!topic/objectify-appengine/5To55CZiI9w
            return false;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.conversionService.addConverter(this);
    }

    private EntityMetadata<?> getMetadata(Class<?> clazz) {
        return ofyService.factory().getMetadata(clazz);
    }

    private Class<?> getIdFieldType(Class<?> clazz) {
        return getMetadata(clazz).getKeyMetadata().getIdFieldType();
    }
}
