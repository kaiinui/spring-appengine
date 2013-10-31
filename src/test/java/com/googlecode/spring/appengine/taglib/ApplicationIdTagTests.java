package com.googlecode.spring.appengine.taglib;

import static org.junit.Assert.assertEquals;

import javax.servlet.jsp.PageContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Marcel Overdijk
 */
public class ApplicationIdTagTests extends AbstractTagTests {

    private ApplicationIdTag tag;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new ApplicationIdTag();
        tag.setPageContext(pageContext);
        System.setProperty("com.google.appengine.application.id", "my-application-id");
    }

    @Test
    public void testRendersApplicationId() throws Exception {
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("my-application-id", output);
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("", output);
        assertEquals("my-application-id", pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("", output);
        assertEquals("my-application-id", pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
