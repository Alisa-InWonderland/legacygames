package org.factoriaf5.legacygames.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    public CategoryRepository() {
    }

    public List<Category> findAll() {
        return List.of(
                new Category("Sports"),
                new Category("Platform"),
                new Category("Racing"),
                new Category("Role-Playing"),
                new Category("Puzzle"),
                new Category("Misc"),
                new Category("Shooter"),
                new Category("Simulation"),
                new Category("Action"),
                new Category("Fighting"),
                new Category("Adventure")
        );
    }
}