package com.capgemini.ccsw.tutorial.category;

import java.util.List;

import com.capgemini.ccsw.tutorial.category.model.CategoryDto;

/**
 * @author ccsw
 *
 */
public interface CategoryService {

    /**
     * Método para recuperar todas las Category
     * 
     * @return
     */
    List<CategoryDto> findAll();

    /**
     * Método para crear o actualizar una Category
     * 
     * @param dto
     * @return
     */
    void save(Long id, CategoryDto dto);

    /**
     * Método para borrar una Category
     * 
     * @param id
     */
    void delete(Long id);
}
