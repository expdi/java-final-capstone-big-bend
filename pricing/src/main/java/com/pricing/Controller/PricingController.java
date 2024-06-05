package com.pricing.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
