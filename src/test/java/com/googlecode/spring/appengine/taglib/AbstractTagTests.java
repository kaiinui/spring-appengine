package com.googlecode.spring.appengine.taglib;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public abstract class AbstractTagTests {

    // TODO check docs how to create mock page page context best
    
    
    protected MockPageContext createPageContext() {
        MockServletContext servletContext = new MockServletContext();
        SimpleWebApplicationContext webApplicationContext = new SimpleWebApplicationContext();
        webApplicationContext.setServletContext(servletContext);
        webApplicationContext.setNamespace("test");
        webApplicationContext.refresh();
        MockHttpServletRequest request = new MockHttpServletRequest(servletContext);
        MockHttpServletResponse response = new MockHttpServletResponse();
        if (inDispatcherServlet()) {
            request.setAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE, webApplicationContext);
        }
        else {
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, webApplicationContext);
        }
        return new MockPageContext(servletContext, request, response);
    }

    protected boolean inDispatcherServlet() {
        return true;
    }
}
