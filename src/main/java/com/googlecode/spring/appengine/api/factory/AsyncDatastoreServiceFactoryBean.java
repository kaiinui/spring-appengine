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

import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceConfig;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

/**
 * {@link FactoryBean} that creates an {@link AsyncDatastoreService}.
 * 
 * <p>Example configuration:
 * 
 * <pre class="code"> &lt;bean id="asyncDatastoreService" class="com.googlecode.spring.appengine.api.factory.AsyncDatastoreServiceFactoryBean" /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class AsyncDatastoreServiceFactoryBean implements FactoryBean<AsyncDatastoreService>, InitializingBean {

    private AsyncDatastoreService asyncDatastoreService;

    private DatastoreServiceConfig config = DatastoreServiceConfig.Builder.withDefaults();

    @Override
    public AsyncDatastoreService getObject() throws Exception {
        return asyncDatastoreService;
    }

    @Override
    public Class<?> getObjectType() {
        return AsyncDatastoreService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        asyncDatastoreService = DatastoreServiceFactory.getAsyncDatastoreService(config);
    }

    // TODO: config setters
}
