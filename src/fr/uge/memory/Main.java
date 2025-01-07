package fr.uge.memory;

public class Main {
    public static void main(String[] args) {
        Player p = new Player("Gonzalo");
        GamingBoard gameBoard = new GamingBoard(p);

        // Crear la interfaz gráfica
        GraphicalGame graphicalGame = new GraphicalGame(gameBoard);
        graphicalGame.createAndShowGUI();
    }
}
