package org.factoriaf5.legacygames.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
    public class GameController {
        @GetMapping("/")
        public String game() {
            return "games";
        }
}

