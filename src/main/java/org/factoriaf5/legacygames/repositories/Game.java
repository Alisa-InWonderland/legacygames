package org.factoriaf5.legacygames.repositories;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
    @Table(name = "games")
    public class Game implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String price;
        private String PEGI;

        public Game() {

        }

        public Game(String title, String price, String PEGI) {
            this.title = title;
            this.price = price;
            this.PEGI = PEGI;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", price='" + price + '\'' +
                    ", PEGI='" + PEGI + '\'' +
                    '}';
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPEGI() {
            return PEGI;
        }

        public void setPEGI(String PEGI) {
            this.PEGI = PEGI;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Game game = (Game) o;
            return Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(price, game.price) && Objects.equals(PEGI, game.PEGI);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, price, PEGI);
        }
    }

