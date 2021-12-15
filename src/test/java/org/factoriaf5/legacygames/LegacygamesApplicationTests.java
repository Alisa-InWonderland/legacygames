package org.factoriaf5.legacygames;

import org.factoriaf5.legacygames.repositories.Game;
import org.factoriaf5.legacygames.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

class LegacygamesApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void loadsTheGamePage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("games"));
	}
	@Autowired
	GameRepository gameRepository;

	@Test
	void returnsTheExistingGames() throws Exception {

		Game game = gameRepository.save(new Game("Stardew Valley", "9'79€","7 años"));

		mockMvc.perform(get("/games"))
				.andExpect(status().isOk())
				.andExpect(view().name("games/all"))
				.andExpect(model().attribute("games", hasItem(game)));
	}
}
