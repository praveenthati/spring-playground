package com.example.model;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("wordCount")
public class WordCountConfig {

    private Boolean caseSensitive;
    private Words words;
    private String delimiter;

    public Boolean getCaseSensitive(){
        return this.caseSensitive;
    }

    public Words getWords(){
        return this.words;
    }

    public void setWords(Words words){
        this.words = words;
    }

    public void setCaseSensitive(Boolean caseSensitive){
        this.caseSensitive = caseSensitive;
    }


    public static class Words{
        private List<String> skip;

        public void setSkip(List<String> skip){
            this.skip = skip;
        }

        public List<String> getSkip(){
            return this.skip;
        }
    }
}
