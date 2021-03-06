package gui;


import logic.*;

import javax.swing.*;

public class GUI extends JFrame {


    private final Game gameInstance;
    private final Board board;

    private final MenuOptions menu;
    private final CellGrid cellGrid;


    public GUI(Game gameInstance) {
        super("Minesweeper!");
        this.gameInstance = gameInstance;
        board = gameInstance.getBoard();
        cellGrid = new CellGrid(board);
        menu = new MenuOptions(board, this.gameInstance, this);
        setSize(board.getCOLS() * 32, board.getROWS() * 32 + 42 + MenuOptions.MENU_HEIGHT);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        add(menu);
        add(cellGrid);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setLoss() {
        menu.setLoss();
        JOptionPane.showMessageDialog(this, "You lose!");
    }

    public void setWin() {
        menu.setWin();
        JOptionPane.showMessageDialog(this, "You win!");
    }

    public void updateFlags(int flags) {
        menu.updateFlags(flags);
    }


}
