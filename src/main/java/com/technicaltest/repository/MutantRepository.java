package com.technicaltest.repository;

import com.technicaltest.models.DnaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Class Repository to persistence
 *
 * @author luismiguelrodriguez
 */
public interface MutantRepository extends CrudRepository<DnaEntity, Long> {

    DnaEntity findByDna(String dna);

    @Query(value = "select count(*) from dnas m where m.mutant = true", nativeQuery = true)
    Long countAllMutants();

    @Query(value = "select count(*) from dnas m where m.mutant = false", nativeQuery = true)
    Long countAllHumans();

}
