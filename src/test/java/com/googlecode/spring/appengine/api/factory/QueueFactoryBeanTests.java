package com.googlecode.spring.appengine.api.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.taskqueue.Queue;

/**
 * @author Marcel Overdijk
 */
public class QueueFactoryBeanTests {

    private QueueFactoryBean factoryBean;

    @Before
    public void setUp() {
        factoryBean = new QueueFactoryBean();
    }
    
    @Test
    public void testDefaultQueue() throws Exception {
        factoryBean.afterPropertiesSet();
        Queue queue = factoryBean.getObject();
        assertEquals("default", queue.getQueueName());
    }
    
    @Test
    public void testQueueByName() throws Exception {
        factoryBean.setName("myQueue");
        factoryBean.afterPropertiesSet();
        Queue queue = factoryBean.getObject();
        assertEquals("myQueue", queue.getQueueName());
    }

    @Test
    public void testTypeIsQueue() {
        assertTrue(factoryBean.getObjectType().isAssignableFrom(Queue.class));
    }

    @Test
    public void testIsPrototype() {
        assertFalse(factoryBean.isSingleton());
    }
}
