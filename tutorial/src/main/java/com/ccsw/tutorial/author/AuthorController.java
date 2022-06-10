package com.ccsw.tutorial.author;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.author.model.AuthorSearchDto;

/**
 * 
 * @author ccamposa
 *
 */
public class AuthorController {
    /**
     * Método para recuperar un listado paginado de
     * {@link com.cssw.tutorial.author.model.Author}
     * 
     * @param dto
     * @return
     */
    public Page<AuthorDto> findPage(AuthorSearchDto dto) {
        return null;
    }

    /**
     * Método para crear o actualizar un
     * {@link com.ccsw.tutorial.author.model.Author}
     * 
     * @param id
     * @param data datos de la entidad
     */
    public void save(Long id, AuthorDto data) {

    }

    /**
     * Método para crear o actualizar un
     * {@link com.ccsw.tutorial.author.model.Author}
     * 
     * @param id PK de la entidad
     */
    public void delete(Long id) {

    }
}
