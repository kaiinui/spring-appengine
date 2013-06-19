package com.github.marceloverdijk.springappengine.samples.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class ToDoRepository {

    private static final Logger log = Logger.getLogger(ToDoRepository.class.getName());
    
    private List<String> toDos;
    
    public ToDoRepository() {
        toDos = new ArrayList<String>();
        toDos.add("Buy Beer");
        toDos.add("Play The Last Of Us");
    }

    @Cacheable("toDos")
    public List<String> get() {
        log.entering(ToDoRepository.class.getSimpleName(), "get");
        return toDos;
    }
}
