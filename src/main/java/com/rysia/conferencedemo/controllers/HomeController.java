package com.rysia.conferencedemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class HomeController {
    @Value("${app.version}")
    private String version;
    @Value("${app.info.dev}")
    private String developer;
    @Value("${app.developer.name}")
    private String developerName;
    @Value("${app.contact}")
    private String contact;
    @Value("${app.name}")
    private String name;
    @Value("${app.description}")
    private String description;
    @Value("${app.license}")
    private String license;
    @Value("${app.license.url}")
    private String licenseURL;


    @GetMapping
    @RequestMapping
    public Map getStatus() {
        Map map = new HashMap<String, String>();
        map.put("app-version", version);
        map.put("app-info-dev", developer);
        map.put("app-contact:", contact);
        map.put("app-name:", name);
        map.put("app-description:", description);
        map.put("app-license:", license);
        map.put("app-license-url:", licenseURL);
        return map;
    }

    public String getVersion() {
        return version;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public String getLicenseURL() {
        return licenseURL;
    }
}
