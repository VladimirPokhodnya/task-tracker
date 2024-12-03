package com.github.vladimirpokhodnya.tasktracker.controller;

import com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LogException;
import com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LogExecution;
import com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LogTracking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @LogExecution
    @LogException
    @LogTracking
    @GetMapping("/exception")
    public String triggerException() throws InterruptedException {
        Thread.sleep(5);
        throw new RuntimeException("This is a test exception");
    }

}