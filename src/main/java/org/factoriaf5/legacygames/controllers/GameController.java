package org.factoriaf5.legacygames.controllers;

import org.factoriaf5.legacygames.repositories.Game;
import org.factoriaf5.legacygames.repositories.GameRepository;
import org.factoriaf5.legacygames.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
    public class GameController {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;


    @Autowired
    public GameController(GameRepository gameRepository, CategoryRepository categoryRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    String listGames(Model model) {
        List<Game> games = (List<Game>) gameRepository.findAll();
        model.addAttribute("title", "Games");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("games", games);
        return "games";
    }

    @GetMapping("/add")
    String getForm(Model model){
        Game game= new Game();
        model.addAttribute("title", "Add game");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("game", game);
        return "games/edit";
    }

    @PostMapping("/add")
    String addGame(@ModelAttribute Game game) {
        gameRepository.save(game);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    String editGame(Model model, @PathVariable Long id){
        Game game = gameRepository.findById(id).get();
        model.addAttribute("game", game);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("title", "Edit game");
        return "games/edit";
    }

    @GetMapping("/delete/{id}")
    String removeGame(@PathVariable Long id) {
        gameRepository.findById(id);
        gameRepository.deleteById(id);
        return "redirect:/";
    }

}

