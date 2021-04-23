package com.technicaltest.utilities;

import com.technicaltest.exception.MutantValidationException;
import com.technicaltest.exception.MutantEmptyException;
import com.technicaltest.exception.MutantInvalidLetterException;
import com.technicaltest.exception.MutantNotSymmetricException;
import com.technicaltest.models.MutantRequest;
import com.technicaltest.utils.TestEntities;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidationsTest {

    @InjectMocks
    private Validations validations;

    private static MutantRequest mutantRequestInvalidLetter;
    private static MutantRequest mutantRequestEmpty;
    private static MutantRequest mutantRequestNotSymmetric;

    @BeforeAll
    static void setUp() {
        mutantRequestInvalidLetter = TestEntities.mockMutantRequestInvalidLetter();
        mutantRequestEmpty = TestEntities.mockMutantRequestEmpty();
        mutantRequestNotSymmetric = TestEntities.mockMutantRequestNotSymmetric();
    }

    @Test
    @DisplayName("Test validation dna and dna is null,return status 400 ")
    void whenSearchDNAThenReturnMutantInvalidLetterException() {
        Assert.assertThrows(MutantInvalidLetterException.class,
                () -> validations.validateDNA(mutantRequestInvalidLetter));
    }

    @Test
    @DisplayName("Test validation dna and dna is empty,return status 400 ")
    void whenSearchDNAThenReturnMutantEmptyException() {
        Assert.assertThrows(MutantEmptyException.class,
                () -> validations.validateDNA(mutantRequestEmpty));
    }

    @Test
    @DisplayName("Test validation dna and dna is not symmetric,return status 400 ")
    void whenSearchDNAThenReturnMutantNotSymmetricException() {
        Assert.assertThrows(MutantNotSymmetricException.class,
                () -> validations.validateDNA(mutantRequestNotSymmetric));
    }

    @Test
    @DisplayName("Test validation dna and dna is not symmetric,return status 400 ")
    void whenSearchDNAThenReturnMutantValidationException() {
        Assert.assertThrows(MutantValidationException.class,
                () -> validations.validateDNA(mutantRequestNotSymmetric));
    }
}
