package com.wgplaner.wgplaner.model.controller;

import com.wgplaner.wgplaner.model.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WgController {

    @GetMapping("/todos")
    public List<Task> testRoute() {
        return List.of(
                new Task(1, "This is a test todo item 1.", false),
                new Task(2, "This is a test todo item 2.", false),
                new Task(3, "This is a test todo item 3.", false)
        );

    }
}

