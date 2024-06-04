package com.pricing.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/pricing")
public class PricingController {
    private double lowerPricing=1;
    private double higherPricing=100;
    @GetMapping("/getPricing")
    public double getPricing(){
        double pricing = ThreadLocalRandom.current().nextDouble(lowerPricing,higherPricing);
        return pricing;
    }

    // PUT action not yet implemented, this is just to test the auth filter
    @PutMapping
    public String testPut(){
        return "test";
    }

}
