import java.util.LinkedList;
import java.util.List;

public class SafeCell extends Cell {

    protected SafeCell(Game game, Coordinates coords) {
        super(game, coords);
        calculateValue();
    }

    private int value;

    @Override
    public void reveal() {
        if (isRevealed()) {
            return;
        }
        getGame().setRemainingSafeCells(getGame().getRemainingSafeCells() - 1);
        setRevealed(true);
        updateImage(value + "_cell.png");
        if (value == 0) {
            for (Cell cell : getNeighbors()) {
                cell.reveal();
            }
        }
        if (!getGame().isLost() && getGame().getRemainingSafeCells() == 0) {
            getGame().setWin();
        }
    }

    public void calculateValue() {
        for (Cell cell : getNeighbors()) {
            if (cell instanceof BombCell) {
                value++;
                if (value > 8) {
                    throw new IllegalStateException("Value cannot exceed 8.");
                }
            }
        }
    }

    public List<Cell> getNeighbors() {
        List<Cell> neighbors = new LinkedList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Cell neighborCell = getGame().getBoard().getCell(getCoords().getX() + i, getCoords().getY() + j);
                if (neighborCell != null) {
                    neighbors.add(neighborCell);
                }
            }
        }
        return neighbors;
    }
}
