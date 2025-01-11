package fr.uge.prueba;

import java.util.Random;
public class SimpleGameData {
	private final Cell[][] matrix;
	private int wins;
	public SimpleGameData(int nbLines, int nbColumns) {
		if (nbLines < 0 || nbColumns < 0) {
			throw new IllegalArgumentException();
		}
		matrix = new Cell[nbLines][nbColumns];
		wins = 0;
		randomise();
	}
	private void randomise() {
	    var tabAnimals = new int[lines() * columns()];
	    var tabHabitats = new int[lines() * columns()];
	    var random = new Random();
	    for (var i = 0; i < tabAnimals.length; i++) {
	        tabAnimals[i] = random.nextInt(5);
	    }
	    for (var i = 0; i < tabHabitats.length; i++) {
	        tabHabitats[i] = random.nextInt(5);
	    }
	    for (var i = 0; i < tabAnimals.length; i++) {
	        var j = random.nextInt(i + 1);
	        int tempAnimal = tabAnimals[i];
	        tabAnimals[i] = tabAnimals[j];
	        tabAnimals[j] = tempAnimal;
	        int tempHabitat = tabHabitats[i];
	        tabHabitats[i] = tabHabitats[j];
	        tabHabitats[j] = tempHabitat;
	    }
	    for (var i = 0; i < tabAnimals.length; i++) {
	        matrix[i % lines()][i / lines()] = new Cell(animalToString(tabAnimals[i]), habitatToString(tabHabitats[i]));
	    }
	}


	public String animalToString(int animal) {
	    switch(animal) {
	        case 0:
	            return "BEAR";
	        case 1:
	            return "ELK";
	        case 2:
	            return "FOX";
	        case 3:
	            return "EAGLE";
	        case 4:
	            return "SALMON";
	        default:
	            return "BLANK";
	    }
	}
	public String habitatToString(int habitat) {
	    switch(habitat) {
	        case 0:
	            return "MOUNTAIN";
	        case 1:
	            return "FOREST";
	        case 2:
	            return "RIVER";
	        case 3:
	            return "GRASSLAND";
	        case 4:
	            return "WETLAND";
	        default:
	            return "BLANK";
	    }
	}



	public int lines() {
		return matrix.length;
	}
	public int columns() {
		return matrix[0].length;
	}
	public int idAnimal(int i, int j) {
		return matrix[i][j].idAnimal();
	}
	public int idHabitat(int i, int j) {
		return matrix[i][j].idHabitat();
	}
	public void clickOnCell(int i, int j) {
		if (i < 0 || columns() <= i || j < 0 || lines() <= j || isVisible(i, j)) {
			return;
		}
		System.out.println("Clicked on cell: [" + i + "," +  j + "]");
	}
	public boolean win() {
		return 2 * wins == lines() * columns();
	}
	public boolean isVisible(int i, int j) {
		return matrix[i][j].visible();
	}
}