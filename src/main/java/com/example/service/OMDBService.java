package com.example.service;

import com.example.model.OMDBConfig;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OMDBService {

   private final OMDBConfig config;
   private final RestTemplate template = new RestTemplate();

    public OMDBService(OMDBConfig config){
        this.config = config;
    }

    public List<Movies> getMovies(String movieName){
        return this.template.getForObject(
                String.format("%s/?s={movieName}",config.getMoviesEndPoint()),
                MoviesResult.class,
                movieName

        ).getSearch();
    }

    public RestTemplate getRestTemplate(){
        return  this.template;
    }

    public static class MoviesResult{
        private List<Movies> search;
        private String totalResults;
        private String response;

        public List<Movies> getSearch(){
            return this.search;
        }

        public String getTotalResults(){
            return this.totalResults;
        }

        public String getResponse(){
            return this.response;
        }

        @JsonSetter("Search")
        public void setSearch(List<Movies> Search){
            this.search = Search;
        }

        public void setTotalResults(String totalResults){
            this.totalResults = totalResults;
        }

        @JsonSetter("Response")
        public void setResponse(String response){
            this.response = response;
        }
    }

    public static class Movies{

        private String title;

        @JsonGetter("title")
        public String getTitle() {
            return title;
        }

        @JsonSetter("Title")
        public void setTitle(String title) {
            this.title = title;
        }



        private String year;

        @JsonGetter("year")
        public String getYear() {
            return year;
        }
        @JsonSetter("Year")
        public void setYear(String year) {
            this.year = year;
        }


        private String type;
        @JsonGetter("type")
        public String getType() {
            return type;
        }
        @JsonSetter("Type")
        public void setType(String type) {
            this.type = type;
        }

        private String poster;
        @JsonGetter("poster")
        public String getPoster() {
            return poster;
        }
        @JsonSetter("Poster")
        public void setPoster(String poster) {
            this.poster = poster;
        }




    }
}
