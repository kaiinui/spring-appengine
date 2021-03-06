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
public class IsNotAdminTagTests extends AbstractTagTests {

    private IsNotAdminTag tag;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new IsNotAdminTag();
        tag.setPageContext(pageContext);
        helper.setEnvIsLoggedIn(true);
    }

    @Test
    public void testRendersBodyIfNotAdmin() throws Exception {
        helper.setEnvIsAdmin(false);
        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
        assertEquals(Tag.EVAL_PAGE, tag.doEndTag());
    }

    @Test
    public void testSkipsBodyIfAdmin() throws Exception {
        helper.setEnvIsAdmin(true);
        SystemProperty.environment.set(Development);
        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
        assertEquals(Tag.EVAL_PAGE, tag.doEndTag());
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        helper.setEnvIsAdmin(false);
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        assertTrue((Boolean) pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        helper.setEnvIsAdmin(false);
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        assertTrue((Boolean) pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
