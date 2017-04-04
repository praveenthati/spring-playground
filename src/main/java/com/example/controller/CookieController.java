package com.example.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @GetMapping("/header")
    public String getHeader(@RequestHeader String host) {
        return host;
    }

    @GetMapping("/cookie")
    public String getCookie(@CookieValue(name = "foo") String cookie) {
        return cookie;
    }
}
