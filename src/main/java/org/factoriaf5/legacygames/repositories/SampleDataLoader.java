package org.factoriaf5.legacygames.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SampleDataLoader {
    private GameRepository gameRepository;

    @Autowired
    public SampleDataLoader(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @PostConstruct
    public void loadSampleData() {
        gameRepository.saveAll(List.of(
                new Game("Super Mario Bros", "14'99€", "7"),
                new Game("Mario Kart Wii", "9'99€", "7"),
                new Game("Tetris", "4'99€", "Fantasy"),
                new Game("Lean Software Development", "Mary Poppendieck", "Software"),
                new Game("Women, Race and Class", "Angela Y. Davis", "Essay"),
                new Game("Object Design", "Rebecca Wirfs-Brock", "Software")
        ));
    }
}
