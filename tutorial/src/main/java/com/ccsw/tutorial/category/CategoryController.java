package com.ccsw.tutorial.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.category.model.CategoryDto;

/**
 * @author ccsw
 */
@RequestMapping(value = "/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Método para recuperar todas las Category
     * 
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CategoryDto> findAll() {

        return this.categoryService.findAll();
    }

    /**
     * Método para crear o actualizar una Category
     * 
     * @param dto
     * @return
     */
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CategoryDto dto) {

        this.categoryService.save(id, dto);
    }

    /**
     * Método para borrar una Category
     * 
     * @param id
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        this.categoryService.delete(id);
    }
}
