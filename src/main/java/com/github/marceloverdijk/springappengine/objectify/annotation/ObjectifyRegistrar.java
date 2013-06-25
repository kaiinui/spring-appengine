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
package com.github.marceloverdijk.springappengine.objectify.annotation;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import com.github.marceloverdijk.springappengine.objectify.OfyService;
import com.github.marceloverdijk.springappengine.objectify.config.AnnotationObjectifyConfigurationSource;
import com.googlecode.objectify.ObjectifyFactory;

/**
 * {@link ImportBeanDefinitionRegistrar} to enable {@link EnableObjectify} annotation.
 * 
 * @author Marcel Overdijk
 * @since 0.1
 */
@Configuration
public class ObjectifyRegistrar implements ImportBeanDefinitionRegistrar, BeanClassLoaderAware, ResourceLoaderAware {

    private ClassLoader beanClassLoader;
    private ResourceLoader resourceLoader;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.BeanClassLoaderAware#setBeanClassLoader(java.lang.ClassLoader)
     */
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.context.ResourceLoaderAware#setResourceLoader(org.springframework.core.io.ResourceLoader)
     */
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.context.annotation.ImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata,
     * org.springframework.beans.factory.support.BeanDefinitionRegistry)
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        Assert.notNull(annotationMetadata);
        Assert.notNull(registry);

        OfyService ofyService = new OfyService();
        ObjectifyFactory factory = ofyService.factory();

        AnnotationObjectifyConfigurationSource configuration = new AnnotationObjectifyConfigurationSource(annotationMetadata);
        for (String candidate : configuration.getCandidates(resourceLoader)) {
            Class<?> clazz = ClassUtils.resolveClassName(candidate, beanClassLoader);
            factory.register(clazz);
        }

        // TODO see RepositoryBeanDefinitionRegistrarSupport
    }
}
