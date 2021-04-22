package com.technicaltest.repository;

import com.technicaltest.models.DnaEntity;
import org.springframework.data.repository.CrudRepository;

public interface MutantRepository extends CrudRepository<DnaEntity, Long> {

   // @Query("select m from dnas m where m.dna = :dna")
      DnaEntity findByDna(String dna);

//    @Query("select count(m) from dnas m where m.mutant = true")
//    Long countAllMutants();
//
//    @Query("select count(m) from dnas m where m.mutant = false")
//    Long countAllHumans();

}
