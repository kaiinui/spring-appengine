package com.googlecode.spring.appengine.samples.objectify.config;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.googlecode.spring.appengine.objectify.OfyService;
import com.googlecode.spring.appengine.samples.objectify.domain.Todo;

public class DatastorePopulator implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = Logger.getLogger(DatastorePopulator.class.getName());

    @Autowired
    private OfyService ofyService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Populating datastore...");
        if (ofyService.ofy().load().type(Todo.class).count() == 0) {
            log.info("Todo datastore is empty, creating tasks...");
            try {
                ofyService.ofy().save().entities(
                        new Todo("Buy Beer"),
                        new Todo("Play Grand Theft Auto V"));
            }
            catch (Exception e) {
                log.warning("Unable to create tasks");
            }
        }
    }
}
