package com.github.marceloverdijk.springappengine.samples.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {

    private static final Logger log = Logger.getLogger(TodoRepository.class.getName());
    
    private List<String> todos;
    
    public TodoRepository() {
        todos = new ArrayList<String>();
        todos.add("Buy Beer");
        todos.add("Play The Last of Us");
    }

    @Cacheable("todos")
    public List<String> findAll() {
        log.entering(TodoRepository.class.getSimpleName(), "findAll");
        return todos;
    }
}
