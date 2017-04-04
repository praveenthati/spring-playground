package com.example.model;

import com.example.model.Passenger;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Praveen Thati on 4/1/17.
 */
@JsonPropertyOrder({"passenger","price"})
public class Ticket {


    private Passenger passenger;

    private double price;

    @JsonGetter("Passenger")
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @JsonProperty("Price")
    public double getPrice() {
        return price;
    }

    @JsonSetter("price")
    public void setPrice(double price) {
        this.price = price;
    }
}
