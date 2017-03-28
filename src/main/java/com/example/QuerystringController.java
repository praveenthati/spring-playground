package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class QuerystringController {

    @GetMapping("/individual-example")
    public String getIndividualParams(@RequestParam String filter) {
        return String.format("Filter is : %s", filter);
    }
}
