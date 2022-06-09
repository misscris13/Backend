package com.ccsw.tutorial.category;

import com.ccsw.tutorial.category.model.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ccsw
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryIT {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/category/";

    public static final Long NEW_CATEGORY_ID = 4L;
    public static final String NEW_CATEGORY_NAME = "CAT4";
    public static final Long MODIFY_CATEGORY_ID = 3L;
    public static final Long DELETE_CATEGORY_ID = 2L;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<CategoryDto>> responseType = new ParameterizedTypeReference<List<CategoryDto>>() {
    };

    @Test // that when calling findAll() it returns every category
    public void findAllShouldReturnAllCategories() {
        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, null, responseType);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());
    }

    @Test // that when saving without ID, a new category is inserted
    public void saveWithoudIdShouldCreateNewCategory() {
        CategoryDto dto = new CategoryDto();
        dto.setName(NEW_CATEGORY_NAME);

        // Add the dto (without id)
        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        // Get the new list of categories
        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, null, responseType);
        assertNotNull(response); // Check if not null
        assertEquals(4, response.getBody().size()); // Check if new size is correct

        // Search for the new item and get its ID. Check if corresponds with reality
        CategoryDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(NEW_CATEGORY_ID))
                .findFirst().orElse(null);
        assertNotNull(categorySearch); // Check if not null
        assertEquals(NEW_CATEGORY_NAME, categorySearch.getName()); // Check if result name is the same as new name
    }

    @Test // that when saving with ID, the category is modified
    public void modifyWithExistIdShouldModifyCategory() {
        CategoryDto dto = new CategoryDto();
        dto.setName(NEW_CATEGORY_NAME);

        // Call save with ID
        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + MODIFY_CATEGORY_ID, HttpMethod.PUT,
                new HttpEntity<>(dto), Void.class);

        // Get the list of categories
        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, null, responseType);
        assertNotNull(response); // Check if not null
        assertEquals(3, response.getBody().size()); // Check if size is the same

        // Get the modified category
        CategoryDto categorySearch = response.getBody().stream().filter(item -> item.getId().equals(MODIFY_CATEGORY_ID))
                .findFirst().orElse(null);
        assertNotNull(categorySearch); // Check if not null
        assertEquals(NEW_CATEGORY_NAME, categorySearch.getName()); // Check if names are the same
    }

    @Test // that when saving with wrong ID, it returns an error
    public void modifyWithNotExistIdShouldInternalError() {
        CategoryDto dto = new CategoryDto();
        dto.setName(NEW_CATEGORY_NAME);

        // Call save with non-existing ID
        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NEW_CATEGORY_ID,
                HttpMethod.PUT, new HttpEntity<>(dto), Void.class);

        // Check that the response was an internal server error
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test // that when deleting a category, it deletes the category
    public void deleteWithExistsIdShouldDeleteCategory() {
        // Call delete with ID
        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_CATEGORY_ID, HttpMethod.DELETE, null,
                Void.class);

        // Get list of categories
        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, null, responseType);
        assertNotNull(response); // Check if not null
        assertEquals(2, response.getBody().size()); // Check if size is one less
    }

    @Test // that when deleting a non-existing category, it returns an error
    public void deleteWithNoExistsIdShouldInternalError() {
        // Call delete with non-existing ID
        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NEW_CATEGORY_ID,
                HttpMethod.DELETE, null, Void.class);
        // Check that the response was an internal server error
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}