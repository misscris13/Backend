package com.ccsw.tutorial.lease;

import com.ccsw.tutorial.lease.model.Lease;
import com.ccsw.tutorial.lease.model.LeaseDto;
import com.ccsw.tutorial.lease.model.LeaseSearchDto;

import org.springframework.data.domain.Page;

/**
 * 
 * @author ccamposa
 *
 */
public interface LeaseService {
    /**
     * Método para recuperar un listado paginado de
     * {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param dto
     * @return
     */
    Page<Lease> findPage(LeaseSearchDto dto);

    /**
     * Método para crear o actualizar una
     * {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param id
     * @param data
     */
    void save(Long id, LeaseDto data);

    /**
     * Método para borrar una {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param id
     */
    void delete(Long id);
}
