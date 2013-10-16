package com.googlecode.spring.appengine.samples.caching.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.spring.appengine.samples.caching.repository.TodoRepository;

@Controller
@RequestMapping("/")
public class TodoController {

    private static final Logger log = Logger.getLogger(TodoController.class.getName());

    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping
    public String index(Model model) {
        log.entering(TodoController.class.getSimpleName(), "index");
        model.addAttribute("todos", todoRepository.findAll());
        return "index";
    }
}
