package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointsController {

    @GetMapping("/")
    public String getIndex()
    {
        return  "Get Index route";
    }


    @PostMapping("/tasks")
    public String createTask()
    {
        return  "Post Task route";
    }

    @GetMapping("/math/pi")
    public String getPI()
    {
        return  "3.141592653589793";
    }
}
