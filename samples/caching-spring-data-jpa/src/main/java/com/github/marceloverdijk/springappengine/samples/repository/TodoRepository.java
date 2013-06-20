package com.github.marceloverdijk.springappengine.samples.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.github.marceloverdijk.springappengine.samples.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Override
    @Cacheable(value = "todos")
    @Transactional(readOnly = true)
    List<Todo> findAll();

    @Override
    @CacheEvict(value = "todos", allEntries = true)
    @Transactional
    <S extends Todo> S save(S entity);

    @Override
    @CacheEvict(value = "todos", allEntries = true)
    @Transactional
    <S extends Todo> List<S> save(Iterable<S> entities);

    @Override
    @CacheEvict(value = "todos", allEntries = true)
    @Transactional
    Todo saveAndFlush(Todo entity);

    @Override
    @CacheEvict(value = "todos", allEntries = true)
    @Transactional
    void delete(Long id);

    @Override
    @CacheEvict(value = "todos", allEntries = true)
    @Transactional
    void delete(Iterable<? extends Todo> entities);

    @Override
    @CacheEvict(value = "todos", allEntries = true)
    @Transactional
    void delete(Todo entity);

    @Override
    @CacheEvict(value = "todos", allEntries = true)
    @Transactional
    void deleteAll();

    @Override
    @CacheEvict(value = "todos", allEntries = true)
    @Transactional
    void deleteAllInBatch();

    @Override
    @CacheEvict(value = "todos", allEntries = true)
    @Transactional
    void deleteInBatch(Iterable<Todo> entities);
}
