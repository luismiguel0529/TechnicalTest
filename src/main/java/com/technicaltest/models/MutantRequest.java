package com.technicaltest.models;

/**
 * Class DTO request
 *
 * @author luismiguelrodriguez
 */
public class MutantRequest {

    private String[] dna;

    public MutantRequest() {
        super();
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

}
