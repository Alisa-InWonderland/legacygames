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
                new Game("Super Mario Bros", "14'99€", "7", "platform", "def-photo.jpg"),
                new Game("Mario Kart Wii", "9'99€", "7", "racing", "def-photo.jpg"),
                new Game("Tetris", "4'99€", "7", "puzzle", "def-photo.jpg"),
                new Game("Grand Theft Auto V", "24'99€", "18", "action", "def-photo.jpg"),
                new Game("Call Of Duty: Modern Warfare 3", "44'99€", "16", "role-playing", "def-photo.jpg"),
                new Game("Halo 3", "44'99€", "12", "shooter", "def-photo.jpg")
        ));
    }
}
