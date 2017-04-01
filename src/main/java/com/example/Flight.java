package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;
import java.util.List;

/**
 * Created by Praveen Thati on 4/1/17.
 */
class Flight {

    private Date departs;
    private List<Ticket> tickets;

    @JsonGetter("Tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    @JsonSetter("tickets")
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
