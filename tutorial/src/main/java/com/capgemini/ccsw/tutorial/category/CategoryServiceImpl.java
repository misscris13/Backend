package com.capgemini.ccsw.tutorial.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.capgemini.ccsw.tutorial.category.model.CategoryDto;

/**
 * @author ccsw
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private long SEQUENCE = 1;

    private Map<Long, CategoryDto> categories = new HashMap<Long, CategoryDto>();

    /**
     * Método para recuperar todas las Category
     * 
     * @return
     */
    public List<CategoryDto> findAll() {

        return new ArrayList(this.categories.values());
    }

    /**
     * Método para crear o actualizar una Category
     * 
     * @param dto
     * @return
     */
    public void save(Long id, CategoryDto dto) {

        CategoryDto category;

        if (id == null) {
            category = new CategoryDto();
            category.setId(this.SEQUENCE++);
            this.categories.put(category.getId(), category);
        } else {
            category = this.categories.get(id);
        }

        category.setName(dto.getName());
    }

    /**
     * Método para borrar una Category
     * 
     * @param id
     */
    public void delete(Long id) {

        this.categories.remove(id);
    }
}
