package com.technicaltest.service;


import com.technicaltest.models.DnaEntity;
import com.technicaltest.models.MutantRequest;
import com.technicaltest.models.StatResponse;
import com.technicaltest.repository.MutantRepository;
import com.technicaltest.services.MutantService;
import com.technicaltest.utils.TestEntities;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class MutanServiceTest {

    @Autowired
    private MutantRepository mutantRepository;

    private MutantService mutantService;

    private static MutantRequest mutantRequest;
    private static DnaEntity dnaEntityMutant;
    private static DnaEntity dnaEntityHumans;

    @BeforeEach
    void setUp() {
        mutantService = new MutantService(mutantRepository);
        mutantRequest = TestEntities.mockMutantRequestOK();
        dnaEntityMutant = TestEntities.mockDnaEntityMutant();
        dnaEntityHumans = TestEntities.mockDnaEntityHumans();
    }

    @Test
    @DisplayName("Test search dna in service and found in db")
    void whenSearchDnaInServiceAndFoundInDB() {
        mutantRepository.save(dnaEntityMutant);
        Optional<Boolean> b = mutantService.isMutan(mutantRequest.getDna());
        Assert.assertEquals(b,Optional.of(true));
    }

    @Test
    @DisplayName("Test search dna in service and not found in db")
    void whenSearchDnaInServiceAndNotFoundInDB() {
        Optional<Boolean> b = mutantService.isMutan(mutantRequest.getDna());
        Assert.assertEquals(b,Optional.of(true));
    }

    @Test
    @DisplayName("Test search method getStats,return StatsResponse")
    void whenCallMethodGetStatsReturnStatsResponse() {
        mutantRepository.save(dnaEntityMutant);
        mutantRepository.save(dnaEntityHumans);
        Optional<StatResponse> statResponse = mutantService.getStat();


        Assert.assertEquals(Optional.of(mutantService.countAllHumans()),statResponse.map(countHuman -> countHuman.getCountHuman()).map(Double::longValue));
        Assert.assertEquals(Optional.of(mutantService.countAllMutants()),statResponse.map(countMutant -> countMutant.getCountMutant()).map(Double::longValue));
    }
}
