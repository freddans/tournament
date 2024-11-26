package com.freddans.tournament.controllers;

import com.freddans.tournament.entities.Category;
import com.freddans.tournament.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @GetMapping("/name/{category}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable("category") String category) {
        return ResponseEntity.ok(categoryService.findCategoryByName(category));
    }

    @PostMapping("/create")
    public ResponseEntity<Category> create(@RequestBody Category newCategory) {
        return ResponseEntity.ok(categoryService.create(newCategory));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") long existingCategoryId, @RequestParam("name") String newCategoryName) {
        return ResponseEntity.ok(categoryService.update(existingCategoryId, newCategoryName));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(categoryService.delete(id));
    }
}
