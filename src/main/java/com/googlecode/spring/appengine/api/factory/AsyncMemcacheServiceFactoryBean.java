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
import org.springframework.util.StringUtils;

import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 * {@link FactoryBean} that creates an {@link AsyncMemcacheService}.
 * 
 * <p>Example configuration:
 * 
 * <pre class="code">
 * &lt;bean id="asyncMemcacheService" class="com.googlecode.spring.appengine.api.factory.AsyncMemcacheServiceFactoryBean" /&gt;</pre>
 * 
 * <p>To specify the namespace use:
 * 
 * <pre class="code">
 * &lt;bean id="asyncMemcacheService" class="com.googlecode.spring.appengine.api.factory.AsyncMemcacheServiceFactoryBean" p:namespace="theNamespace" /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class AsyncMemcacheServiceFactoryBean implements FactoryBean<AsyncMemcacheService>, InitializingBean {

    private AsyncMemcacheService asyncMemcacheService;

    private String namespace;

    @Override
    public AsyncMemcacheService getObject() throws Exception {
        return asyncMemcacheService;
    }

    @Override
    public Class<?> getObjectType() {
        return AsyncMemcacheService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.hasText(namespace)) {
            asyncMemcacheService = MemcacheServiceFactory.getAsyncMemcacheService(namespace);
        }
        else {
            asyncMemcacheService = MemcacheServiceFactory.getAsyncMemcacheService();
        }
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
