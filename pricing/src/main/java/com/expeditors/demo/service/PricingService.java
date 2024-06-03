package com.expeditors.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public Double evaluateTrack(int duration) {
        return duration * 0.2;
    }
}
