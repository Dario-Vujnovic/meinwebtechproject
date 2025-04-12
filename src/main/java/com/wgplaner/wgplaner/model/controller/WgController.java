package com.wgplaner.wgplaner.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WgController {

    @GetMapping("/hello")
    public String hello() {
        return "Willkommen beim WG-Planer!";
    }
}

