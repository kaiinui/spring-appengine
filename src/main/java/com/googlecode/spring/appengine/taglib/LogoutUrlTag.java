/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.spring.appengine.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.util.TagUtils;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * JSP {@link Tag} which returns the url that can be used to log the current user out.
 * Optionally exposes a <code>Object</code> scripting variable containing the value.
 * 
 * @author Marcel Overdijk
 * @since 0.2
 * @see UserService#createLogoutURL(String)
 */
@SuppressWarnings("serial")
public class LogoutUrlTag extends TagSupport {

    private String destinationUrl;
    private String var;
    private int scope = PageContext.PAGE_SCOPE;

    @Override
    public int doEndTag() throws JspException {
        String loginUrl = UserServiceFactory.getUserService().createLogoutURL(destinationUrl);
        if (var == null) {
            try {
                pageContext.getOut().print(loginUrl);
            }
            catch (IOException e) {
                throw new JspException(e);
            }
        }
        else {
            pageContext.setAttribute(var, loginUrl, scope);
        }
        return Tag.EVAL_PAGE;
    }

    /**
     * Set the destination url where the user will be redirected after they log out.
     */
    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    /**
     * Set the variable name to expose the login url under.
     * Defaults to rendering the login url to the current {@link javax.servlet.jsp.JspWriter}.
     */
    public void setVar(String var) {
        this.var = var;
    }

    /**
     * Set the scope to export the application identifier variable to.
     * This attribute has no meaning unless var is also defined.
     * Defaults to {@link PageContext#PAGE_SCOPE}.
     */
    public void setScope(String scope) {
        this.scope = TagUtils.getScope(scope);
    }
}
