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

import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;

/**
 * {@link FactoryBean} that creates a {@link MailService}.
 * 
 * <p>Example configuration:
 * 
 * <pre class="code">
 * &lt;bean id="mailService" class="com.googlecode.spring.appengine.api.factory.MailServiceFactoryBean" /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class MailServiceFactoryBean implements FactoryBean<MailService> {

    @Override
    public MailService getObject() throws Exception {
        return MailServiceFactory.getMailService();
    }

    @Override
    public Class<?> getObjectType() {
        return MailService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
