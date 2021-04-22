package com.technicaltest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class DTO response
 *
 * @author luismiguelrodriguez
 */
public class StatResponse {

    @JsonProperty("count_mutant_dna")
    private double countMutant;

    @JsonProperty("count_human_dna")
    private double countHuman;

    private double ratio;

    public StatResponse(double countMutant, double countHuman, double ratio) {
        this.countMutant = countMutant;
        this.countHuman = countHuman;
        this.ratio = ratio;
    }

    public double getCountMutant() {
        return countMutant;
    }

    public void setCountMutant(Long countMutant) {
        this.countMutant = countMutant;
    }

    public double getCountHuman() {
        return countHuman;
    }

    public void setCountHuman(Long countHuman) {
        this.countHuman = countHuman;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
