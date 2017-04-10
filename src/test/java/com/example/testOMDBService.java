package com.example;

import com.example.service.OMDBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testOMDBService {

    @Autowired
    private OMDBService service;

    @Test
    public void testGetMovies(){

        assertThat(service.getMovies("harry").size() == 10);
    }
}
