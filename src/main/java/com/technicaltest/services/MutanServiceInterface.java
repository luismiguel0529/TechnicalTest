package com.technicaltest.services;

import com.technicaltest.models.DnaEntity;

import java.util.Optional;

public interface MutanServiceInterface {

    Optional<Boolean> isMutan(String[] dna);

    DnaEntity findById(String dna);

    void saveDna(String stringDNA, boolean isMutant);

}
