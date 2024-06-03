package com.expeditors.demo.controller;

import com.expeditors.demo.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class PriceController {

        @Autowired
        private PricingService pricingService;

        @GetMapping("/track/{duration}")
        public ResponseEntity evaluateTrack(@RequestBody String durationInSeconds){
            int duration = Integer.parseInt(durationInSeconds);
            Double price = this.pricingService.evaluateTrack(duration);
            return ResponseEntity.ok(price);
        }
    }
