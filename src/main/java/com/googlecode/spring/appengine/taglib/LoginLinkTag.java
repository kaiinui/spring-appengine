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
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * JSP {@link Tag} which renders the link that can be used to display a login page to the user.
 * 
 * @author Marcel Overdijk
 * @since 0.2
 * @see UserService#createLoginURL(String)
 */
@SuppressWarnings("serial")
public class LoginLinkTag extends TagSupport implements DynamicAttributes {

    private String destinationUrl;
    private String federatedIdentity;
    private Map<String, Object> dynamicAttributes;

    @Override
    public int doStartTag() throws JspException {
        String loginUrl = UserServiceFactory.getUserService().createLoginURL(destinationUrl, federatedIdentity);
        try {
            JspWriter out = pageContext.getOut();
            out.print("<a href=\"" + loginUrl + "\" ");
            for (Map.Entry<String, Object> dynamicAttribute : dynamicAttributes.entrySet()) {
                String key = dynamicAttribute.getKey();
                Object val = dynamicAttribute.getValue();
                if (val != null) {
                    out.print(key + "=\"" + val.toString() + "\" ");
                }
            }
            out.print(">");
        }
        catch (IOException e) {
            throw new JspException(e);
        }
        return Tag.EVAL_BODY_INCLUDE;
    }
    
    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().print("</a>");;
        }
        catch (IOException e) {
            throw new JspException(e);
        }
        return Tag.EVAL_PAGE;
    }

    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (this.dynamicAttributes == null) {
            this.dynamicAttributes = new HashMap<String, Object>();
        }
        dynamicAttributes.put(localName, value);
    }

    /**
     * Set the destination url where the user will be redirected after they log in.
     */
    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    /**
     * Set the federated identity string which is to be asserted for users who are authenticated using a non-Google ID (e.g., OpenID). In order to use federated logins this feature must be enabled for the application.
     */
    public void setFederatedIdentity(String federatedIdentity) {
        this.federatedIdentity = federatedIdentity;
    }
}
