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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.util.TagUtils;

import com.google.appengine.api.utils.SystemProperty;

/**
 * Conditional JSP {@link Tag} which evaluates its body if the current executing 
 * environment is <code>Development</code>. 
 * Optionally exposes a <code>Boolean</code> scripting variable representing the 
 * evaluation of <code>SystemProperty.environment.value() == SystemProperty.Environment.Value.Development</code>.
 * 
 * @author Marcel Overdijk
 * @since 0.2
 * @see SystemProperty#environment
 */
@SuppressWarnings("serial")
public class IsDevelopmentTag extends TagSupport {

    private String var;
    
    private int scope = PageContext.PAGE_SCOPE;

    @Override
    public int doStartTag() throws JspException {
        boolean development = SystemProperty.environment.value() == SystemProperty.Environment.Value.Development;
        if (var != null) {
            pageContext.setAttribute(var, development, scope);
        }
        return development ? Tag.EVAL_BODY_INCLUDE : Tag.SKIP_BODY;
    }

    /**
     * Set the variable name to expose the value under. 
     */
    public void setVar(String var) {
        this.var = var;
    }
    
    /**
     * Set the scope to export the variable to. 
     * This attribute has no meaning unless var is also defined.
     * Defaults to {@link PageContext#PAGE_SCOPE}.
     */
    public void setScope(String scope) {
        this.scope = TagUtils.getScope(scope);
    }
}
