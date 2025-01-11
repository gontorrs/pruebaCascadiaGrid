package fr.uge.prueba;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private boolean visible;
    private String animal;
    private String habitat;
    public Cell(String animal, String habitat) {
    	this.animal = animal;
    	this.habitat = habitat;
        visible = true;
    }
    public void hide() {
        visible = false;
    }

    public void show() {
        visible = true;
    }

    public boolean visible() {
        return visible;
    }
    public int animalToInt(String animal) {
        switch(animal.toUpperCase()) {
            case "BEAR":
                return 0;
            case "ELK":
                return 1;
            case "FOX":
                return 2;
            case "EAGLE":
                return 3;
            case "SALMON":
                return 4;
            default:
                return -1;
        }
    }
    public int habitatToInt(String habitat) {
        switch(habitat.toUpperCase()) {
            case "MOUNTAIN":
                return 0;
            case "FOREST":
                return 1;
            case "RIVER":
                return 2;
            case "GRASSLAND":
                return 3;
            case "WETLAND":
                return 4;
            default:
                return -1;
        }
    }

    public int idAnimal() {
    	return animalToInt(animal);
    }
    public int idHabitat() {
    	return habitatToInt(habitat);
    }
}
