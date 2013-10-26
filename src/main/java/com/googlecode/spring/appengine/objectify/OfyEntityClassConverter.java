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

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
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
        this.conversionService = conversionService;
        this.ofyService = ofyService;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> convertibleTypes = new HashSet<ConvertiblePair>();
        convertibleTypes.add(new ConvertiblePair(Long.class, Object.class));
        convertibleTypes.add(new ConvertiblePair(String.class, Object.class));
        return convertibleTypes;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null || !StringUtils.hasText(source.toString())) {
            return null;
        }
        Object id = conversionService.convert(source, getIdFieldType(targetType.getType()));
        if (id instanceof Long) {
            return ofyService.ofy().load().type(targetType.getType()).id((Long) id);
        }
        else {
            return ofyService.ofy().load().type(targetType.getType()).id((String) id);
        }
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return conversionService.canConvert(sourceType.getType(), getIdFieldType(targetType.getType()));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.conversionService.addConverter(this);
    }

    private Class<?> getIdFieldType(Class<?> clazz) {
        EntityMetadata<?> metadata = ofyService.factory().getMetadata(clazz);
        return metadata.getKeyMetadata().getIdFieldType();
    }
}
