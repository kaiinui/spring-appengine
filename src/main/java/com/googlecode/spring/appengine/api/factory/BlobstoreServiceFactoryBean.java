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

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

/**
 * {@link FactoryBean} that creates a {@link BlobstoreService}.
 * 
 * <p>Example configuration:
 * 
 * <pre class="code">
 * &lt;bean id="blobstoreService" class="com.googlecode.spring.appengine.api.factory.BlobstoreServiceFactoryBean" /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class BlobstoreServiceFactoryBean implements FactoryBean<BlobstoreService> {

    @Override
    public BlobstoreService getObject() throws Exception {
        return BlobstoreServiceFactory.getBlobstoreService();
    }

    @Override
    public Class<?> getObjectType() {
        return BlobstoreService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
