package com.freddans.tournament.repositories;

import com.freddans.tournament.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByNameIgnoreCase(String name);
}
