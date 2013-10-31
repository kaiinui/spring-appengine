package com.googlecode.spring.appengine.taglib;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Marcel Overdijk
 */
public class LoginLinkTagTests extends AbstractTagTests {

    private LoginLinkTag tag;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tag = new LoginLinkTag();
        tag.setPageContext(pageContext);
        tag.setDestinationUrl("/my-destination-url");
    }

    @Test
    public void testRendersLoginLink() throws Exception {
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("<a href=\"/_ah/login?continue=%2Fmy-destination-url\"></a>", output);
    }
    
    @Test
    public void testRendersLoginLinkWithDynamicAttributes() throws Exception {
        tag.setDynamicAttribute(null, "id", "my-id");
        tag.setDynamicAttribute(null, "class", "my-class");
        tag.doStartTag();
        tag.doEndTag();
        String output = ((MockHttpServletResponse) pageContext.getResponse()).getContentAsString();
        assertEquals("<a href=\"/_ah/login?continue=%2Fmy-destination-url\" id=\"my-id\" class=\"my-class\"></a>", output);
    }
}
