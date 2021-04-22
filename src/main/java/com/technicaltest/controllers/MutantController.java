package com.technicaltest.controllers;

import com.technicaltest.exception.MutantException;
import com.technicaltest.models.MutantRequest;
import com.technicaltest.services.MutantService;
import com.technicaltest.utilities.Validations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MutantController {

    final MutantService mutantService;

    final Validations validations;

    public MutantController(MutantService mutantService, Validations validations) {
        this.mutantService = mutantService;
        this.validations = validations;
    }

    @PostMapping(path = "/mutant", consumes = "application/json")
    public ResponseEntity<?> mutant(@RequestBody MutantRequest mutantRequest) {

        validations.validateDNA(mutantRequest);

        return mutantService.isMutan(mutantRequest.getDna())
                .map(data -> data
                        ? new ResponseEntity<>(HttpStatus.OK)
                        : new ResponseEntity<>(HttpStatus.FORBIDDEN))
                .orElseThrow(MutantException::new);

    }
}
