package com.technicaltest.services;

import com.technicaltest.business.MutantBusiness;
import com.technicaltest.models.DnaEntity;
import com.technicaltest.models.StatResponse;
import com.technicaltest.repository.MutantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service mutants
 *
 * @author luismiguelrodriguez
 */
@Service
public class MutantService implements MutanServiceInterface {

    private static final Logger log = LoggerFactory.getLogger(MutantService.class);

    final MutantRepository mutantRepository;

    public MutantService(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    @Override
    public Optional<Boolean> isMutan(String[] dna) {
        String stringDNA = Arrays.stream(dna).map(String::new).collect(Collectors.joining("-"));
        DnaEntity dnaDB = findById(stringDNA);

        if (dnaDB != null) {
            log.info("Dna found in db");
            return Optional.ofNullable(dnaDB.isMutant());
        }

        char[][] charArrays = Arrays.stream(dna).map(String::toCharArray).toArray(char[][]::new);
        boolean isMutant = new MutantBusiness().validateDNA(charArrays) > 1 ? true : false;
        saveDna(stringDNA, isMutant);
        return Optional.ofNullable(isMutant);
    }

    @Override
    public DnaEntity findById(String dna) {
        return mutantRepository.findByDna(dna);
    }

    @Override
    public void saveDna(String stringDNA, boolean isMutant) {
        DnaEntity dnaEntity = new DnaEntity();
        dnaEntity.setDna(stringDNA);
        dnaEntity.setMutant(isMutant);
        mutantRepository.save(dnaEntity);
    }

    @Override
    public Optional<StatResponse> getStat() {
        double mutants = countAllMutants();
        double humans = countAllHumans();
        double ratio = Math.max(mutants, 0) / Math.max(humans, 1);
        return Optional.ofNullable(new StatResponse(mutants, humans, ratio));
    }

    @Override
    public Long countAllMutants() {
        return mutantRepository.countAllMutants();
    }

    @Override
    public Long countAllHumans() {
        return mutantRepository.countAllHumans();
    }
}
