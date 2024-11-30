package com.github.vladimirpokhodnya.tasktracker.controller;

import com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LoggingException;
import com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LoggingExecution;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @LoggingException
    @LoggingExecution
    @GetMapping("/exception")
    public String triggerException() {
        throw new RuntimeException("This is a test exception");
    }

}