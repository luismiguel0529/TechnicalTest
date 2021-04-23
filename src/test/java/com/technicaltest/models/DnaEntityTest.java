package com.technicaltest.models;

import com.technicaltest.utils.TestEntities;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class DnaEntityTest {

    @Test
    @DisplayName("Test Dto DnaEntity")
    void testDtoDnaEntity(){
        String stringDNA = Arrays.stream(TestEntities.mockMutantRequestForbidden().getDna()).map(String::new).collect(Collectors.joining("-"));
        DnaEntity dnaEntity = new DnaEntity(1L,true,stringDNA);
        Assert.assertEquals(Optional.of(1L), Optional.of(dnaEntity.getId()));
        Assert.assertEquals(true,dnaEntity.getMutant());
        Assert.assertEquals(stringDNA,dnaEntity.getDna());
    }
}
