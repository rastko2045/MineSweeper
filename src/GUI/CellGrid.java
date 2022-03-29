package GUI;

import Logic.Board;

import javax.swing.*;
import java.awt.*;

public class CellGrid extends JPanel {

    public CellGrid(Board board) {
        setLayout(new GridLayout(board.getROWS(), board.getCOLS()));
        for (int x = 0; x < board.getROWS(); x++) {
            for (int y = 0; y < board.getCOLS(); y++) {
                add(board.getCell(x, y));
            }
        }
    }
}
