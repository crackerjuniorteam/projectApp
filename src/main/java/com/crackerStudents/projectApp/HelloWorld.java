package com.crackerStudents.projectApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping
    public String index(){
        return "<h1> Hello, World!</h1>";
    }
}
