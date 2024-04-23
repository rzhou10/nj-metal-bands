package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.interfaces.Band;


@RestController
public class BandsController {

	@GetMapping("/fetch-bands")
	public String fetchBands(@RequestBody String sortOrder) {
		return "Fetching bands!";
	}

	@PostMapping ("/insert-bands")
	public String insertBands(@RequestBody Band entity) {
		return "Fetching bands!";
	}
    
}
