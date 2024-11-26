package com.freddans.tournament.services;

import com.freddans.tournament.entities.Category;
import com.freddans.tournament.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        return optionalCategory.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Category with ID: " + id));
    }

    public Category findCategoryByName(String name) {
        Category category = categoryRepository.findCategoryByNameIgnoreCase(name);

        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find Category named: " + name);
        }

        return category;
    }

    public Category create(Category newCategory) {
        if (newCategory.getName() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Missing name of Category");
        }

        return categoryRepository.save(newCategory);
    }

    public Category update(long id, String newCategoryName) {
        Category category = findCategoryById(id);

        if (newCategoryName == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ERROR: Missing new name of Category");
        }

        category.setName(newCategoryName);

        return categoryRepository.save(category);
    }

    public String delete(long id) {
        Category categoryToDelete = findCategoryById(id);

        categoryRepository.delete(categoryToDelete);

        return "Successfully deleted Category ID: " + id;
    }
}
