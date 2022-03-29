package Logic;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public abstract class Cell extends JComponent {
    private final Game game;
    private final Coordinates coords;
    private boolean isRevealed;
    private boolean isFlagged;
    private Image image;


    protected Cell(Game game, Coordinates coords) {
        this.game = game;
        this.coords = coords;
        updateImage("default_cell.png");
        addMouseListener(new MouseHandler());
    }

    public Game getGame() {
        return game;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public abstract void reveal();

    protected void updateImage(String imageName) {
        ClassLoader cl = getClass().getClassLoader();
        image = Toolkit.getDefaultToolkit().createImage(cl.getResource("resources/" + imageName));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public java.util.List<Cell> getNeighbors() {
        List<Cell> neighbors = new LinkedList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Cell neighborCell = getGame().getBoard().getCell(getCoords().getX() + i, getCoords().getY() + j);
                if (neighborCell != null) {
                    neighbors.add(neighborCell);
                }
            }
        }
        return neighbors;
    }

    public void flag() {
        isFlagged = true;
        updateImage("flagged_cell.png");
    }

    public void unflag() {
        isFlagged = false;
        updateImage("default_cell.png");
    }

    public boolean isFlagged() {
        return isFlagged;
    }
}
