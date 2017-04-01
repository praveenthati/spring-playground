package com.example;

import com.fasterxml.jackson.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

class Flight {

    private Date departs;
    private List<Ticket> tickets;

    @JsonGetter("Tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTicket(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    @JsonGetter("Departs")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }
}

@JsonPropertyOrder({"passenger","price"})
class Ticket {

    @JsonProperty("Passenger")
    private Passenger passenger;
    @JsonProperty("Price")
    private double price;


    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Passenger {
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
