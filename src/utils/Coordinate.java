package utils;

public class Coordinate {
    private Integer x;
    private Integer y;

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate coordinate) {
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x.hashCode() * y.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!=getClass())
            return false;

        Coordinate coordinate = (Coordinate) obj;
        if(this.x.equals(coordinate.x) && this.y.equals(coordinate.y)) return true;
        else return false;
    }
}
