package org.factoriaf5.legacygames.controllers;

import org.factoriaf5.legacygames.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
    public class GameController {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final PEGIRepository pegiRepository;


    @Autowired
    public GameController(GameRepository gameRepository, CategoryRepository categoryRepository, PEGIRepository pegiRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.pegiRepository = pegiRepository;


    }

    @GetMapping("/")
    String listGames(@Valid Model model, @RequestParam(required = false) String category, @RequestParam(required = false) String PEGI) {
        model.addAttribute("title", "Game list");
        model.addAttribute("games", getGames(category, PEGI));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("PEGIList", pegiRepository.findAll());
        return "games";
    }


    @GetMapping("/add")
    String getForm( @Valid Model model ) {
        Game game = new Game();
        model.addAttribute("title", "Add game");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("game", game);
        return "games/edit";
    }

    @PostMapping("/add")
    String addGame(@Valid @ModelAttribute Game game) {
        gameRepository.save(game);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    String editGame(@Valid Model model, @PathVariable Long id) {
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

    private List<Game> getGames(String category, String PEGI) {

        if (category != null) {
            return gameRepository.findGamesByCategoryEquals(category);
        }
        if (PEGI != null) {
            return gameRepository.findGamesByPEGIEquals(PEGI);
        }


        return gameRepository.findAll();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
