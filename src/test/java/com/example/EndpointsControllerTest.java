package com.example;

import com.example.controller.EndpointsController;
import com.example.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(EndpointsController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure=false)
public class EndpointsControllerTest {

    @Autowired
    private
    MockMvc mvc;

    @Test
    public void testgetPI() throws Exception{

        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));


    }

    @Test
    public void testgetVehicles() throws Exception {
        this.mvc.perform(get("/vehicles?year=1987&doors=2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testcalculate() throws Exception {

        this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
        .andExpect(content().string("4 + 6 = 10"));

        this.mvc.perform(get("/math/calculate?x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));

        this.mvc.perform(get("/math/calculate?operation=multiply&x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 * 5 = 150"));

        this.mvc.perform(get("/math/calculate?operation=subtract&x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 - 5 = 25"));

        this.mvc.perform(get("/math/calculate?operation=divide&x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));

        this.mvc.perform(get("/math/calculate?operation=divide&x=30&y=0"))
                .andExpect(status().isOk())
                .andExpect(content().string("y cannot be zero"));
    }

    @Test
    public void testsum() throws Exception {
        this.mvc.perform(post("/math/sum?n=4&n=5&n=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testMathService() throws Exception {

        MathService service = new MathService();

        List<String> valuesList = new ArrayList<>();
        assertEquals(service.sum(valuesList),"0");

        valuesList.add("2");
        valuesList.add("2");
        valuesList.add("2000");

        assertEquals(service.sum(valuesList),"2 + 2 + 2000 = 2004");
        assertEquals(service.calculate("add", 2,2),"2 + 2 = 4");
        assertEquals(service.calculate("divide", 2,2),"2 / 2 = 1");
        assertEquals(service.calculate("subtract", 2,2),"2 - 2 = 0");
        assertEquals(service.calculate("multiply", 2,2),"2 * 2 = 4");

    }
}
