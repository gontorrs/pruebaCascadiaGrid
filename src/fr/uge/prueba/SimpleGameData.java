package fr.uge.prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class SimpleGameData {
	private final Tile[][] matrix;
	private int wins;
	public SimpleGameData(int nbLines, int nbColumns) {
		if (nbLines < 0 || nbColumns < 0) {
			throw new IllegalArgumentException();
		}
		matrix = new Tile[nbLines][nbColumns];
		wins = 0;
		randomise();
	}
	private void randomise() {
	    var tabAnimals = new int[lines() * columns()];
	    var tabHabitats = new int[lines() * columns()];
	    var random = new Random();
	    for (var i = 0; i < tabAnimals.length; i++) {
	        tabAnimals[i] = random.nextInt(5) + 1;
	    }
	    for (var i = 0; i < tabHabitats.length; i++) {
	        tabHabitats[i] = random.nextInt(5) + 1;
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
	        int row = i % lines();
	        int col = i / lines();
	        if (row == 0) {
	            matrix[row][col] = new Tile(animalToList(5), habitatToList(5), 1, false);
	        } else {
	            matrix[row][col] = new Tile(animalToList(tabAnimals[i]), habitatToList(tabHabitats[i]), 1, true);
	        }
	    }


	}


	public List<Animal> animalToList(int animal) {
	    switch (animal) {
	        case 0:
	            return List.of(Animal.BEAR);
	        case 1:
	            return List.of(Animal.ELK);
	        case 2:
	            return List.of(Animal.FOX);
	        case 3:
	            return List.of(Animal.EAGLE);
	        case 4:
	            return List.of(Animal.SALMON);
	        default:
	        	List<Animal> animalEmpty = new ArrayList<>();
	            return animalEmpty;
	    }
	}

	public List<Habitat> habitatToList(int habitat) {
	    switch (habitat) {
	        case 0:
	            return List.of(Habitat.MOUNTAIN);
	        case 1:
	            return List.of(Habitat.FOREST);
	        case 2:
	            return List.of(Habitat.RIVER);
	        case 3:
	            return List.of(Habitat.GRASSLAND);
	        case 4:
	            return List.of(Habitat.WETLAND);
	        default:
	        	List<Habitat> habitatEmpty = new ArrayList<>();
	            return habitatEmpty;
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
	public int secondAnimalId(int i, int j) {
		return matrix[i][j].secondAnimalId();
	}
	public void clickOnCell(int i, int j) {
		if (i < 0 || columns() <= i || j < 0 || lines() <= j || !isVisible(i, j)) {
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