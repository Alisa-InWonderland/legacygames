package org.factoriaf5.legacygames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findGamesByTitleContaining(String word);
    List<Game> findGamesByCategoryEquals(String category);
    List<Game> findGamesByPEGIEquals(String PEGI);

}

