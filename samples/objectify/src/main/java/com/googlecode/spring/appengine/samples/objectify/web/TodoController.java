package com.googlecode.spring.appengine.samples.objectify.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.spring.appengine.objectify.OfyService;
import com.googlecode.spring.appengine.samples.objectify.domain.Todo;

@Controller
@RequestMapping("/")
public class TodoController {

    @Autowired
    private OfyService ofyService;

    @RequestMapping
    public String index(Model model) {
        List<Todo> todos = ofyService.ofy().load().type(Todo.class).list();
        model.addAttribute("todos", todos);
        return "index";
    }
}
