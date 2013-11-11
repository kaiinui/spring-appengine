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
package com.googlecode.spring.appengine.api.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.google.appengine.api.search.SearchService;
import com.google.appengine.api.search.SearchServiceConfig;
import com.google.appengine.api.search.SearchServiceFactory;

/**
 * {@link FactoryBean} that creates a {@link SearchService}.
 * 
 * <p>
 * Example configuration:
 * 
 * <pre class="code">
 * &lt;bean id="searchService" class="com.googlecode.spring.appengine.api.factory.SearchServiceFactoryBean" /&gt;
 * </pre>
 * 
 * <p>Also the configurations options of the Search API can be set, like the deadline:
 * 
 * <pre class="code">
 * &lt;bean id="searchService" class="com.googlecode.spring.appengine.api.factory.SearchServiceFactoryBean" p:deadline="5.0" /&gt;
 * </pre>
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class SearchServiceFactoryBean implements FactoryBean<SearchService>, InitializingBean {

    private SearchService searchService;

    private SearchServiceConfig.Builder configBuilder = SearchServiceConfig.newBuilder();

    @Override
    public SearchService getObject() throws Exception {
        return searchService;
    }

    @Override
    public Class<?> getObjectType() {
        return SearchService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        searchService = SearchServiceFactory.getSearchService(configBuilder.build());
    }

    /**
     * Set the deadline in seconds. 
     */
    public void setDeadline(Double deadlineInSeconds) {
        this.configBuilder.setDeadline(deadlineInSeconds);
    }

    /**
     * Specify the namespace to use.
     */
    public void setNamespace(String namespace) {
        this.configBuilder.setNamespace(namespace);
    }
}
