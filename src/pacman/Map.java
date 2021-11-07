package pacman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Map {
    private ArrayList<String> map = new ArrayList<>();
    private ArrayList<Point> caseChecked = new ArrayList();
    private ArrayList<Point> rightPath = new ArrayList();
    private ArrayList<Point> listNoeud = new ArrayList<>();
    private float sizeRightPath;
    private int sizeMapWidth;
    private int sizeMapHeight;
    static Point startPoint;
    static Point endPoint;
    private Point currentPoint;
    private Point[][] p;
    private boolean pathFind;

    // heuristic
    // abs(a.x - b.x) + abs(a.y - b.y)
    /**
     * une liste pour le chemin
     * une map pour pour les cases checkés
     * et la blockingqueue pour savoir quel point je suis et je check
     */
    Map(int x, int y){
        this.sizeMapWidth = x;
        this.sizeMapHeight = y;
        this.p = new Point[x][y];
    }

    public void generatePoint(){
        for(int i =0; i < this.sizeMapWidth; i++) {
            for (int j = 0; j < this.sizeMapHeight; j++) {
                boolean wall = true;
                if (j == 5 && (i != 0 && i != 6)) wall = false;
                if (i == 5 && (j != 0 && j != 6)) wall = false;
                if (j == 1 && (i == 2 || i == 3 || i == 4 || i ==5))wall = false;
                if(i == 0 && j == 2) wall = false;
                p[i][j] = new Point(i, j, j+i, wall);
                listNoeud.add(p[i][j]);
            }
        }
        endPoint = p[4][3];
        startPoint = p[0][0];
        System.out.println(Map.endPoint.getCoord());
    }

    public int randInt() {
        return ThreadLocalRandom.current().nextInt(2, this.sizeMapWidth);
    }
    public void generateHeuristic(){
        for(int i =0; i < this.sizeMapHeight; i++) {
            for (int j = 0; j < this.sizeMapWidth; j++) {
                p[i][j].setHeuristic(Math.abs(p[i][j].getX() - Map.endPoint.getX()) + Math.abs(p[i][j].getY() - Map.endPoint.getY()));
                System.out.println("coord : "+ p[i][j].getCoord() + " heuristique : " + p[i][j].getHeuristic());
            }
        }
    }
    // a ce stade, quel est le chemin le plus rapide entre le startPoint et le endPoint
    // chaque case checké sera enregistré dans un map
    public void findShortestPath() {
        currentPoint = startPoint;
        rightPath.add(startPoint);
        System.out.println("______________________________________________________");
        while(!pathFind){

            boolean caseFinded = false;
            caseChecked.clear();
            for(Point p2 : listNoeud){
                if(!rightPath.contains(p2) && p2.isIfValide()) {
                    if(p2.getHeuristic() < currentPoint.getHeuristic() &&
                            p2.getX() - currentPoint.getX() <= 1 &&
                            p2.getY() - currentPoint.getY() <= 1 &&
                            (p2.getX() - currentPoint.getX() == 0 ||
                             p2.getY() - currentPoint.getY() == 0)
                    ) {
                        System.out.println("REGULAR p2.getX() - currentPoint.getX() : " + (p2.getX() - currentPoint.getX()));
                        System.out.println("REGULAR p2.getY() - currentPoint.getY() : "+(p2.getY() - currentPoint.getY()));
                        System.out.println("Current point : "+(currentPoint.getCoord()));
                        rightPath.add(p2);
                        currentPoint = p2;
                        caseFinded = true;
                        if(p2.equals(endPoint)) {
                            pathFind = true;
                            System.out.println("FINISH");
                            for(Point p3 : rightPath) {System.out.println(p3.getCoord());}
                            return;
                        }
                    } else {
                        caseChecked.add(p2);
                    }
                }
            }
            if(!caseFinded) {
                System.out.println("case checked 0 : " + caseChecked.get(0).getHeuristic());
                float min = 9999999;
                Point pointToPush = null;
                for(int i = 0; i < caseChecked.size(); i++) {
//                    if (i == 0) {
//                        min =  caseChecked.get(0).getHeuristic();
//                        pointToPush = caseChecked.get(0);
//                    }
                    if((caseChecked.get(i).getHeuristic() < min || i == 0) &&
                        (caseChecked.get(i).getX() - currentPoint.getX() <= 1) &&
                        (caseChecked.get(i).getY() - currentPoint.getY() <= 1) &&
                        caseChecked.get(i).isIfValide() &&
                            (caseChecked.get(i).getX() - currentPoint.getX() == 0 ||
                            caseChecked.get(i).getY() - currentPoint.getY() == 0)
                    ) {
                        System.out.println("IN CASECHECKED p2.getX() - currentPoint.getX() : " + (caseChecked.get(i).getX() - currentPoint.getX()));
                        System.out.println("IN CASE CHECKED p2.getY() - currentPoint.getY() : "+(caseChecked.get(i).getY() - currentPoint.getY()));
                        System.out.println("Current point BEFORE: "+(currentPoint.getCoord()));
                        if(caseChecked.get(i).equals(endPoint)) {
                            pathFind = true;
                            System.out.println("FINISH");
                            for(Point p3 : rightPath) {System.out.println(p3.getCoord());}
                            return;
                        }
                        min = caseChecked.get(i).getHeuristic();
                        pointToPush = caseChecked.get(i);
                    }
                }
                for(Point p3 : caseChecked) {
                    System.out.println("case checked : "+ p3.getCoord());
                }
                if(!rightPath.contains(pointToPush)) {
                    rightPath.add(pointToPush);
                    currentPoint = pointToPush;
                }
                System.out.println("Current point AFTER: "+(currentPoint.getCoord()));
                for(Point p3 : rightPath) {
                    System.out.println(p3.getCoord());
                }
            }
        }
    }
}
