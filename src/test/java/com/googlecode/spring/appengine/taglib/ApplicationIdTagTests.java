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
public class ApplicationIdTagTests extends AbstractTagTests {

    private ApplicationIdTag tag;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new ApplicationIdTag();
        tag.setPageContext(pageContext);
        System.setProperty("com.google.appengine.application.id", "myAppId");
    }

    @Test
    public void testShowsApplicationId() throws Exception {
        helper.setEnvAppId("myAppId");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString(); 
        assertEquals("myAppId", output);
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        assertEquals("myAppId", pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        SystemProperty.environment.set(Production);
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        assertEquals("myAppId", pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
