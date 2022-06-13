package com.ccsw.tutorial.category;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author ccsw
 */
@ExtendWith(MockitoExtension.class)
public class CategoryTest {
    public static final String CATEGORY_NAME = "CAT1";
    public static final Long EXISTS_CATEGORY_ID = 1L;
    public static final Long NOT_EXISTS_CATEGORY_ID = 0L;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void getExistsCategoryIdShouldReturnCategory() {
        Category category = mock(Category.class);
        when(category.getId()).thenReturn(EXISTS_CATEGORY_ID);
        when(categoryRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(category));

        Category categoryResponse = categoryService.get(EXISTS_CATEGORY_ID);

        assertNotNull(categoryResponse);
        assertEquals(EXISTS_CATEGORY_ID, category.getId());
    }

    @Test
    public void getNotExistsCategoryIdShouldReturnNull() {
        when(categoryRepository.findById(NOT_EXISTS_CATEGORY_ID)).thenReturn(Optional.empty());

        Category category = categoryService.get(NOT_EXISTS_CATEGORY_ID);

        assertNull(category);
    }

    @Test // that when calling findAll() it returns every category
    public void findAllShouldReturnAllCategories() {
        List<Category> list = new ArrayList<>();
        list.add(mock(Category.class));

        when(categoryRepository.findAll()).thenReturn(list);

        List<Category> categories = categoryService.findAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
    }

    @Test // that when saving without ID, a new category is inserted
    public void saveNotExistsCategoryIdShouldInsert() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(CATEGORY_NAME);

        ArgumentCaptor<Category> category = ArgumentCaptor.forClass(Category.class);

        // Call save with null ID (non-existent category)
        categoryService.save(null, categoryDto);

        // Simulate save action
        verify(categoryRepository).save(category.capture());

        // Check if value is the provided one
        assertEquals(CATEGORY_NAME, category.getValue().getName());
    }

    @Test // that when saving with ID, the category is modified
    public void saveExistsCategoryIdShouldUpdate() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(CATEGORY_NAME);

        // Create mock category
        Category category = mock(Category.class);
        // Find an existing category (id 1)
        when(categoryRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(category));

        // Call save with existing ID and mock category
        categoryService.save(EXISTS_CATEGORY_ID, categoryDto);

        // Verify that save was called over the same category
        verify(categoryRepository).save(category);
    }

    @Test // that when deleting a category, it deletes the category
    public void deleteExistsCategoryIdShouldDelete() {
        // Delete the category
        categoryService.delete(EXISTS_CATEGORY_ID);
        // Verify that the category was deleted
        verify(categoryRepository).deleteById(EXISTS_CATEGORY_ID);
    }
}
