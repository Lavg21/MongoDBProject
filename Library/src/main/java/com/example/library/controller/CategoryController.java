package com.example.library.controller;

import com.example.library.domain.Category;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategorys() {
        List<Category> categorys = categoryService.getAllCategorys();
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<Object> getCategoryById(@PathVariable String categoryId) {
        try {
            Category category = categoryService.getCategoryById(categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse("There was an error when retrieving the category!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        try {
            String result = categoryService.createCategory(category);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category, @PathVariable String categoryId) {
        try {
            String result = categoryService.updateCategory(categoryId, category);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable String categoryId) {
        try {
            String result = categoryService.deleteCategory(categoryId);
            if ("SUCCESS".equals(result)) {
                return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(createErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private Map<String, Object> createErrorResponse(String errorMessage) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("Error message", errorMessage);
        return errorResponse;
    }
}
