package com.technicaltest.utils;

import com.technicaltest.models.DnaEntity;
import com.technicaltest.models.MutantRequest;
import com.technicaltest.models.StatResponse;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestEntities {

    public static MutantRequest mockMutantRequestOK() {
        MutantRequest mutantRequest = new MutantRequest();
        String[] dna = new String[]{
                "ATGCGA",
                "AAGTGC",
                "TTATGT",
                "AGAAGG",
                "TCCCTA",
                "TCACTG"
        };
        mutantRequest.setDna(dna);
        return mutantRequest;
    }

    public static MutantRequest mockMutantRequestForbidden() {
        MutantRequest mutantRequest = new MutantRequest();
        String[] dna = new String[]{
                "ATGCGA",
                "AAGTAC",
                "TTATGT",
                "AGAAGG",
                "TCCCTA",
                "TCACTG"
        };
        mutantRequest.setDna(dna);
        return mutantRequest;
    }

    public static StatResponse mockStatResponse() {
        StatResponse statResponse = new StatResponse();
        statResponse.setCountMutant(2L);
        statResponse.setCountHuman(4L);
        statResponse.setRatio(0.1);
        return statResponse;
    }

    public static MutantRequest mockMutantRequestInvalidLetter() {
        MutantRequest mutantRequest = new MutantRequest();
        String[] dna = new String[]{
                "ATGCGA",
                "AAGTGC",
                "TTATGT",
                "AGAXGG",
                "TCCCTA",
                "TCACTG"
        };
        mutantRequest.setDna(dna);
        return mutantRequest;
    }

    public static MutantRequest mockMutantRequestEmpty() {
        MutantRequest mutantRequest = new MutantRequest();
        String[] dna = new String[]{};
        mutantRequest.setDna(dna);
        return mutantRequest;
    }

    public static MutantRequest mockMutantRequestNotSymmetric() {
        MutantRequest mutantRequest = new MutantRequest();
        String[] dna = new String[]{
                "ATGCGA",
                "AAGTGC",
                "TTATGT",
                "AGAXGGG",
                "TCCCTA",
                "TCACTG"
        };
        mutantRequest.setDna(dna);
        return mutantRequest;
    }

    public static DnaEntity mockDnaEntityMutant() {
        DnaEntity dnaEntity = new DnaEntity();
        String stringDNA = Arrays.stream(mockMutantRequestOK().getDna()).map(String::new).collect(Collectors.joining("-"));
        dnaEntity.setId(1l);
        dnaEntity.setDna(stringDNA);
        dnaEntity.setMutant(true);
        return dnaEntity;
    }

    public static DnaEntity mockDnaEntityHumans() {
        DnaEntity dnaEntity = new DnaEntity();
        String stringDNA = Arrays.stream(mockMutantRequestForbidden().getDna()).map(String::new).collect(Collectors.joining("-"));
        dnaEntity.setId(2l);
        dnaEntity.setDna(stringDNA);
        dnaEntity.setMutant(false);
        return dnaEntity;
    }
}
