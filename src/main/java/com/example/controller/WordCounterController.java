package com.example.controller;

import com.example.service.WordCounter;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WordCounterController {

    WordCounter Service;

    public WordCounterController(WordCounter service){
        this.Service = service;
    }

    @PostMapping("/words/count")
    public Map<String, Integer> countWords(@RequestBody String raw){
        return this.Service.count(raw);
    }
}
