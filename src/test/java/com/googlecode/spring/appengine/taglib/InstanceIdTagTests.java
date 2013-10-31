package com.googlecode.spring.appengine.taglib;

import static org.junit.Assert.assertEquals;

import javax.servlet.jsp.PageContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import com.google.apphosting.api.ApiProxy;

/**
 * @author Marcel Overdijk
 */
public class InstanceIdTagTests extends AbstractTagTests {

    private InstanceIdTag tag;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new InstanceIdTag();
        tag.setPageContext(pageContext);
        ApiProxy.getCurrentEnvironment().getAttributes().put("com.google.appengine.instance.id", "my-instance-id");
    }

    @Test
    public void testRendersInstanceId() throws Exception {
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("my-instance-id", output);
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("", output);
        assertEquals("my-instance-id", pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("", output);
        assertEquals("my-instance-id", pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
