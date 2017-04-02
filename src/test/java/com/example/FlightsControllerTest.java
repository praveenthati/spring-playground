package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cache.support.NullValue;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class FlightsControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetFlight() throws Exception{

        this.mvc.perform(get("/flights/flight"))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName",is("Praveen")));
    }

    @Test
    public void testGetFlights() throws Exception{

        this.mvc.perform(get("/flights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName",is("User")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName").doesNotExist());
    }

    @Test
    public void testComputeTicketsTotalV1() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights//tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\": [ {\"price\": 201},{\"price\": 150}]}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":351.0}"));
    }
}
