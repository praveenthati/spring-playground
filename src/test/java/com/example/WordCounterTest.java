package com.example;

import com.example.controller.WordCounterController;
import com.example.service.WordCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// please check WordCounterTestWithMockBean for WebMvcTest with MockBean
// can you let me know if WordCounter is implemented correctly ?

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure=false)
public class WordCounterTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    WordCounter service;

    Map<String, Integer> expectedResult = new LinkedHashMap<>();
    String input = "A  brown cow jumps over a brown fox";

    @Before
    public void setUpExpectedResult(){

        expectedResult.put("brown",2);
        expectedResult.put("cow",1);
        expectedResult.put("jumps",1);
        expectedResult.put("over",1);
        expectedResult.put("fox",1);

    }

    @Test
    public void testCount() throws Exception {

        assertEquals(expectedResult,service.count(input));
    }


    @Test
    public void testCountWords() throws Exception{

        this.mvc.perform(post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content(input))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"brown\":2,\"cow\":1,\"jumps\":1,\"over\":1,\"fox\":1}"));


    }

}



