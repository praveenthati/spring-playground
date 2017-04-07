package com.example;


import com.example.model.WordCountConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


// please check WordCounterTestWithMockBean for WebMvcTest with MockBean
// can you let me know if WordCounter is implemented correctly ?

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "wordCount.caseSensitive=false",
        "wordCount.words.skip[0]=hello",
        "wordCount.words.skip[1]=this",
        "wordCount.words.skip[2]=is",
        "wordCount.words.skip[2]=bob"
})
public class WordCountConfigTestWithTestConfigSource {

    @Autowired
    WordCountConfig config;

    @Test
    public void testCount() throws Exception {

        assertEquals(false,config.getCaseSensitive());
        assertEquals(true, config.getWords().getSkip().contains("bob"));

    }

}



