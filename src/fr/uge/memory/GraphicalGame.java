package fr.uge.memory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GraphicalGame {

    private GamingBoard gameBoard;

    // Coordenadas y tamaño del collage
    private final int collageX = 50;  // Coordenada X inicial del collage
    private final int collageY = 50;  // Coordenada Y inicial del collage
    private final int collageWidth = 300;  // Ancho total del collage
    private final int collageHeight = 300; // Altura total del collage
    private final int gridWidth = 5;  // Número de columnas del grid
    private final int gridHeight = 5; // Número de filas del grid
    private final int tileSize = 100;  // Tamaño de cada tile (imagen)

    public GraphicalGame(GamingBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void createAndShowGUI() {
        // Crear ventana
        JFrame frame = new JFrame("Animal Collage Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear panel
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g);
            }
        };

        // Añadir un MouseListener al panel para detectar clics
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                // Verificar si el clic está dentro del collage
                if (mouseX >= collageX && mouseX <= collageX + collageWidth &&
                    mouseY >= collageY && mouseY <= collageY + collageHeight) {
                    System.out.println("Has clickeado en la posición: (" + mouseX + ", " + mouseY + ")");
                }
            }
        });

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private void drawGrid(Graphics g) {
        // Dibujar el grid de 5x5
        Random random = new Random();

        // Coordenadas iniciales para el grid
        int x = collageX;
        int y = collageY;

        // Recorrer todas las celdas del grid
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                // Obtener un tile aleatorio desde el GamingBoard
                Tile randomTile = gameBoard.randomTile();

                // Dibujar el borde del tile
                g.setColor(Color.BLACK);
                g.drawRect(x, y, tileSize, tileSize);

                // Dibujar los animales y hábitats dentro de cada tile
                drawTile(g, randomTile, x, y);

                // Desplazar las coordenadas para la siguiente celda
                x += tileSize;
            }
            // Al final de la fila, mover la coordenada y hacia abajo y restablecer la coordenada x
            x = collageX;
            y += tileSize;
        }
    }

    private void drawTile(Graphics g, Tile tile, int x, int y) {
        // Obtener las listas de animales y hábitats
        List<Image> animalImages = getImages(tile.animalList(), "data/");
        List<Image> habitatImages = getImages(tile.habitatList(), "data/");

        // Calcular la cantidad de espacio disponible para las imágenes de animales y hábitats
        int totalImageCount = animalImages.size() + habitatImages.size();
        
        // Calcular el tamaño máximo de las imágenes en base al número total de imágenes
        int imageHeight = tileSize / totalImageCount; // Ajustar la altura de cada imagen
        
        // Dibujar los animales
        int animalY = y;
        for (Image image : animalImages) {
            g.drawImage(image, x + 5, animalY + 5, tileSize - 10, imageHeight - 10, null); // Ajusta la altura de las imágenes
            animalY += imageHeight; // Desplazar hacia abajo para el siguiente animal
        }

        // Dibujar los hábitats
        int habitatY = animalY; // Continuamos desde donde se dejó la altura de los animales
        for (Image image : habitatImages) {
            g.drawImage(image, x + 5, habitatY + 5, tileSize - 10, imageHeight - 10, null); // Ajusta la altura de las imágenes
            habitatY += imageHeight; // Desplazar hacia abajo para el siguiente hábitat
        }
    }


    private List<Image> getImages(List<?> items, String path) {
        return items.stream()
            .map(item -> new ImageIcon(path + item.toString().toLowerCase() + ".png").getImage())
            .toList();
    }

}
