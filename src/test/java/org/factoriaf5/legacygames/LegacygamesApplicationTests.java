package org.factoriaf5.legacygames;

import org.factoriaf5.legacygames.repositories.Game;
import org.factoriaf5.legacygames.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

class LegacygamesApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@BeforeEach
	void setUp() {
		gameRepository.deleteAll();
	}

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

		Game game = gameRepository.save(new Game("Stardew Valley", "9'79€","7"));

		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("games"))
				.andExpect(model().attribute("games", hasItem(game)));
	}

	@Test
	void returnsAFormToAddNewGames() throws Exception {
		mockMvc.perform(get("/add"))
				.andExpect(status().isOk())
				.andExpect(view().name("games/edit"));
	}

	@Test
	void allowsToCreateANewGame() throws Exception {
		mockMvc.perform(post("/add")
						.param("title", "Harry Potter")
						.param("price", "25.99€")
						.param("PEGI", "21")
				)
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"))
		;

		List<Game> existingGames = (List<Game>) gameRepository.findAll();
		assertThat(existingGames, contains(allOf(
				hasProperty("title", equalTo("Harry Potter")),
				hasProperty("price", equalTo("25.99€")),
				hasProperty("PEGI", equalTo("21"))
		)));
	}
	@Test
	void returnsAFormToEditGames() throws Exception {
		Game game = gameRepository.save(new Game("Stardew Valley", "9'79€","7"));
		mockMvc.perform(get("/edit/" + game.getId()))
				.andExpect(status().isOk())
				.andExpect(view().name("games/edit"))
				.andExpect(model().attribute("game", game))
				.andExpect(model().attribute("title", "Edit game"));
	}

	@Test
	void allowsToDeleteAGame() throws Exception {
		Game game = gameRepository.save(new Game("Tetris", "12€", "12"));
		mockMvc.perform(get("/delete/" + game.getId()))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));

		assertThat(gameRepository.findById(game.getId()), equalTo(Optional.empty()));
	}
}
