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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.impl.translate.TranslatorFactory;

/**
 * {@link OfyService} builder typically used when having a Java-based container 
 * configuration setup.
 * 
 * <p> The builder can scan for {@link Entity} annotated classes by adding base packages.
 * Instead of scanning for entities it is also possible to register them manually.
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class OfyServiceBuilder {

    private final Log logger = LogFactory.getLog(getClass());

    private List<String> basePackages = new ArrayList<String>();
    private List<Class<?>> entityClasses = new ArrayList<Class<?>>();
    private List<TranslatorFactory<?>> translatorFactories = new ArrayList<TranslatorFactory<?>>();

    public OfyServiceBuilder addBasePackage(String basePackage) {
        basePackages.add(basePackage);
        return this;
    }

    public OfyServiceBuilder registerEntity(Class<?> clazz) {
        entityClasses.add(clazz);
        return this;
    }

    public OfyServiceBuilder registerTranslatorFactory(TranslatorFactory<?> translatorFactory) {
        translatorFactories.add(translatorFactory);
        return this;
    }

    public OfyService build() {
        long startTime = System.currentTimeMillis();
        entityClasses.addAll(scanBasePackages());
        OfyService ofyService = new OfyService();
        for (Class<?> clazz : entityClasses) {
            ofyService.factory().register(clazz);
            if (logger.isInfoEnabled()) {
                logger.info("Registered entity class [" + clazz.getName() + "]");
            }
        }
        for (TranslatorFactory<?> translatorFactory : translatorFactories) {
            ofyService.factory().getTranslators().add(translatorFactory);
            if (logger.isInfoEnabled()) {
                logger.info("Registered translator factory [" + translatorFactory.getClass().getName() + "]");
            }
        }
        if (this.logger.isInfoEnabled()) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            this.logger.info("Building objectify service completed in " + elapsedTime + " ms");
        }
        return ofyService;
    }

    protected List<Class<?>> scanBasePackages() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (String basePackage : basePackages) {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("Scanning package [" + basePackage + "]");
            }
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
            Set<BeanDefinition> candidates = scanner.findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                Class<?> clazz = ClassUtils.resolveClassName(candidate.getBeanClassName(), ClassUtils.getDefaultClassLoader());
                classes.add(clazz);
            }
        }
        return classes;
    }
}
