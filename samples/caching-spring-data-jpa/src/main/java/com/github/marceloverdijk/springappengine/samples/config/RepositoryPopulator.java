package com.github.marceloverdijk.springappengine.samples.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.DataAccessException;

import com.github.marceloverdijk.springappengine.samples.domain.Todo;
import com.github.marceloverdijk.springappengine.samples.repository.TodoRepository;

public class RepositoryPopulator implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = Logger.getLogger(RepositoryPopulator.class.getName());

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Populating repository...");
        if (todoRepository.count() == 0) {
            log.info("Todo repository is empty, creating tasks...");
            try {
                todoRepository.saveAndFlush(new Todo("Buy Beer"));
                todoRepository.saveAndFlush(new Todo("Play The Last of Us"));
            }
            catch (DataAccessException e) {
                log.warning("Unable to create tasks");
            }
        }
    }
}
