package com.github.marceloverdijk.springappengine.samples.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.marceloverdijk.springappengine.samples.repository.TodoRepository;

@Controller
@RequestMapping("/")
public class TodoController {

    private static final Logger log = Logger.getLogger(TodoController.class.getName());
    
    private final TodoRepository todoRepository;
    
    @Autowired
    public TodoController(TodoRepository todoRepository) {
        Assert.notNull(todoRepository);
        this.todoRepository = todoRepository;
    }
    
    @RequestMapping
    public String index(Model model) {
        log.entering(TodoController.class.getSimpleName(), "index");
        model.addAttribute("todos", todoRepository.findAll());
        return "index";
    }
}
