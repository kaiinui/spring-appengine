package com.googlecode.spring.appengine.taglib;

import static com.google.appengine.api.utils.SystemProperty.Environment.Value.Development;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.utils.SystemProperty;

/**
 * @author Marcel Overdijk
 */
public class IsLoggedInTagTests extends AbstractTagTests {

    private IsLoggedInTag tag;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new IsLoggedInTag();
        tag.setPageContext(pageContext);
    }

    @Test
    public void testRendersBodyIfLoggedIn() throws Exception {
        helper.setEnvIsLoggedIn(true);
        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
        assertEquals(Tag.EVAL_PAGE, tag.doEndTag());
    }

    @Test
    public void testSkipsBodyIfNotLoggedIn() throws Exception {
        helper.setEnvIsLoggedIn(false);
        SystemProperty.environment.set(Development);
        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
        assertEquals(Tag.EVAL_PAGE, tag.doEndTag());
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        helper.setEnvIsLoggedIn(true);
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        assertTrue((Boolean) pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        helper.setEnvIsLoggedIn(true);
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        assertTrue((Boolean) pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
