package com.ccsw.tutorial.lease;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.lease.model.LeaseDto;
import com.ccsw.tutorial.lease.model.LeaseSearchDto;
import com.ccsw.tutorial.config.mapper.BeanMapper;

/**
 * 
 * @author ccamposa
 *
 */
@RequestMapping(value = "/lease")
@RestController
@CrossOrigin(origins = "*")
public class LeaseController {

    @Autowired
    LeaseService leaseService;

    @Autowired
    BeanMapper beanMapper;

    /**
     * Método para recuperar un listado paginado de
     * {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param dto
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<LeaseDto> findPage(@RequestBody LeaseSearchDto dto) {
        return this.beanMapper.mapPage(this.leaseService.findPage(dto), LeaseDto.class);
    }

    /**
     * Método para crear o actualizar un {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param id
     * @param data
     */
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(Long id, @RequestBody LeaseDto data) {
        this.leaseService.save(id, data);
    }

    /**
     * Método para borrar un {@link com.ccsw.tutorial.lease.model.Lease}
     * 
     * @param id
     * @param data
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(Long id) {
        this.leaseService.delete(id);
    }

}
