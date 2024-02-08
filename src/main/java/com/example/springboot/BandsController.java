package com.example.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BandsController {

    // fetch bands
    @GetMapping(path = "/fetch-bands")
    public JSONObject fetchBands() {

        JSONObject bands = {};
        return bands;
    }


    @PostMapping(path = "/update-bands")
    public JSONObject updateBands() {

        JSONObject bands = {};
        return bands;
    }


    @PostMapping(path = "/insert-bands")
    public JSONObject insertBands() {

        JSONObject bands = {};
        return bands;
    }
}