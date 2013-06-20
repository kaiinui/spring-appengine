package com.github.marceloverdijk.springappengine.samples.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.marceloverdijk.springappengine.samples.repository.TodoRepository;

@Controller
@RequestMapping("/")
public class TodoController {

    private final TodoRepository todoRepository;
    
    @Autowired
    public TodoController(TodoRepository todoRepository) {
        Assert.notNull(todoRepository);
        this.todoRepository = todoRepository;
    }
    
    @RequestMapping
    public String index(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        return "index";
    }
}
