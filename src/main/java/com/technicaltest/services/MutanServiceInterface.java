package com.technicaltest.services;

import com.technicaltest.models.DnaEntity;
import com.technicaltest.models.StatResponse;

import java.util.Optional;

/**
 * Interface service
 *
 * @author luismiguelrodriguez
 */
public interface MutanServiceInterface {

    Optional<Boolean> isMutan(String[] dna);

    DnaEntity findById(String dna);

    void saveDna(String stringDNA, boolean isMutant);

    Optional<StatResponse> getStat();

    Long countAllMutants();

    Long countAllHumans();
}
