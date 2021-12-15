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
                new Game("Tetris", "4'99€", "7"),
                new Game("Grand Theft Auto V", "24'99€", "18"),
                new Game("Call Of Duty: Modern Warfare 3", "44'99€", "16"),
                new Game("Halo 3", "44'99€", "12")
        ));
    }
}
