package logic;

import java.util.*;

public class Coordinates {
    private final int xCoord;
    private final int yCoord;

    private static final Random generator = new Random();
    private static final Set<Coordinates> generatedCoordinates = new HashSet<>();

    public Coordinates(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getX() {
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return xCoord == that.xCoord && yCoord == that.yCoord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);
    }


    public static Coordinates generateCoordinates(int xLimit, int yLimit) {
        Coordinates coords;
        do {
            int x = generator.nextInt(xLimit);
            int y = generator.nextInt(yLimit);
            coords = new Coordinates(x, y);
        } while (generatedCoordinates.contains(coords));
        generatedCoordinates.add(coords);
        return coords;
    }

    public static void resetGeneration() {
        generatedCoordinates.clear();
    }
}
