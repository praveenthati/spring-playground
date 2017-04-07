package com.example.service;

import com.example.model.WordCountConfig;
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

@Service
public class WordCounter {

    WordCountConfig WordCountConfig;

    public WordCounter(WordCountConfig wordCountConfig)
    {
        this.WordCountConfig = wordCountConfig;
    }

    public Map<String, Integer> count(String input){

        if(input == null)
            return null;

        if(!this.WordCountConfig.getCaseSensitive()){
            input = input.toLowerCase();
        }

        Map<String,Integer> wordCounter = new LinkedHashMap<>();

        for(String word : input.trim().replaceAll("[^\\w\\s]", "").split("\\s+"))
        {
            // do you want us to consider case for excluded words too or will they be in small case ?

            if(!this.WordCountConfig.getWords().getSkip().contains(word)) {
                if (!wordCounter.containsKey(word)) {
                    wordCounter.put(word, 1);

                } else {
                    wordCounter.put(word, wordCounter.get(word) + 1);
                }
            }
        }

        return wordCounter;

    }
}

