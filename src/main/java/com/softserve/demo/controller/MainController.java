package com.softserve.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index() {
        logger.info("page was opened");
        return "Hello world";
    }
}
