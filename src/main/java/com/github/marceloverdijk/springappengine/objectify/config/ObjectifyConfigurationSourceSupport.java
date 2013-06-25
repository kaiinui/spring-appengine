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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.googlecode.objectify.annotation.Entity;

/**
 * Base class to implement {@link ObjectifyConfigurationSource}s.
 * 
 * @author Marcel Overdijk
 * @since 0.1
 */
public abstract class ObjectifyConfigurationSourceSupport implements ObjectifyConfigurationSource {

    /*
     * (non-Javadoc)
     * 
     * @see com.github.marceloverdijk.springappengine.objectify.config.ObjectifyConfigurationSource#getCandidates(org.springframework.context.annotation.
     * ClassPathScanningCandidateComponentProvider)
     */
    @Override
    public Collection<String> getCandidates(ResourceLoader loader) {

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        scanner.setResourceLoader(loader);

        Set<String> result = new HashSet<String>();

        for (String basePackage : getBasePackages()) {
            Collection<BeanDefinition> components = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition definition : components) {
                result.add(definition.getBeanClassName());
            }
        }

        return result;
    }
}
