package fr.uge.memory;

public record Position(int x, int y) {

	public Position {
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
