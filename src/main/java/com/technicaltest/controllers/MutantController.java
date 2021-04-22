package com.technicaltest.controllers;

import com.technicaltest.exception.MutantException;
import com.technicaltest.models.MutantRequest;
import com.technicaltest.models.StatResponse;
import com.technicaltest.services.MutantService;
import com.technicaltest.utilities.Validations;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mutant controller containing the operations of search mutants
 *
 * @author luismiguelrodriguez
 */
@RestController
public class MutantController {

    /**
     * Service mutants
     */
    final MutantService mutantService;

    /**
     * Service valitations dna
     */
    final Validations validations;

    public MutantController(MutantService mutantService, Validations validations) {
        this.mutantService = mutantService;
        this.validations = validations;
    }

    /**
     * Method to search mutants
     *
     * @param mutantRequest
     * @return HttpStatus.OK if mutants or HttpStatus.FORBIDDEN if not mutants
     */
    @ApiOperation(value = "Method to search mutants")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping(path = "/mutant", consumes = "application/json")
    public ResponseEntity<?> mutant(@RequestBody MutantRequest mutantRequest) {

        validations.validateDNA(mutantRequest);

        return mutantService.isMutan(mutantRequest.getDna())
                .map(data -> data
                        ? new ResponseEntity<>(HttpStatus.OK)
                        : new ResponseEntity<>(HttpStatus.FORBIDDEN))
                .orElseThrow(MutantException::new);

    }

    /**
     * Method to return statistics of the DNA checks
     *
     * @return statistics of the DNA checks
     */
    @ApiOperation(value = "Method to search mutants")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    @GetMapping(path = "/stats", consumes = "application/json")
    public ResponseEntity<StatResponse> stats() {
        return mutantService.getStat()
                .map(statResponse -> new ResponseEntity<>(statResponse, HttpStatus.OK))
                .orElseThrow(MutantException::new);
    }
}
