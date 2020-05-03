package com.firstproject.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @Value("${app.version}")
    private String appVersion;
    @Value("${info.dev}")
    private String dev;
    @Value("${contact}")
    private String contact;
    @Value("${app.nome}")
    private String name;

    @GetMapping
    @RequestMapping
    public Map getStatus() {
        Map map = new HashMap<String, String>();
        map.put("app-version", appVersion);
        map.put("info-dev", dev);
        map.put("contact:", contact);
        map.put("name: ", name);
        return map;
    }
}
