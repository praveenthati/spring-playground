package com.example.controller;


import com.example.service.MathService;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.lang.String;


@RestController
public class EndpointsController {

    private MathService service;

    @GetMapping("/")
    public String getIndex()
    {
        return  "Get Index route";
    }

    @GetMapping("/vehicles")
    public String getVehicles(@RequestParam String year,@RequestParam String doors)
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

    @GetMapping("/math/calculate")
    public String calculate(@RequestParam(required = false, defaultValue = "add") String operation,@RequestParam int x,@RequestParam int y)
    {
        service = new MathService();

        return service.calculate(operation,x,y);

    }

    @PostMapping("/math/sum")
    public String sum(@RequestParam MultiValueMap<String, String> map){

        if(map != null) {

            service = new MathService();

            return service.sum(map.get("n"));
        }

        return  "no args";


    }

}
