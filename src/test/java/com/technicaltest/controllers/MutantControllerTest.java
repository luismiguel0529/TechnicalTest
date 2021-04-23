package com.technicaltest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.exception.MutantException;
import com.technicaltest.models.MutantRequest;
import com.technicaltest.models.StatResponse;
import com.technicaltest.services.MutantService;
import com.technicaltest.utilities.Validations;
import com.technicaltest.utils.TestEntities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MutantController.class)
public class MutantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MutantService mockMutantService;

    @MockBean
    private Validations validations;

    private static MutantRequest mutantRequestOK;
    private static MutantRequest mutantRequestForbidden;
    private static StatResponse statResponseOK;

    private static final String MUTANTS_PATH = "/mutant";
    private static final String STATS_PATH = "/stats";

    @BeforeAll
    static void setUp() {
        mutantRequestOK = TestEntities.mockMutantRequestOK();
        mutantRequestForbidden = TestEntities.mockMutantRequestForbidden();
        statResponseOK = TestEntities.mockStatResponse();
    }

    @Test
    @DisplayName("Test search dna is mutant,return status 200 ")
    void whenSearchDNAThenReturnStatusOK() throws Exception {
        given(mockMutantService.isMutan(mutantRequestOK.getDna())).willReturn(Optional.of(true));
        String json = new ObjectMapper().writeValueAsString(mutantRequestOK);
        String url = MUTANTS_PATH;
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test search dna is not mutant,return status 403 ")
    void whenSearchDNAThenReturnStatusForbidden() throws Exception {
        given(mockMutantService.isMutan(mutantRequestForbidden.getDna())).willReturn(Optional.of(false));
        String json = new ObjectMapper().writeValueAsString(mutantRequestForbidden);
        String url = MUTANTS_PATH;
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test search dna,return status 500 ")
    void whenSearchDNAThenReturnStatusInternalServerError() throws Exception {
        given(mockMutantService.isMutan(mutantRequestOK.getDna())).willThrow(new MutantException());
        String json = new ObjectMapper().writeValueAsString(mutantRequestOK);
        String url = MUTANTS_PATH;
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Test search stats dna,return status 200 ")
    void whenSearchStatsThenReturnStatus200() throws Exception {
        given(mockMutantService.getStat()).willReturn(Optional.ofNullable(statResponseOK));
        String url = STATS_PATH;
        mvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

