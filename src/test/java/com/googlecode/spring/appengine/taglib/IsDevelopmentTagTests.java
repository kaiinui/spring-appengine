package com.googlecode.spring.appengine.taglib;

import static com.google.appengine.api.utils.SystemProperty.Environment.Value.Development;
import static com.google.appengine.api.utils.SystemProperty.Environment.Value.Production;
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
public class IsDevelopmentTagTests extends AbstractTagTests {

    private IsDevelopmentTag tag;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new IsDevelopmentTag();
        tag.setPageContext(pageContext);
    }

    @Test
    public void testRendersBodyIfDevelopment() throws Exception {
        SystemProperty.environment.set(Development);
        assertEquals(Tag.EVAL_BODY_INCLUDE, tag.doStartTag());
        assertEquals(Tag.EVAL_PAGE, tag.doEndTag());
    }

    @Test
    public void testSkipsBodyIfNotDevelopment() throws Exception {
        SystemProperty.environment.set(Production);
        assertEquals(Tag.SKIP_BODY, tag.doStartTag());
        assertEquals(Tag.EVAL_PAGE, tag.doEndTag());
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        SystemProperty.environment.set(Development);
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        assertTrue((Boolean) pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testVarExplicitScope() throws Exception {
        SystemProperty.environment.set(Development);
        tag.setVar("myVar");
        tag.setScope("request");
        tag.doStartTag();
        tag.doEndTag();
        assertTrue((Boolean) pageContext.getAttribute("myVar", PageContext.REQUEST_SCOPE));
    }
}
