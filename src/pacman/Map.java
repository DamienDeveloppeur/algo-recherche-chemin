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
        for(int i =0; i < this.sizeMapHeight; i++) {
            for (int j = 0; j < this.sizeMapWidth; j++) {
                boolean wall = true;
                if(j == 3) {
                    if (i == 1 || i == 2 || i == 3) {
                        wall = false;
                    }
                }
                if(j == 2 && i == 3) wall = false;
                if(j == 1 && i == 3) wall = false;
                if(j == 0 && i == 1) wall = false;

                p[i][j] = new Point(i, j, j+i, wall);
                listNoeud.add(p[i][j]);
            }
        }
        //startPoint = p[this.randInt()][this.randInt()];
        endPoint = p[4][3];
         startPoint = p[0][0];
        //System.out.println(endPoint);
//        while(endPoint == null){
//            if(p[this.randInt()][this.randInt()].isIfValide()) endPoint = p[this.randInt()][this.randInt()];
//        }

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
//            for(Point p2 : listNoeud) {
//                System.out.println(p2.getCoord());
//            }


            boolean caseFinded = false;
//            for(Point p3 : rightPath) {
//                System.out.println(p3.getCoord());
//            }
            caseChecked.clear();
            // on check chaque case, on place ainsi chaque case checké dans une liste
            // on a un point de tête, bloquing queue
            // on compare l'heuristique de chaque
            for(Point p2 : listNoeud){
                if(!rightPath.contains(p2)) {
                    if(p2.getHeuristic() < currentPoint.getHeuristic() &&
                            p2.getX() - currentPoint.getX() <= 1 &&
                            p2.getY() - currentPoint.getY() <= 1 &&
                            p2.isIfValide() &&
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
                System.out.println("case checkec 0 : " + caseChecked.get(0).getHeuristic());

                float min =  caseChecked.get(0).getHeuristic();
                Point pointToPush = caseChecked.get(0);
                for(int i = 0; i < caseChecked.size(); i++) {
                    if(caseChecked.get(i).getHeuristic() < min &&
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
        // code en python
        /**
         * frontier = PriorityQueue()
         * frontier.put(start, 0)
         * came_from = dict()
         * cost_so_far = dict()
         * came_from[start] = None
         * cost_so_far[start] = 0
         *
         * while not frontier.empty():
         *    current = frontier.get()
         *
         *    if current == goal:
         *       break
         *
         *    for next in graph.neighbors(current):
         *       new_cost = cost_so_far[current] + graph.cost(current, next)
         *       if next not in cost_so_far or new_cost < cost_so_far[next]:
         *          cost_so_far[next] = new_cost
         *          priority = new_cost + heuristic(goal, next)
         *          frontier.put(next, priority)
         *          came_from[next] = current
         */
    }
    /**
     *  XXX0X
     *  XXXXX
     *  XXXXX
     *  X0XXX
     *  XXXXX
     */
}
