package com.googlecode.spring.appengine.taglib;

import static com.google.appengine.api.utils.SystemProperty.Environment.Value.Production;
import static org.junit.Assert.assertEquals;

import javax.servlet.jsp.PageContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import com.google.appengine.api.utils.SystemProperty;

/**
 * @author Marcel Overdijk
 */
public class ApplicationVersionTagTests extends AbstractTagTests {

    private ApplicationVersionTag tag;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new ApplicationVersionTag();
        tag.setPageContext(pageContext);
        System.setProperty("com.google.appengine.application.version", "99.1");
    }

    @Test
    public void testShowsApplicationVersion() throws Exception {
        helper.setEnvAppId("myAppId");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString(); 
        assertEquals("99.1", output);
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        assertEquals("99.1", pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        SystemProperty.environment.set(Production);
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        assertEquals("99.1", pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
