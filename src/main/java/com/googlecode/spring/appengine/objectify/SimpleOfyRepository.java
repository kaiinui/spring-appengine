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

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.googlecode.objectify.cmd.Query;

/**
 * TODO
 * 
 * @author Marcel Overdijk
 * @since 0.2
 */
@Repository
public class SimpleOfyRepository<T, ID extends Serializable> implements OfyRepository<T, ID> {

    protected OfyService ofyService;
    protected Class<T> type;

    @SuppressWarnings("unchecked")
    public SimpleOfyRepository(OfyService ofyService) {
        Assert.notNull(ofyService, "OfyService must not be null");
        this.ofyService = ofyService;
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public long count() {
        return ofyService.load().type(type).count();
    }

    @Override
    public void delete(ID id) {
        Assert.notNull(id, "The id must not be null");
        if (id instanceof Long) {
            ofyService.delete().type(type).id((Long) id).now();
        }
        else {
            ofyService.delete().type(type).id((String) id).now();
        }
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        Assert.notNull(entities, "The iterable of entities must not be null"); 
        ofyService.delete().entities(entities).now();
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null");
        ofyService.delete().entity(entity).now();
    }

    @Override
    public void deleteAll() {
        ofyService.delete().keys(ofyService.load().type(type).keys().list()).now();
    }

    @Override
    public boolean exists(ID id) {
        Assert.notNull(id, "The id must not be null");
        return findOne(id) != null;
    }

    @Override
    public List<T> findAll() {
        return ofyService.load().type(type).list();
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) {
        Assert.notNull(ids, "The iterable of ids must not be null");
        Map<ID, T> entities = ofyService.load().type(type).ids(ids);
        return new ArrayList<T>(entities.values());
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<T>(findAll());
        }
        Query<T> query = ofyService.load().type(type);
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        long total = count();
        List<T> content = total > pageable.getOffset() ? query.list() : Collections.<T> emptyList();
        return new PageImpl<T>(content, pageable, total);
    }

    @Override
    public List<T> findAll(Sort sort) {
        Query<T> query = ofyService.load().type(type);
        if (sort != null) {
            Iterator<Sort.Order> iterator = sort.iterator();
            while (iterator.hasNext()) {
                Sort.Order order = iterator.next();
                query = query.order((order.isAscending() ? "" : "-") + order.getProperty());
            }
        }
        return query.list();
    }

    @Override
    public T findOne(ID id) {
        Assert.notNull(id, "The id must not be null");
        if (id instanceof Long) {
            return ofyService.load().type(type).id((Long) id).now();
        }
        else {
            return ofyService.load().type(type).id((String) id).now();
        }
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        Assert.notNull(entities, "The iterable of entities must not be null");
        ofyService.save().entities(entities).now();
        List<S> temp = new ArrayList<S>();
        Iterator<S> iterator = entities.iterator();
        while (iterator.hasNext()) {
            temp.add(iterator.next());
        }
        return temp;
    }

    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "The entity must not be null");
        ofyService.save().entity(entity).now();
        return entity;
    }
}
