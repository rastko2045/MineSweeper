public class BombCell extends Cell {

    protected BombCell(Game game, Coordinates coords) {
        super(game, coords);
    }

    @Override
    public void reveal() {
        if (isRevealed()) {
            return;
        }
        if (getGame().isLost()) {
            updateImage("bomb_cell.png");
        } else {
            getGame().setIsLost(true);
            updateImage("bomb_ignited_cell.png");
            getGame().revealAll(this);
        }
    }
}
