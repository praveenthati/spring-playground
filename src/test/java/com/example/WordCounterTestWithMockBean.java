package com.example;

import com.example.controller.WordCounterController;
import com.example.service.WordCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordCounterController.class)
@AutoConfigureMockMvc(secure=false)
public class WordCounterTestWithMockBean {

    String input = "A  brown cow jumps over a brown fox";
    Map<String, Integer> expectedResult = new LinkedHashMap<>();

    @Autowired
    MockMvc mvc;

    @MockBean
    WordCounter service;

    @Before
    public void setUpExpectedResult(){

        expectedResult.put("A",1);
        expectedResult.put("brown",2);
        expectedResult.put("cow",1);
        expectedResult.put("jumps",1);
        expectedResult.put("over",1);
        expectedResult.put("a",1);
        expectedResult.put("fox",1);

        //given(service.count(input)).willReturn(expectedResult);
        when(service.count(input)).thenReturn(expectedResult);

    }

    @Test
    public void testCount() throws Exception{

        this.mvc.perform(post("/words/count")
                .contentType(MediaType.TEXT_PLAIN)
                .content(input))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"A\":1,\"brown\":2,\"cow\":1,\"jumps\":1,\"over\":1,\"a\":1,\"fox\":1}"));


    }
}
