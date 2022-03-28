import javax.swing.*;
import java.awt.*;

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
