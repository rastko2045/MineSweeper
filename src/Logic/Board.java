package Logic;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int ROWS;
    private final int COLS;
    private final int NUMBER_OF_MINES;
    private final Game game;

    private final Cell[][] cells;

    public Board(int rows, int cols, int mineNumber, Game game) {
        this.ROWS = rows;
        this.COLS = cols;
        this.NUMBER_OF_MINES = mineNumber;
        this.game = game;
        cells = new Cell[ROWS][COLS];
    }

    public void generateBoard() {
        generateMines();
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if (cells[x][y] == null) {
                    cells[x][y] = new SafeCell(game, new Coordinates(x, y));
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= ROWS || y < 0 || y >= COLS) {
            return null;
        }
        return cells[x][y];
    }

    private List<Coordinates> generateMineCoordinates() {
        Coordinates.resetGeneration();
        List<Coordinates> mineCoords = new LinkedList<>();
        for (int i = 0; i < NUMBER_OF_MINES; i++) {
            mineCoords.add(Coordinates.generateCoordinates(ROWS, COLS));
        }
        return mineCoords;
    }

    private void generateMines() {
        List<Coordinates> mineCoordsList = generateMineCoordinates();
        for (Coordinates coords : mineCoordsList) {
            cells[coords.getX()][coords.getY()] = new MineCell(game, coords);
        }
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLS() {
        return COLS;
    }

}
