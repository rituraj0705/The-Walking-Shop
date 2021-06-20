package com.theWalkingshoe.coudgateway.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudGateWayController {
    @GetMapping("/userServiceFallback")
    public String userServiceFallbackMethod() {
        return "Taking Longer than expected to load the user service.";
    }

    @GetMapping("/projectServiceFallback")
    public String projectServiceFallbackMethod() {
        return "Taking Longer than expected to load the user service.";
    }
}