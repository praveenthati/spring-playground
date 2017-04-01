package com.example;

import com.fasterxml.jackson.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/flights")
public class FlightsController {

    @GetMapping("/flight")
    public Flight getFlight() {

        Passenger passenger = new Passenger();
        passenger.setFirstName("Praveen");
        passenger.setLastName("Thati");

        Ticket ticket = new Ticket();
        ticket.setPrice(200);
        ticket.setPassenger(passenger);

        Flight flight = new Flight();

        Date date = new Date();


        flight.setDeparts(date);
        flight.setTicket(Arrays.asList(ticket));


        return flight;
    }

    @GetMapping("")
    public List<Flight> getFlights() {

        Passenger passenger = new Passenger();
        Ticket ticket = new Ticket();
        Date date = new Date();

        passenger.setFirstName("User");
        //passenger.setLastName("1");

        ticket.setPrice(200);
        ticket.setPassenger(passenger);

        Flight flight = new Flight();

        flight.setDeparts(date);
        flight.setTicket(Arrays.asList(ticket));

        Passenger passenger2 = new Passenger();
        Ticket ticket2 = new Ticket();

        passenger2.setFirstName("User2");
        //passenger.setLastName("2");


        ticket2.setPrice(100);
        ticket2.setPassenger(passenger2);

        Flight flight2 = new Flight();

        flight2.setDeparts(date);
        flight2.setTicket(Arrays.asList(ticket2));


        return Arrays.asList(flight,flight2);
    }

    @PostMapping(value = "/tickets/total")
    public Total computeTicketsTotal(@RequestBody Flight input){
        Total total = new Total();


        double sum = 0;
        for (int i=0;i<input.getTickets().size();i++){
            sum = sum + input.getTickets().get(i).getPrice();
        }

        total.setResult(sum);

        return total;
    }
}

