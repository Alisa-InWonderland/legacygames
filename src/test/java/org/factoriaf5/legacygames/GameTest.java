package org.factoriaf5.legacygames;

import org.factoriaf5.legacygames.repositories.Game;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GameTest {
    @Test
    void calcularPrecioConDescuento() {
        Game game = new Game("Super Mario Bros", 14.99, "7", "Platform", "superMarioBros2006.webp", 20, 1985);
        assertThat(game.getPriceWithDiscount(),equalTo(11.992));

    }
}
