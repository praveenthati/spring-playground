package com.example;

import org.junit.Test;

import static org.junit.Assert.*;


public class PagesControllerTest {

    @Test
    public void helloTest()
    {
        PagesController controller = new PagesController();
        assertEquals("Hello World!",controller.hello());
    }
}