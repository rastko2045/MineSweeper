package Logic;

public class MineCell extends Cell {

    protected MineCell(Game game, Coordinates coords) {
        super(game, coords);
    }

    @Override
    public void reveal() {
        if (isRevealed()) {
            return;
        }
        if (getGame().isLost() || getGame().isWon()) {
            updateImage("mine_cell.png");
        } else {
            getGame().setIsLost(true);
            updateImage("mine_ignited_cell.png");
            getGame().revealAll(this);
        }
    }
}
