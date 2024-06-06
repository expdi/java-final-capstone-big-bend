package com.pricing.Controller;

import com.pricing.Service.PricingService;
import com.pricing.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/pricing")
public class PricingController {

    @Autowired
    private PricingService pricingService;
    @GetMapping("/{uuid}")
    public Double getPricing(@PathVariable String uuid){
        return this.pricingService.evaluateTrack(UUID.fromString(uuid));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Track>> getTrackList(){
        List<Track> tracks = this.pricingService.evaluationList();
        return ResponseEntity.ok().body(tracks);
    }

    // PUT action not yet implemented, this is just to test the auth filter
    @PutMapping
    public String testPut(){
        return "test";
    }

}
