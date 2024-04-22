package org.learning.blogapplication.repositries;

import org.learning.blogapplication.entities.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
