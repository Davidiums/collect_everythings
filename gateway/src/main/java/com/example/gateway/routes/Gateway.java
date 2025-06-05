package com.example.gateway.routes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Gateway {
    @GetMapping("hello")
    public String helloGateway() {
        return "Hello Gateway!";
    }
}
