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
    private ArrayList<Point> caseChecked = new ArrayList<>();
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
        for(int i =0; i < this.sizeMapHeight; i++) {
            for (int j = 0; j < this.sizeMapWidth; j++) {
                Random r = new Random();
                double randomWall = r.nextInt((10) + 1) + 0;
                boolean wall = false;
                if(randomWall <= 8) wall = true;
                p[i][j] = new Point(i, j, j+i, wall);
                listNoeud.add(p[i][j]);
            }
        }
        //startPoint = p[this.randInt()][this.randInt()];
        //endPoint = p[this.randInt()][this.randInt()];
         startPoint = p[0][0];
        //System.out.println(endPoint);
        while(endPoint == null){
            if(p[this.randInt()][this.randInt()].isIfValide()) endPoint = p[this.randInt()][this.randInt()];
        }

        System.out.println(Map.endPoint.getCoord());
    }

    public int randInt() {
        return ThreadLocalRandom.current().nextInt(2, this.sizeMapWidth);
    }
    public void generateHeuristic(){
        for(int i =0; i < this.sizeMapHeight; i++) {
            for (int j = 0; j < this.sizeMapWidth; j++) {
                //System.out.println(this.p[i][j].getPoint());
                p[i][j].setHeuristic(Math.abs(p[i][j].getX() - Map.endPoint.getX()) + Math.abs(p[i][j].getY() - Map.endPoint.getY()));
                System.out.println("coord : "+ p[i][j].getCoord() + " heuristique : " + p[i][j].getHeuristic());
            }
        }
    }

    // a ce stade, quel est le chemin le plus rapide entre le startPoint et le endPoint
    // chaque case checké sera enregistré dans un map
    public void findShortestPath() {
        sizeRightPath = p[0][0].getHeuristic();
        currentPoint = startPoint;
    System.out.println("______________________________________________________");
        while(!pathFind){
            for(Point p3 : rightPath) {
                System.out.println(p3.getCoord());
            }
            currentPoint = null;
            caseChecked = null;
            // on check chaque case, on place ainsi chaque case checké dans une liste
            // on a un point de tête, bloquing queue
            // on compare l'heuristique de chaque
            for(Point p2 : listNoeud){
                // si un point est plus proche
                caseChecked.add(p2);
                if(p2.getHeuristic() < currentPoint.getHeuristic() &&
                        Math.abs(p2.getX() - currentPoint.getX()) <= 1 && Math.abs(p2.getY() - currentPoint.getY()) <= 1 &&
                        p2.isIfValide()) {
                    if(p2.equals(endPoint)) {
                        pathFind = true;
                        System.out.println("FINISH");
                        for(Point p3 : rightPath) {
                            System.out.println(p3.getCoord());

                        }
                        return;
                    }
                    currentPoint = p2;
                    rightPath.add(p2);
                }
            }
            if(currentPoint.equals(null)) {
                for(Point p3 : caseChecked){

                }
            }
        }
    }
}
