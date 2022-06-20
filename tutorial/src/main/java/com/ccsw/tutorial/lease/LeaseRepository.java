package com.ccsw.tutorial.lease;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.lease.model.Lease;

/**
 * 
 * @author ccamposa
 *
 */
public interface LeaseRepository extends CrudRepository<Lease, Long> {
    /**
     * Método para recuperar un listado paginado de
     * {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param page
     * @return
     */
    Page<Lease> findAll(Pageable pageable);
}
