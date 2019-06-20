package dev.stelmach.tweeditapi.database;

import dev.stelmach.tweeditapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
