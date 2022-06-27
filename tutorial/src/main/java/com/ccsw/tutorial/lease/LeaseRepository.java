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
    // query
    Page<Lease> findAll(Pageable pageable);
}