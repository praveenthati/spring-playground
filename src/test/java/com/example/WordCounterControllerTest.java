package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WordCounterControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testCountWords() throws Exception{

        this.mvc.perform(post(String.format("/words/count"))
                .contentType(MediaType.TEXT_PLAIN)
                .content("A  brown cow jumps over a brown fox"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"A\":1,\"brown\":2,\"cow\":1,\"jumps\":1,\"over\":1,\"a\":1,\"fox\":1}"));


    }
}
