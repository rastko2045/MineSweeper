package logic;

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
        if (getGame().isWon()) {
            throw new IllegalStateException("This shouldn't happen. Game won despite not revealing all safe cells");
        }
        getGame().setRemainingSafeCells(getGame().getRemainingSafeCells() - 1);
        setRevealed(true);
        if (getGame().isLost() && this.isFlagged()) {
            updateImage("crossed_out_mine_cell.png");
        } else {
            updateImage(value + "_cell.png");
        }
        if (value == 0) {
            for (Cell cell : getNeighbors()) {
                cell.reveal();
            }
        }
        if (!getGame().isLost() && getGame().getRemainingSafeCells() == 0) {
            if (getGame().isWon()) {
                return;
            }
            getGame().setWin();
        }
    }

    public void chord() {
        if (isRevealed() && !getGame().isWon() && !getGame().isLost()) {
            int flaggedNeighbors = 0;
            for (Cell neighbor : getNeighbors()) {
                if (neighbor.isFlagged()) {
                    flaggedNeighbors++;
                }
            }
            if (flaggedNeighbors == getValue()) {
                getNeighbors().stream()
                        .filter(e -> !e.isFlagged())
                        .forEach(Cell::reveal);
            }
        }
    }

    public void calculateValue() {
        for (Cell cell : getNeighbors()) {
            if (cell instanceof MineCell) {
                value++;
                if (value > 8) {
                    throw new IllegalStateException("Value cannot exceed 8.");
                }
            }
        }
    }

    public int getValue() {
        return value;
    }
}
