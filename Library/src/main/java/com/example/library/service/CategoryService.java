package com.example.library.service;

import com.example.library.domain.Category;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.exception.InvalidNameException;
import com.example.library.exception.InvalidNumberException;
import com.example.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategorys() {
        return categoryRepository.getAllCategories();
    }

    public Category getCategoryById(String categoryId) {
        Category category = categoryRepository.getCategoryById(categoryId);
        if (category == null) {
            throw new EntityNotFoundException("The category was not found!");
        }
        return category;
    }

    public String createCategory(Category category) {
        validateCategory(category);

        if (!categoryRepository.isNameUnique(category.getName())) {
            throw new InvalidNameException("Name " + category.getName() + " is not unique!");
        }

        if (category.getNumberOfBooks() < 0) {
            throw new InvalidNumberException("Number of books " + category.getNumberOfBooks() + " is not valid!");
        }

        categoryRepository.createCategory(category);
        return "SUCCESS";
    }

    public String updateCategory(String categoryId, Category updatedCategory) {
        validateCategory(updatedCategory);

        if (!categoryRepository.isCategoryExists(categoryId)) {
            throw new EntityNotFoundException("The category was not found!");
        }
        categoryRepository.updateCategory(categoryId, updatedCategory);
        return "SUCCESS";
    }

    public String deleteCategory(String categoryId) {
        if (!categoryRepository.isCategoryExists(categoryId)) {
            throw new EntityNotFoundException("The category was not found!");
        }
        categoryRepository.deleteCategory(categoryId);
        return "SUCCESS";
    }

    private void validateCategory(Category category) {
        if (StringUtils.isEmpty(category.getName())) {
            throw new InvalidNameException("Category name cannot be empty!");
        }

        if (StringUtils.isEmpty(category.getDescription())) {
            throw new InvalidNameException("Category description cannot be empty!");
        }

        if (category.getNumberOfBooks() < 0) {
            throw new InvalidNumberException("The age cannot be a negative number!");
        }
    }
}
