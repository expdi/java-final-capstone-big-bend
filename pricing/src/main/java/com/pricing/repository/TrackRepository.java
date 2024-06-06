package com.pricing.repository;

import com.pricing.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackRepository extends JpaRepository<Track, Long> {

    public Track getTrackByUuid(UUID uuid);

}
