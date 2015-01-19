package com.ricardovz.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ricardovz.api.dto.PropertyDTO;

/**
 * Repository interface that generates with spring implementation to perform the
 * CRUD operations
 * 
 * @author Ricardo Javier
 *
 */
public interface PropertiesRepository extends CrudRepository<PropertyDTO, Long> {

    /**
     * Find all the properties that match exactly the names provided
     * 
     * @param names
     * @return List<PropertyDTO>
     */
    @Query("SELECT property FROM #{#entityName} property WHERE property.name IN :names")
    List<PropertyDTO> findByNames(@Param("names") List<String> names);

    /**
     * Delete all the properties that match exactly the names provided
     * 
     * @param names
     */
    @Modifying
    @Query("DELETE FROM #{#entityName} property WHERE property.name IN :names")
    void deleteByNames(@Param("names") List<String> names);

}
