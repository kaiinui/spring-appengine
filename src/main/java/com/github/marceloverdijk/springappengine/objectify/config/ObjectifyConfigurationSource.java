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

import org.springframework.core.io.ResourceLoader;

/**
 * Interface containing the configurable options for the Objectify OfyService.
 * 
 * @author Marcel Overdijk
 * @since 0.1
 */
public interface ObjectifyConfigurationSource {

    /**
     * Returns the base packages the Objectify entity classes shall be found under.
     * 
     * @return must not be {@literal null}.
     */
    Iterable<String> getBasePackages();

    /**
     * Returns the fully-qualified names of the Objectify entity classes.
     * 
     * @param loader
     * @return
     */
    Collection<String> getCandidates(ResourceLoader loader);
}
