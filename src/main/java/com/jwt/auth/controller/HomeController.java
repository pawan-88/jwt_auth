package com.jwt.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HomeController {

    @RequestMapping("/welcome")
    public String welcome(){
        String text ="this is private text ";

        text +="this page is not allowed to unauthrozied  users";
        return text;

    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(){
        return "{\"name\":\"Pawan\"}";
    }

    @GetMapping("/getName")
    public String getName(){
        return "Pawan Sanap";
    }
}
