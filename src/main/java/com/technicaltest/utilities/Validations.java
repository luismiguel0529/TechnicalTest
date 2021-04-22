package com.technicaltest.utilities;

import com.technicaltest.exception.MutantEmptyException;
import com.technicaltest.exception.MutantInvalidLetterException;
import com.technicaltest.exception.MutantNotSymmetricException;
import com.technicaltest.models.MutantRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Class validations dna
 *
 * @author luismiguelrodriguez
 */
@Component
public class Validations {

    private static final Logger log = LoggerFactory.getLogger(Validations.class);

    public void validateDNA(MutantRequest mutantRequest) {
        String[] dna = mutantRequest.getDna();
        if ((dna == null) || (dna.length == 0)) {
            log.info("Failed DNA");
            throw new MutantEmptyException("Failed DNA");
        }

        Arrays.asList(dna).forEach(word -> {
            if (word.length() != dna.length) {
                log.info("The data is not symmetric");
                throw new MutantNotSymmetricException("The data is not symmetric");
            }
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                if (!Arrays.asList('A', 'T', 'C', 'G').contains(letter)) {
                    log.info("The data has invalid letters");
                    throw new MutantInvalidLetterException("The data has invalid letters");
                }
            }
        });
    }
}
