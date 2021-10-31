package pacman;

public class Point {
    private int x;
    private int y;
    private int point;
    private boolean ifValide;
    private float heuristic;

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
        return "["+this.getX()+", "+this.getY()+"]";
    }

    public float getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(float heuristic) {
        this.heuristic = heuristic;
    }
}
