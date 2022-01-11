package org.factoriaf5.legacygames;

import org.factoriaf5.legacygames.repositories.Game;
import org.factoriaf5.legacygames.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
	@WithMockUser
	void loadsTheGamePage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("games"));
	}

	@Autowired
	GameRepository gameRepository;

	@Test
	@WithMockUser
	void returnsTheExistingGames() throws Exception {

		Game game = gameRepository.save(new Game("Stardew Valley", "9'79€","7", "Racing", "img1"));

		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("games"))
				.andExpect(model().attribute("games", hasItem(game)))
				.andExpect(model().attribute("categories", hasItems(
						hasProperty("name", is("Racing"))

				)));
	}

	@Test
	@WithMockUser
	void returnsAFormToAddNewGames() throws Exception {
		mockMvc.perform(get("/add"))
				.andExpect(status().isOk())
				.andExpect(view().name("games/edit"))
				.andExpect(model().attributeExists("game"))
				.andExpect(model().attribute("title", "Add game"))
				.andExpect(model().attribute("categories", hasItems(
						hasProperty("name", is("Sports")),
						hasProperty("name", is("Platform")),
						hasProperty("name", is("Racing"))
					)));
		}

	@Test
	@WithMockUser
	void allowsToCreateANewGame() throws Exception {
		mockMvc.perform(post("/add")
						.with(csrf())
						.param("title", "Harry Potter")
						.param("price", "25.99€")
						.param("PEGI", "21")
						.param("category", "adventure")
						.param("image", "img2.jpg")

				)
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));

		List<Game> existingGames = (List<Game>) gameRepository.findAll();
		assertThat(existingGames, contains(allOf(
				hasProperty("title", equalTo("Harry Potter")),
				hasProperty("price", equalTo("25.99€")),
				hasProperty("PEGI", equalTo("21")),
				hasProperty("category", equalTo("adventure")),
				hasProperty("image", equalTo("img2.jpg"))

		)));
	}

	@Test
	@WithMockUser
	void returnsAFormToEditGames() throws Exception {
		Game game = gameRepository.save(new Game("Stardew Valley", "9'79€","7", "racing", "img1"));
		mockMvc.perform(get("/edit/" + game.getId()))
				.andExpect(status().isOk())
				.andExpect(view().name("games/edit"))
				.andExpect(model().attribute("game", game))
				.andExpect(model().attribute("title", "Edit game"));
	}

	@Test
	@WithMockUser
	void allowsToDeleteAGame() throws Exception {
		Game game = gameRepository.save(new Game("Tetris", "12€", "12", "puzzle", "img3.jpg"));
		mockMvc.perform(get("/delete/" + game.getId()))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));

		assertThat(gameRepository.findById(game.getId()), equalTo(Optional.empty()));
	}

	@Test
	void returns401() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isUnauthorized());
	}
	@Test
	@WithMockUser
	void returnsGamesFromAGivenCategory() throws Exception {

		Game racingGame = gameRepository.save(new Game("Mario Kart Wii", "9'99€", "7","racing","Mario-kart-wii2008.webp"));


		mockMvc.perform(get("/?category=racing"))
				.andExpect(status().isOk())
				.andExpect(view().name("games"))
				.andExpect(model().attribute("games", hasItem(racingGame)));
	}
	@Test
	@WithMockUser
	void returnsGamesFromAGivenPEGI() throws Exception {

		Game PEGIGame = gameRepository.save(new Game("Mario Kart Wii", "9'99€", "7","racing","Mario-kart-wii2008.webp"));
		Game PEGIGame2 = gameRepository.save(new Game("Mario Kart Wii", "9'99€", "12","racing","Mario-kart-wii2008.webp"));


		mockMvc.perform(get("/?PEGI=7"))
				.andExpect(status().isOk())
				.andExpect(view().name("games"))
				.andExpect(model().attribute("games", hasItem(PEGIGame)))
				.andExpect(model().attribute("games",not( hasItem(PEGIGame2))));
	}
}
