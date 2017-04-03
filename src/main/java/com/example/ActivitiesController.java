package com.example;


import ch.qos.logback.core.status.StatusUtil;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;


class Views {
    interface CompactView {};
    interface DetailView extends CompactView {};
}

//

@RestController
public class ActivitiesController {

    @PostMapping(value = "/activities/simplify", produces = "application/vnd.galvanize.detailed+json")
    @JsonView(Views.DetailView.class)
    public Activities SimplifyV1(@RequestBody Activities activities) {
        return null;
    }

    @PostMapping(value = "/activities/simplify", produces = "application/vnd.galvanize.compact+json")
    @JsonView(Views.CompactView.class)
    public Activities SimplifyV2(@RequestBody Activities activiti) {
        return null;
    }
}

class Activities {
    private List<Activity> activties;

    public void setActivities(List<Activity> activities) {
        this.activties = activities;
    }

    public List<Activity> getActivities() {
        return activties;
    }
}

class Activity {

    private User user;
    private Status status;

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

class User {
    private int id;
    private String username;
    private List<Email> emails;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

class Email {
    private int id;
    private String address;
    private boolean primary;

    public void setId(int id) {
        this.id = id;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getPrimary() {
        return primary;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }
}

class Status {
    private String text;
    private Date date;

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
}
