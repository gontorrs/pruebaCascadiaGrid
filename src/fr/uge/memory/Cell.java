package fr.uge.memory;
public class Cell {
	
	private final int secondAnimalId;
	private final int id;
	public Cell(int i, int secondAnimalId) {
		id = i;
		this.secondAnimalId = secondAnimalId;
	}
	public int id() {
		return id;
	}

	public int secondAnimalId() {
		return secondAnimalId;
	}
}
