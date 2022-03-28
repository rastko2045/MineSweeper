public class Game {

    private final int EASY_DIFF_ROWS = 9;
    private final int EASY_DIFF_COLS = 9;
    private final int EASY_DIFF_MINES = 10;
    private final int MID_DIFF_ROWS = 15;
    private final int MID_DIFF_COLS = 15;
    private final int MID_DIFF_MINES = 40;
    private final int HARD_DIFF_ROWS = 16;
    private final int HARD_DIFF_COLS = 30;
    private final int HARD_DIFF_MINES = 99;

    private int bombAmount;
    private Board board;
    private int rows, cols;
    private Difficulty difficulty;
    private static GUI gui;
    private int remainingSafeCells;
    private int remainingFlags;
    private boolean isLost;


    public static void main(String[] args) {
        Game game = new Game();
    }

    public Game() {
        initializeGame();
    }

    /*
        Config file idea, number of rows and cols in game.
         */
    public void initializeGame() {
        setIsLost(false);
        if (difficulty == null) {
            difficulty = Difficulty.EASY;
        }
        if (difficulty == Difficulty.EASY) {
            bombAmount = EASY_DIFF_MINES;
            rows = EASY_DIFF_ROWS;
            cols = EASY_DIFF_COLS;
        }
        if (difficulty == Difficulty.MEDIUM) {
            bombAmount = MID_DIFF_MINES;
            rows = MID_DIFF_ROWS;
            cols = MID_DIFF_COLS;
        }
        if (difficulty == Difficulty.HARD) {
            bombAmount = HARD_DIFF_MINES;
            rows = HARD_DIFF_ROWS;
            cols = HARD_DIFF_COLS;
        }
        board = new Board(rows, cols, bombAmount, this);
        remainingFlags = bombAmount;
        remainingSafeCells = rows * cols - bombAmount;
        board.generateBoard();
        gui = new GUI(this);
        gui.initializeGUI();
    }

    public void revealAll(Cell source) {
        gui.setLoss();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Cell cell = board.getCell(x, y);
                if (!cell.equals(source)) {
                    cell.reveal();
                }
            }
        }
    }

    public void setWin() {
        gui.setWin();

    }

    public boolean isLost() {
        return isLost;
    }

    public void setIsLost(boolean hasLost) {
        this.isLost = hasLost;
    }

    public Board getBoard() {
        return board;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getRemainingSafeCells() {
        return remainingSafeCells;
    }

    public void setRemainingSafeCells(int remainingSafeCells) {
        this.remainingSafeCells = remainingSafeCells;
    }

    public int getRemainingFlags() {
        return remainingFlags;
    }

    public void setRemainingFlags(int remainingFlags) {
        this.remainingFlags = remainingFlags;
    }

    public enum Difficulty {
        EASY, MEDIUM, HARD, CUSTOM
    }
}
