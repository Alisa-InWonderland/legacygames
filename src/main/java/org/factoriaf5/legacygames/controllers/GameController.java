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

    private GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    String listGames(Model model) {
        List<Game> games = (List<Game>) gameRepository.findAll();
<<<<<<< HEAD
=======
        model.addAttribute("title", "Game list");
>>>>>>> 817e994522ccd7faf994d667a04fb5386d9fce34
        model.addAttribute("games", games);
        return "games";
    }



<<<<<<< HEAD



    @GetMapping("/add")
    String getForm(Model model){
        Game game= new Game();
        model.addAttribute("title", "Create new game");
        model.addAttribute("game", game);
        return "games/edit";
    }

    @PostMapping("/add")
    String addBook(@ModelAttribute Game game) {
        gameRepository.save(game);
        return "redirect:/";
    }
=======
>>>>>>> 817e994522ccd7faf994d667a04fb5386d9fce34
}

