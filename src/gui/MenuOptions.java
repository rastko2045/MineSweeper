package gui;

import logic.Board;
import logic.Game;

import javax.swing.*;
import java.awt.*;

import static logic.Game.Difficulty.CUSTOM;

public class MenuOptions extends JPanel {

    public static final int MENU_HEIGHT = 100;

    private final JRadioButton easyDiff, mediumDiff, hardDiff, customDiff;
    private final JLabel gameStateLabel;
    private final JLabel flagsRemainingLabel;
    private final JLabel timeElapsedLabel;
    private final JButton newGameButton = new JButton("New Game");

    private int time;
    private final Timer timer;

    public MenuOptions(Board board, Game gameInstance, GUI gui) {
        setLayout(new GridLayout(4, 1));
        setMaximumSize(new Dimension(board.getCOLS() * 32, MENU_HEIGHT));
        setPreferredSize(new Dimension(board.getCOLS() * 32, MENU_HEIGHT));
        timeElapsedLabel = new JLabel("Time: " + time);
        timer = new Timer(1000, e -> {
            time++;
            timeElapsedLabel.setText("Time: " + time);
        });
        easyDiff = new JRadioButton("Easy");
        easyDiff.addActionListener(e -> gameInstance.setDifficulty(Game.Difficulty.EASY));
        mediumDiff = new JRadioButton("Medium");
        mediumDiff.addActionListener(e -> gameInstance.setDifficulty(Game.Difficulty.MEDIUM));
        hardDiff = new JRadioButton("Hard");
        hardDiff.addActionListener(e -> gameInstance.setDifficulty(Game.Difficulty.HARD));
        customDiff = new JRadioButton("Custom");
        customDiff.addActionListener(e -> {
            gameInstance.setDifficulty(CUSTOM);
            new CustomOptionsDialog(gameInstance, gui);
        });
        setDefaultDifficultyButton(gameInstance);
        ButtonGroup diffButtonGroup = new ButtonGroup();
        diffButtonGroup.add(easyDiff);
        diffButtonGroup.add(mediumDiff);
        diffButtonGroup.add(hardDiff);
        diffButtonGroup.add(customDiff);

        gameStateLabel = new JLabel("Playing!");
        flagsRemainingLabel = new JLabel("Mines: " + gameInstance.getRemainingFlags());
        newGameButton.addActionListener(e -> {
            gui.dispose();
            gameInstance.initializeGame();
        });
        add(gameStateLabel);
        add(newGameButton);
        add(easyDiff);
        add(mediumDiff);
        add(hardDiff);
        add(customDiff);
        add(flagsRemainingLabel);
        add(timeElapsedLabel);
        timer.start();
    }

    public void setLoss() {
        gameStateLabel.setText("You lose!");
        timer.stop();
    }

    public void setWin() {
        gameStateLabel.setText("You win!");
        timer.stop();
    }

    public void updateFlags(int flags) {
        flagsRemainingLabel.setText("Mines: " + flags);
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
