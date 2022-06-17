package com.ccsw.tutorial.lease;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import com.ccsw.tutorial.lease.model.LeaseDto;
import com.ccsw.tutorial.lease.model.LeaseSearchDto;

@RestController
public class LeaseController {

    /**
     * Método para recuperar un listado paginado de
     * {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param dto
     * @return
     */
    public Page<LeaseDto> findPage(LeaseSearchDto dto) {
        return null;
    }

    /**
     * Método para crear o actualizar un {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param id
     * @param data
     */
    public void save(Long id, LeaseDto data) {

    }

    /**
     * Método para borrar un {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param id
     * @param data
     */
    public void delete(Long id) {

    }

}
