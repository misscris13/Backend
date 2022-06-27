package com.ccsw.tutorial.lease;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.lease.model.Lease;
import com.ccsw.tutorial.lease.model.LeaseDto;
import com.ccsw.tutorial.lease.model.LeaseSearchDto;

public interface LeaseService {
    /**
     * Método para recuperar un {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param id
     * @return
     */
    Lease get(Long id);

    /**
     * Método para recuperar un listado paginado de
     * {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param dto
     * @return
     */
    Page<Lease> findPage(LeaseSearchDto dto);

    /**
     * Recupera un listado de {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @return
     */
    List<Lease> findAll();

    /**
     * Método para crear o editar una {@link com.ccsw.tutorial.lease.model.Lease}
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