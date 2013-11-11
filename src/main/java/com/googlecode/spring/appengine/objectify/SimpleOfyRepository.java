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

import com.googlecode.objectify.cmd.DeleteType;
import com.googlecode.objectify.cmd.Deleter;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.cmd.Saver;

/**
 * Default implementation of the {@link OfyRepository} interface.
 * 
 * @author Marcel Overdijk
 * @since 0.2
 * @param <T> The type of the entity to handle
 * @param <ID> The type of the entity's identifier
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
        return query().count();
    }

    @Override
    public void delete(ID id) {
        Assert.notNull(id, "The id must not be null");
        if (id instanceof Long) {
            deleteType().id((Long) id).now();
        }
        else {
            deleteType().id((String) id).now();
        }
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        Assert.notNull(entities, "The iterable of entities must not be null");
        delete().entities(entities).now();
    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null");
        delete().entity(entity).now();
    }

    @Override
    public void deleteAll() {
        delete().keys(query().keys().list()).now();
    }

    @Override
    public boolean exists(ID id) {
        Assert.notNull(id, "The id must not be null");
        return findOne(id) != null;
    }

    @Override
    public List<T> findAll() {
        return query().list();
    }

    @Override
    public List<T> findAll(Iterable<ID> ids) {
        Assert.notNull(ids, "The iterable of ids must not be null");
        Map<ID, T> entities = loadType().ids(ids);
        return new ArrayList<T>(entities.values());
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<T>(findAll());
        }
        Query<T> query = query();
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        long total = count();
        List<T> content = total > pageable.getOffset() ? query.list() : Collections.<T> emptyList();
        return new PageImpl<T>(content, pageable, total);
    }

    @Override
    public List<T> findAll(Sort sort) {
        Query<T> query = query();
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
            return loadType().id((Long) id).now();
        }
        else {
            return loadType().id((String) id).now();
        }
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        Assert.notNull(entities, "The iterable of entities must not be null");
        save().entities(entities).now();
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
        save().entity(entity).now();
        return entity;
    }

    protected Deleter delete() {
        return ofyService.delete();
    }

    protected DeleteType deleteType() {
        return ofyService.delete().type(type);
    }

    protected LoadType<T> loadType() {
        return ofyService.load().type(type);
    }

    protected Query<T> query() {
        return ofyService.load().type(type);
    }

    protected Saver save() {
        return ofyService.save();
    }
}
