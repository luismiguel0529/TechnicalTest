package com.technicaltest.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity for persistence
 *
 * @author luismiguelrodriguez
 */
@Entity
@Table(name = "dnas")
public class DnaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotNull
    @Column(name = "mutant")
    boolean mutant;

    @NotNull
    @Column(name = "dna")
    String dna;

    public DnaEntity() {
    }

    public DnaEntity(Long id, boolean mutant, String dna) {
        this.id = id;
        this.mutant = mutant;
        this.dna = dna;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }
}
