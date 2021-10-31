package pacman;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Map map = new Map(5,5);
        map.generatePoint();
        map.generateHeuristic();
        map.findShortestPath();

    }
}
