package com.googlecode.spring.appengine.taglib;

import static org.junit.Assert.assertEquals;

import javax.servlet.jsp.PageContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Marcel Overdijk
 */
public class LoginUrlTagTests extends AbstractTagTests {

    private LoginUrlTag tag;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new LoginUrlTag();
        tag.setPageContext(pageContext);
        tag.setDestinationUrl("/my-destination-url");
    }

    @Test
    public void testRendersLoginUrl() throws Exception {
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("/_ah/login?continue=%2Fmy-destination-url", output);
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("", output);
        assertEquals("/_ah/login?continue=%2Fmy-destination-url", pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("", output);
        assertEquals("/_ah/login?continue=%2Fmy-destination-url", pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
