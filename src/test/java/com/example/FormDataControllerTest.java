package com.example;

import com.example.controller.FormDataController;
import com.example.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.*;

@WebMvcTest(FormDataController.class)
@RunWith(SpringRunner.class)
public class FormDataControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testEncodedFormURL() throws Exception {

        String content = String.valueOf(new Random().nextInt());

        this.mvc.perform(post("/comments")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("content",content)
                .param("author","Dwayne"))
                .andExpect(status().isOk())
                .andExpect(content().string("Dwayne said "+content));
    }

    @Test
    public void testInvalidArea() throws Exception {

        double radius = 2;
        double length = 2;
        double width = 3;
        String type = "Circl";

        this.mvc.perform(post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("radius", Double.toString(radius))
                .param("type",type))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }

    @Test
    public void testRectangleArea() throws Exception {

        double length = 2;
        double width = 3;
        String type = "rectangle";

        this.mvc.perform(post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("length", Double.toString(length))
                .param("width", Double.toString(width))
                .param("type",type))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("Area of a %fx%f rectangle is %f",length,width,length*width)));
    }

    @Test
    public void testCircleArea() throws Exception {

        double radius = 2;
        String type = "Circle";

        this.mvc.perform(post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("radius", Double.toString(radius))
                .param("type",type))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("Area of a circle with a radius of %f is %f",radius,Math.PI*radius*radius)));
    }

    @Test
    public void testMathArea() throws Exception {

        double radius = 2;
        double length = 2;
        double width = 3;


        assertEquals(String.format("Area of a %fx%f rectangle is %f", length, width, length*width),new MathService().area("rectangle",width,length,radius));
        assertNotEquals(radius*radius,new MathService().area("circle",width,length,radius));
        assertEquals(String.format("Area of a circle with a radius of %f is %f", radius, radius*radius*Math.PI),new MathService().area("circle",width,length,radius));
    }

}
