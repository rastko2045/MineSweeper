package logic;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
        Component source = e.getComponent();
        if (source instanceof Cell cell) {
            Game game = cell.getGame();
            if (game.isLost() || game.isWon()) {
                return;
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (cell.isRevealed() && cell instanceof SafeCell safeCell) {
                    safeCell.chord();
                } else {
                    cell.leftClick();
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                cell.rightClick();
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
