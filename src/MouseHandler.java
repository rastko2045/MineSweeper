import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        Component source = e.getComponent();
        if (source instanceof Cell cell) {
            Game gameInstance = cell.getGame();
            if (gameInstance.isLost()) {
                return;
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (!cell.isRevealed() && !cell.isFlagged()) {
                    cell.reveal();
                    if (cell instanceof BombCell) {
                        gameInstance.setRemainingSafeCells(gameInstance.getRemainingSafeCells() - 1);
                    }
                    if (gameInstance.getRemainingSafeCells() == 0) {
                        gameInstance.setWin();
                    }
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
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
