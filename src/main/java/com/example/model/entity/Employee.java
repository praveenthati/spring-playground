package com.example.model.entity;

import com.example.Views;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Secured.class)
    private Long id;
    @JsonView(Views.Secured.class)
    private String name;

    public Employee(){

    }

    public Employee(String name, int salary, Long managerId) {
        this.name = name;
        this.salary = salary;
        this.managerId = managerId;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    private int salary;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @JsonView(Views.Secured.class)
    private Long managerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
