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
        private double price;
        private String PEGI;
        private String category;
        private String image;
        private int discount;
        private int launchYear;



        public Game() {

        }

    public Game(String title, double price, String PEGI, String category, String image) {
        this.title = title;
        this.price = price;
        this.PEGI = PEGI;
        this.category = category;
        this.image = image;

    }

    public Game(String title, double price, String PEGI, String category, String image, int discount, int launchYear) {
            this.title = title;
            this.price = price;
            this.PEGI = PEGI;
            this.category = category;
            this.image = image;
            this.discount = discount;
            this.launchYear= launchYear;

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

        public double getPrice() {
            return price;
        }
        public void setPrice(double price) {
            this.price = price;
        }

        public String getPEGI() {
            return PEGI;
        }
        public void setPEGI(String PEGI) {
            this.PEGI = PEGI;
        }

        public String getCategory() {return category;}
        public void setCategory(String category) {this.category = category;}

        public String getImage() {return image;}
        public void setImage(String image) {this.image = image;}

        public int getDiscount() {
            return discount;
        }
        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getLaunchYear() {
        return launchYear;
    }
        public void setLaunchYear(int launchYear) {
        this.launchYear = launchYear;
    }

        @Override
        public String toString() {
            return "Game{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", price='" + price + '\'' +
                    ", PEGI='" + PEGI + '\'' +
                    ", category='" + category + '\'' +
                    ", image='" + image + '\'' +
                    ", discount='" + discount + '\'' +
                    ", launchYear='" + launchYear + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Game game = (Game) o;
            return Objects.equals(id, game.id) && Objects.equals(title, game.title) && Objects.equals(price, game.price) && Objects.equals(PEGI, game.PEGI)
                    && Objects.equals(category, game.category) && Objects.equals(image, game.image) && Objects.equals(discount, game.discount)&& Objects.equals(launchYear, game.launchYear);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, title, price, PEGI, category, image, discount, launchYear);
        }
    }

