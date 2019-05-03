package com.softserve.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index() {
        return "Hello world";
    }
}
