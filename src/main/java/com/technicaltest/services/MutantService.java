package com.technicaltest.services;

import com.technicaltest.business.MutantBusiness;
import com.technicaltest.models.DnaEntity;
import com.technicaltest.repository.MutantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

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

        if (dnaDB != null){
            log.info("Dna found in db");
            return Optional.ofNullable(dnaDB.isMutant());
        }

        char[][] charArrays = Arrays.stream(dna).map(String::toCharArray).toArray(char[][]::new);
        int sequence = new MutantBusiness().validateDNA(charArrays);
        boolean isMutant = sequence > 1 ? true: false;
        saveDna(stringDNA,isMutant);
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


}
