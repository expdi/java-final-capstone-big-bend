package com.pricing.Service;

import com.pricing.model.Track;
import com.pricing.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PricingService {

    @Autowired
    private TrackRepository trackRepository;

    public Double evaluateTrack(UUID uuid){
        Track track = trackRepository.getTrackByUuid(uuid);
        if(track == null){
            track = new Track(uuid);
            track = trackRepository.save(track);
        }
        Double value = track.evaluate();
        trackRepository.save(track);
        return value;
    }

    public List<Track> evaluationList(){
        return this.trackRepository.findAll();
    }
}
