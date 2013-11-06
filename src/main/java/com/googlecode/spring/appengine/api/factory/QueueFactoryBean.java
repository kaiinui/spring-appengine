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

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;

/**
 * {@link FactoryBean} that creates an {@link Queue}.
 * 
 * <p>Example configuration:
 * 
 * <pre class="code"> &lt;bean id="queue" class="com.googlecode.spring.appengine.api.factory.QueueFactoryBean" /&gt;</pre>
 * 
 * <p>Above example will return the default queue. To specify the queue name use:
 * 
 * <pre class="code"> &lt;bean id="queue" class="com.googlecode.spring.appengine.api.factory.QueueFactoryBean" p:name="theName" /&gt;</pre>
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
public class QueueFactoryBean implements FactoryBean<Queue>, InitializingBean {

    private Queue queue;

    private String name;

    @Override
    public Queue getObject() throws Exception {
        return queue;
    }

    @Override
    public Class<?> getObjectType() {
        return Queue.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.hasText(name)) {
            queue = QueueFactory.getQueue(name);
        }
        else {
            queue = QueueFactory.getDefaultQueue();
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
