package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    
    // default route
    @GetMapping(path = "/")
    public String fetchBands() {

        return "There is nothing here, please try again!";
    }
}
