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
public class IsNotLoggedInTagTests extends AbstractTagTests {

    private IsNotLoggedInTag tag;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new IsNotLoggedInTag();
        tag.setPageContext(pageContext);
    }

    @Test
    public void testRendersBodyIfNotLoggedIn() throws Exception {
        helper.setEnvIsLoggedIn(false);
        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
        assertEquals(Tag.EVAL_PAGE, tag.doEndTag());
    }

    @Test
    public void testSkipsBodyIfLoggedIn() throws Exception {
        helper.setEnvIsLoggedIn(true);
        SystemProperty.environment.set(Development);
        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
        assertEquals(Tag.EVAL_PAGE, tag.doEndTag());
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        helper.setEnvIsLoggedIn(false);
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        assertTrue((Boolean) pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        helper.setEnvIsLoggedIn(false);
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        assertTrue((Boolean) pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
