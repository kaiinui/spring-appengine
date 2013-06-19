package com.github.marceloverdijk.springappengine.samples.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.marceloverdijk.springappengine.samples.repository.ToDoRepository;

@Controller
@RequestMapping("/")
public class ToDoController {

    private static final Logger log = Logger.getLogger(ToDoController.class.getName());
    
    private final ToDoRepository toDoRepository;
    
    @Autowired
    public ToDoController(ToDoRepository toDoRepository) {
        Assert.notNull(toDoRepository);
        this.toDoRepository = toDoRepository;
    }
    
    @RequestMapping
    public String index(Model model) {
        log.entering(ToDoController.class.getSimpleName(), "index");
        model.addAttribute("toDos", toDoRepository.get());
        return "index";
    }
}
