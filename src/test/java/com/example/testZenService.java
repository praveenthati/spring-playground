package com.example;

import com.example.service.ZenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(ZenService.class)
public class testZenService {

    @Autowired
    ZenService service;

    @Test
    public void testGetMessage(){
        assertEquals(true,service.getMessage().length() > 0);
    }
}
