package GUI;

import Logic.Board;
import Logic.Game;

import javax.swing.*;
import java.awt.*;

import static Logic.Game.Difficulty.CUSTOM;

public class MenuOptions extends JPanel {

    private int menuHeight;

    private JRadioButton easyDiff, mediumDiff, hardDiff, customDiff;
    private JLabel gameState;
    private JLabel flagsRemaining;
    private JButton newGame = new JButton("New Game");

    public MenuOptions(int menuHeight, Board board, Game gameInstance, JFrame gui) {
        this.menuHeight = menuHeight;
        setLayout(new GridLayout(4, 1));
        setMaximumSize(new Dimension(board.getCOLS() * 32, menuHeight));
        setPreferredSize(new Dimension(board.getCOLS() * 32, menuHeight));
        easyDiff = new JRadioButton("Easy");
        easyDiff.addActionListener(e -> gameInstance.setDifficulty(Game.Difficulty.EASY));
        mediumDiff = new JRadioButton("Medium");
        mediumDiff.addActionListener(e -> gameInstance.setDifficulty(Game.Difficulty.MEDIUM));
        hardDiff = new JRadioButton("Hard");
        hardDiff.addActionListener(e -> gameInstance.setDifficulty(Game.Difficulty.HARD));
        customDiff = new JRadioButton("Custom");
        customDiff.addActionListener(e -> {
            gameInstance.setDifficulty(CUSTOM);
            new CustomOptionsDialog(gameInstance);
        });
        setDefaultDifficultyButton(gameInstance);
        ButtonGroup diffButtonGroup = new ButtonGroup();
        diffButtonGroup.add(easyDiff);
        diffButtonGroup.add(mediumDiff);
        diffButtonGroup.add(hardDiff);
        diffButtonGroup.add(customDiff);

        gameState = new JLabel("Playing!");
        flagsRemaining = new JLabel("Flags remaining: " + gameInstance.getRemainingFlags());
        newGame.addActionListener(e -> {
            gui.dispose();
            gameInstance.initializeGame();
        });
        add(gameState);
        add(newGame);
        add(easyDiff);
        add(mediumDiff);
        add(hardDiff);
        add(customDiff);
        add(flagsRemaining);
    }

    public void setLoss() {
        gameState.setText("You lose!");
    }

    public void setWin() {
        gameState.setText("You win!");
    }

    public void updateFlags(int flags) {
        flagsRemaining.setText("Mines: " + flags);
    }

    private void setDefaultDifficultyButton(Game gameInstance) {
        switch (gameInstance.getDifficulty()) {
            case EASY -> easyDiff.setSelected(true);
            case MEDIUM -> mediumDiff.setSelected(true);
            case HARD -> hardDiff.setSelected(true);
            case CUSTOM -> customDiff.setSelected(true);
        }
    }

}
