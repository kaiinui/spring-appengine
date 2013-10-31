package com.googlecode.spring.appengine.taglib;

import static com.google.appengine.api.utils.SystemProperty.Environment.Value.Development;
import static com.google.appengine.api.utils.SystemProperty.Environment.Value.Production;
import static org.junit.Assert.assertEquals;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockPageContext;

import com.google.appengine.api.utils.SystemProperty;

/**
 * @author Marcel Overdijk
 */
public class IsDevelopmentTagTests {

    private IsDevelopmentTag tag;
    
    private MockPageContext pageContext;

    @Before
    public void setUp() throws Exception {
        tag = new IsDevelopmentTag();
    }

    @Test
    public void testVarDefaultScope() throws Exception {
        SystemProperty.environment.set(Development);
        tag.setVar("myVar");
        tag.doStartTag();
        tag.doEndTag();
        assertEquals("url/path", pageContext.getAttribute("myVar", PageContext.PAGE_SCOPE));
    }

    @Test
    public void testShowsBodyIfDevelopment() throws Exception {
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
    
    // TODO https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/test/java/org/springframework/web/servlet/tags/UrlTagTests.java
}
