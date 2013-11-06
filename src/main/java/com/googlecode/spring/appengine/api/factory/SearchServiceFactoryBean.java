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
import com.google.appengine.api.search.SearchServiceFactory;

/**
 * {@link FactoryBean} that creates a {@link SearchService}.
 * 
 * <p>Example configuration:
 * 
 * <pre class="code">
 * &lt;bean id="searchService" class="com.googlecode.spring.appengine.api.factory.SearchServiceFactoryBean" /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class SearchServiceFactoryBean implements FactoryBean<SearchService>, InitializingBean {

    // TODO: implement initializing bean to set config / namespace

    @Override
    public SearchService getObject() throws Exception {
        return SearchServiceFactory.getSearchService();
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
        // TODO
    }
}
