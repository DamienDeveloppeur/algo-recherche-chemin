package pacman;

import java.util.Objects;

public class Point {
    private int x;
    private int y;
    private int point;
    private boolean ifValide;
    private float heuristic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point1 = (Point) o;
        return getX() == point1.getX() && getY() == point1.getY() && getPoint() == point1.getPoint() && isIfValide() == point1.isIfValide();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getPoint(), isIfValide());
    }

    Point(int x, int y, int point, boolean ifValide){
        this.x = x;
        this.y = y;
        this.point = point;
        this.ifValide = ifValide;
        // calcul diagonal
        //a² + b² = c²
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getCoord() {
        return "["+this.getX()+", "+this.getY()+", ifValide : "+ this.isIfValide()+"]";
    }

    public float getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(float heuristic) {
        this.heuristic = heuristic;
    }
    public boolean isIfValide() {return ifValide;}
}
