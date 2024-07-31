package com.example.Config_New.controller;

import com.example.Config_New.repository.IModunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    private IModunRepository repository;

    @GetMapping("hello")
    public String index() {
        System.out.println("Hello method");
        System.out.println(repository.findAll().get(0));
        return "hello";
    }
}
