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
package com.googlecode.spring.appengine.objectify;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Deleter;
import com.googlecode.objectify.cmd.Loader;
import com.googlecode.objectify.cmd.Saver;

/**
 * Service implementation following the Objectify best practice pattern to create
 * a custom service to interact with the master {@link ObjectifyFactory} and get {@link Objectify} instances.
 * 
 * @author Marcel Overdijk
 * @since 0.2
 * @see <a href="http://code.google.com/p/objectify-appengine/wiki/BestPractices#Use_Your_Own_Service">Objectify best practices</a>
 */
public class OfyService {

    public Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

    public Deleter delete() {
        return ofy().delete();
    }

    public Loader load() {
        return ofy().load();
    }

    public Saver save() {
        return ofy().save();
    }
}
