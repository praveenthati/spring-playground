package com.example.controller;

import com.example.service.OMDBService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.timestamp.TSRequest;

import java.util.List;

@RestController
public class OMDBController {

    OMDBService service;

    public OMDBController(OMDBService service){
        this.service = service;
    }

    @GetMapping("/movies")
    public List<OMDBService.Movies> getMovies(@RequestParam String q){
        return service.getMovies(q);
    }
}
