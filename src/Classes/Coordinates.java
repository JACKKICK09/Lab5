package Classes;

public class Coordinates {
    private final double x;
    private final Long y;

    public Coordinates(double x, Long y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    @Override
    public int hashCode() {
        return y.hashCode() + (int) x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates coordinatesObj) {
            return (x == coordinatesObj.getX()) && y.equals(coordinatesObj.getY());
        }
        return false;
    }
}