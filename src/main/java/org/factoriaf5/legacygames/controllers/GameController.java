package org.factoriaf5.legacygames.controllers;

import org.factoriaf5.legacygames.repositories.Game;
import org.factoriaf5.legacygames.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
    public class GameController {
        /*@GetMapping("/")
        public String game() {
            return "games";*/


    private GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    String listGames(Model model) {
        List<Game> games = (List<Game>) gameRepository.findAll();
        model.addAttribute("title", "Game list");
        model.addAttribute("games", games);
        return "games";
    }

    @GetMapping("games/add")
    String addGames(@ModelAttribute Game game) {
        gameRepository.save(game);
        return "games/add";
    }
}

