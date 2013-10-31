package com.googlecode.spring.appengine.taglib;

import static org.junit.Assert.assertEquals;

import javax.servlet.jsp.PageContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Marcel Overdijk
 */
public class RuntimeEnvironmentTagTests extends AbstractTagTests {

    private RuntimeEnvironmentTag tag;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new RuntimeEnvironmentTag();
        tag.setPageContext(pageContext);
        System.setProperty("com.google.appengine.runtime.environment", "my-runtime-environment");
    }

    @Test
    public void testRendersRuntimeEnvironment() throws Exception {
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("my-runtime-environment", output);
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("", output);
        assertEquals("my-runtime-environment", pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("", output);
        assertEquals("my-runtime-environment", pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
