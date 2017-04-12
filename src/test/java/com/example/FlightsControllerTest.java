package com.example;

import com.example.controller.FlightsController;
import com.example.model.Flight;
import com.example.model.Ticket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(FlightsController.class)
@AutoConfigureMockMvc(secure=false)
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

    private Gson gson = new GsonBuilder().create();

    @Test
    public void testComputeTicketsTotalV2() throws Exception {
        Flight flight = new Flight();
        Ticket ticket1 = new Ticket();
        ticket1.setPrice(100);

        Ticket ticke2 = new Ticket();
        ticke2.setPrice(100);

        flight.setTicket(Arrays.asList(ticke2,ticket1));

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(flight));

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":200.0}"));
    }

//    @Test
//    public void testComputeTicketsTotalV3() throws Exception {
//        String json = getJSON("/data.json");
//
//        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json);
//
//        this.mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().string("{\"result\":300.0}"));
//    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }


}
