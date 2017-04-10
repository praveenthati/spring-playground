package com.example.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("omdb")
public class OMDBConfig {

    private String moviesEndPoint;

    public String getMoviesEndPoint(){
        return this.moviesEndPoint;
    }

    public void setMoviesEndPoint(String moviesEndPoint){
        this.moviesEndPoint = moviesEndPoint;
    }
}
