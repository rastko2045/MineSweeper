package logic;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
        Component source = e.getComponent();
        if (source instanceof Cell cell) {
            Game gameInstance = cell.getGame();
            if (gameInstance.isLost() || gameInstance.isWon()) {
                return;
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (!cell.isRevealed() && !cell.isFlagged()) {
                    cell.reveal();
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                if (cell.isRevealed()) {
                    return;
                }
                if (!cell.isFlagged()) {
                    if (gameInstance.getRemainingFlags() == 0) {
                        return;
                    }
                    cell.flag();
                    gameInstance.setRemainingFlags(gameInstance.getRemainingFlags() - 1);
                } else {
                    cell.unflag();
                    gameInstance.setRemainingFlags(gameInstance.getRemainingFlags() + 1);
                }
            }
            if (e.getButton() == MouseEvent.BUTTON2) {
                if (cell.isRevealed() && cell instanceof SafeCell safeCell) {
                    int flaggedNeighbors = 0;
                    for (Cell neighbor : cell.getNeighbors()) {
                        if (neighbor.isFlagged()) {
                            flaggedNeighbors++;
                        }
                    }
                    if (flaggedNeighbors == safeCell.getValue()) {
                        for (Cell neighbor : cell.getNeighbors()) {
                            if (!neighbor.isFlagged()) {
                                neighbor.reveal();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
