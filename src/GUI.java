
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JFrame mainInterface;
    private Game gameInstance;
    private Board board;

    private JRadioButton easyDiff, mediumDiff, hardDiff, customDiff;
    private JLabel gameState;
    private JLabel flagsRemaining;
    private JButton newGame = new JButton("New Game");


    private final int MENU_HEIGHT = 100;

    public GUI(Game gameInstance) {
        this.gameInstance = gameInstance;
        board = gameInstance.getBoard();
    }

    public void initializeGUI() {
        mainInterface = new JFrame("Minesweeper!");
        mainInterface.setSize(board.getCOLS() * 32, board.getROWS() * 32 + 42 + MENU_HEIGHT);
        mainInterface.setLayout(new BoxLayout(mainInterface.getContentPane(), BoxLayout.PAGE_AXIS));
        JPanel menu = createMenu();
        mainInterface.add(menu);
        JPanel cellGrid = createCellGrid();
        mainInterface.add(cellGrid);
        mainInterface.setResizable(false);
        mainInterface.setVisible(true);
    }

    private JPanel createMenu() {
        JPanel menuPanel = new JPanel(new GridLayout(3, 2));
        menuPanel.setMaximumSize(new Dimension(board.getCOLS() * 32, MENU_HEIGHT));
        menuPanel.setPreferredSize(new Dimension(board.getCOLS() * 32, MENU_HEIGHT));
        easyDiff = new JRadioButton("Easy");
        easyDiff.addActionListener(this);
        mediumDiff = new JRadioButton("Medium");
        mediumDiff.addActionListener(this);
        hardDiff = new JRadioButton("Hard");
        hardDiff.addActionListener(this);
        customDiff = new JRadioButton("Custom");
        hardDiff.addActionListener(this);
        setDefaultDifficultyButton();
        ButtonGroup diffButtonGroup = new ButtonGroup();
        diffButtonGroup.add(easyDiff);
        diffButtonGroup.add(mediumDiff);
        diffButtonGroup.add(hardDiff);
        diffButtonGroup.add(customDiff);


        gameState = new JLabel("Playing!");
        flagsRemaining = new JLabel("Flags remaining: " + gameInstance.getRemainingFlags());
        newGame.addActionListener(this);
        menuPanel.add(gameState);
        menuPanel.add(newGame);
        menuPanel.add(easyDiff);
        menuPanel.add(mediumDiff);
        menuPanel.add(hardDiff);
        menuPanel.add(customDiff);

        return menuPanel;
    }

    private JPanel createCellGrid() {
        GridLayout layout = new GridLayout(board.getROWS(), board.getCOLS());
        JPanel cellGrid = new JPanel(layout);
        for (int x = 0; x < board.getROWS(); x++) {
            for (int y = 0; y < board.getCOLS(); y++) {
                cellGrid.add(board.getCell(x, y));
            }
        }
        return cellGrid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if (button.equals("Easy")) {
            gameInstance.setDifficulty(Game.Difficulty.EASY);
        }
        if (button.equals("Medium")) {
            gameInstance.setDifficulty(Game.Difficulty.MEDIUM);
        }
        if (button.equals("Hard")) {
            gameInstance.setDifficulty(Game.Difficulty.HARD);
        }
        if (button.equals("Custom")) {
            gameInstance.setDifficulty(Game.Difficulty.CUSTOM);
        }
        if (button.equals("New Game")) {
            mainInterface.dispose();
            gameInstance.initializeGame();
        }
    }

    public void setLoss() {
        gameState.setText("You lose!");
    }

    public void setWin() {
        gameState.setText("You win!");
    }

    private void setDefaultDifficultyButton() {
        switch (gameInstance.getDifficulty()) {
            case EASY -> easyDiff.setSelected(true);
            case MEDIUM -> mediumDiff.setSelected(true);
            case HARD -> hardDiff.setSelected(true);
            case CUSTOM -> customDiff.setSelected(true);
        }
    }

}
