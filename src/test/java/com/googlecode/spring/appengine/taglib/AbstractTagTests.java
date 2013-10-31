package com.googlecode.spring.appengine.taglib;

import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;

public abstract class AbstractTagTests {

    protected MockServletContext servletContext;
    protected MockPageContext pageContext;
    
    protected LocalServiceTestHelper helper;

    public void setUp() throws Exception {
        servletContext = new MockServletContext();
        pageContext = new MockPageContext(servletContext);
        helper = new LocalServiceTestHelper(new LocalUserServiceTestConfig());
        helper.setUp();
    }
}
