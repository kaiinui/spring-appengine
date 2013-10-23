package com.googlecode.spring.appengine.objectify;

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
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
@Repository
public class SimpleOfyRepository<T, ID extends Serializable> implements OfyRepository<T, ID> {

    private OfyService ofyService;
    private Class<T> type;

    public SimpleOfyRepository(OfyService ofyService) {
        Assert.notNull(ofyService, "OfyService must not be null");
        this.ofyService = ofyService;
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public long count() {
        return ofyService.ofy().load().type(type).count();
    }

    @Override
    public void delete(ID id) {
        // TODO
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        ofyService.ofy().delete().entities(entities);
    }

    @Override
    public void delete(T entity) {
        ofyService.ofy().delete().entity(entity);
    }

    @Override
    public void deleteAll() {
        // TODO
    }

    @Override
    public boolean exists(ID id) {
        // TODO
        return false;
    }

    @Override
    public List<T> findAll() {
        return ofyService.ofy().load().type(type).list();
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) {
        // TODO
        return null;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        // TODO
        return null;
    }

    @Override
    public List<T> findAll(Sort sort) {
        // TODO
        return null;
    }

    @Override
    public T findOne(ID id) {
//        if (id instanceof Long) {
//            return ofyService.ofy().load().type(type).id((Long) id).now();
//        }
//        else {
//            return ofyService.ofy().load().type(type).id((String) id).now();
//        }
        // TODO
        return null;
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        ofyService.ofy().save().entities(entities).now();
        List<S> temp = new ArrayList<S>();
        Iterator<S> iterator = entities.iterator();
        while (iterator.hasNext()) {
            temp.add(iterator.next());
        }
        return temp;
    }

    @Override
    public <S extends T> S save(S entity) {
        ofyService.ofy().save().entity(entity).now();
        return entity;
    }
}
