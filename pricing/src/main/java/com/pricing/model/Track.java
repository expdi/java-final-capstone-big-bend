package com.pricing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Track {

    @Id
    @GeneratedValue
    private Long id;

    public UUID uuid;
    private Long evaluations;
    private Double rate;

    public Track(UUID uuid) {
        this.uuid = uuid;
        this.rate = 1.2;
        this.evaluations = 0L;
    }

    public Track() {

    }


    public Long getId() {
        return id;
    }



    public Long getEvaluations() {
        return evaluations;
    }

    public Double getRate() {
        return rate;
    }

    public double evaluate() {
        this.evaluations++;

        return evaluations * getRate();
    }
}
