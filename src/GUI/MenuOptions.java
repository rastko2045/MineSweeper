package GUI;

import Logic.Board;
import Logic.Game;

import javax.swing.*;
import java.awt.*;

import static Logic.Game.Difficulty.CUSTOM;

public class MenuOptions extends JPanel {

    private int menuHeight;

    private JRadioButton easyDiff, mediumDiff, hardDiff, customDiff;
    private JLabel gameStateLabel;
    private JLabel flagsRemainingLabel;
    private JLabel timeElapsedLabel;
    private JButton newGameButton = new JButton("New Game");

    private int time;
    private Timer timer;

    public MenuOptions(int menuHeight, Board board, Game gameInstance, JFrame gui) {
        this.menuHeight = menuHeight;
        setLayout(new GridLayout(4, 1));
        setMaximumSize(new Dimension(board.getCOLS() * 32, menuHeight));
        setPreferredSize(new Dimension(board.getCOLS() * 32, menuHeight));
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
            new CustomOptionsDialog(gameInstance);
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
