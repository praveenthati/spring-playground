package com.example.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Praveen Thati on 4/1/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Passenger {

    private String firstName;

    private String lastName;

    @JsonGetter("LastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonGetter("FirstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
