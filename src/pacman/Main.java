package pacman;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Map map = new Map(6,6);
        map.generatePoint();
        map.generateHeuristic();
        map.findShortestPath();

    }
}
