package com.example.service;

import javassist.expr.Cast;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class WordCounter {

    public Map<String, Integer> count(String input){

        if(input == null)
            return null;

        Map<String,Integer> wordCounter = new LinkedHashMap<>();

        for(String word : input.trim().replaceAll("[^\\w\\s]", "").split("\\s+"))
        {
            if(!wordCounter.containsKey(word))
            {
                wordCounter.put(word,1);

            }
            else
            {
                wordCounter.put(word,wordCounter.get(word)+1);
            }
        }

        return wordCounter;

    }
}
