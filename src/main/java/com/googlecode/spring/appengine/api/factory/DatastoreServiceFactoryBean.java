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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceConfig;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.ImplicitTransactionManagementPolicy;
import com.google.appengine.api.datastore.ReadPolicy;

/**
 * {@link FactoryBean} that creates a {@link DatastoreService}.
 * 
 * <p>Example configuration:
 * 
 * <pre class="code">
 * &lt;bean id="datastoreService" class="com.googlecode.spring.appengine.api.factory.DatastoreServiceFactoryBean" /&gt;</pre>
 * 
 * <p>Also the properties of the datastore can be set, like the deadline:
 * 
 * <pre class="code">
 * &lt;bean id="datastoreService" class="com.googlecode.spring.appengine.api.factory.DatastoreServiceFactoryBean" p:deadline="5.0" /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class DatastoreServiceFactoryBean implements FactoryBean<DatastoreService>, InitializingBean {

    private DatastoreService datastoreService;

    private DatastoreServiceConfig config = DatastoreServiceConfig.Builder.withDefaults();

    @Override
    public DatastoreService getObject() throws Exception {
        return datastoreService;
    }

    @Override
    public Class<?> getObjectType() {
        return DatastoreService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        datastoreService = DatastoreServiceFactory.getDatastoreService(config);
    }

    /**
     * Set the deadline, in seconds, for all rpcs initiated by this {@link DatastoreService}.
     */
    public void setDeadline(double deadline) {
        this.config.deadline(deadline);
    }

    /**
     * Set the implicit transaction management policy.
     */
    public void setImplicitTransactionManagementPolicy(ImplicitTransactionManagementPolicy p) {
        this.config.implicitTransactionManagementPolicy(p);
    }

    /**
     * Set the maximum number of entity groups that can be represented in a single rpc. 
     */
    public void setMaxEntityGroupsPerRpc(int maxEntityGroupsPerRpc) {
        this.config.maxEntityGroupsPerRpc(maxEntityGroupsPerRpc);
    }

    /**
     * Set the read policy.
     */
    public void setReadPolicy(ReadPolicy readPolicy) {
        this.config.readPolicy(readPolicy);
    }
}
