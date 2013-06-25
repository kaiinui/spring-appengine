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
package com.github.marceloverdijk.springappengine.objectify.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import com.github.marceloverdijk.springappengine.objectify.annotation.EnableObjectify;

/**
 * Annotation based {@link ObjectifyConfigurationSource}.
 * 
 * @author Marcel Overdijk
 * @since 0.1
 */
public class AnnotationObjectifyConfigurationSource extends ObjectifyConfigurationSourceSupport {

    private static final String BASE_PACKAGES = "basePackages";
    private static final String BASE_PACKAGE_CLASSES = "basePackageClasses";

    private final AnnotationMetadata metadata;
    private final AnnotationAttributes attributes;

    /**
     * Creates a new {@link AnnotationObjectifyConfigurationSource} from the given {@link AnnotationMetadata}.
     * 
     * @param metadata must not be {@literal null}.
     */
    public AnnotationObjectifyConfigurationSource(AnnotationMetadata metadata) {
        Assert.notNull(metadata);

        this.attributes = new AnnotationAttributes(metadata.getAnnotationAttributes(EnableObjectify.class.getName()));
        this.metadata = metadata;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.marceloverdijk.springappengine.objectify.config.ObjectifyConfigurationSource#getBasePackages()
     */
    @Override
    public Iterable<String> getBasePackages() {

        String[] value = attributes.getStringArray("value");
        String[] basePackages = attributes.getStringArray(BASE_PACKAGES);
        Class<?>[] basePackageClasses = attributes.getClassArray(BASE_PACKAGE_CLASSES);

        // if no explicit configuration is set return package of annotated class as default configuration
        if (value.length == 0 && basePackages.length == 0 && basePackageClasses.length == 0) {
            String className = metadata.getClassName();
            return Collections.singleton(className.substring(0, className.lastIndexOf('.')));
        }

        Set<String> packages = new HashSet<String>();
        packages.addAll(Arrays.asList(value));
        packages.addAll(Arrays.asList(basePackages));

        for (Class<?> typeName : basePackageClasses) {
            packages.add(ClassUtils.getPackageName(typeName));
        }

        return packages;
    }
}
