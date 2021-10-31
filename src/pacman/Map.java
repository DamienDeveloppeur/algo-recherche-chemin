package pacman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Map {
    private ArrayList<String> map = new ArrayList<>();

    private HashMap caseChecked = new HashMap();
    private HashMap rightPath = new HashMap();
    private float sizeRightPath;
    private int sizeMapWidth;
    private int sizeMapHeight;
    static Point startPoint;
    static Point endPoint;
    private Point[][] p;
    private boolean pathFind = false;
    private Point currentPoint;
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
                p[i][j] = new Point(i, j, j+i, true);
            }
        }
        //startPoint = p[this.randInt()][this.randInt()];
        //endPoint = p[this.randInt()][this.randInt()];
         startPoint = p[0][0];
        endPoint = p[this.randInt()][this.randInt()];
        System.out.println(Map.endPoint.getCoord());
    }

    public int randInt() {
        return ThreadLocalRandom.current().nextInt(0, this.sizeMapWidth);
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
    // System.out.println(startPoint.getHeuristic());
        while(rightPath.size() != sizeRightPath){
            // on check chaque case, on place ainsi chaque case checké dans une liste
            // on a un point de tête, bloquing queue
            // on compare l'heuristique de chaque
            for(int i =0; i < this.sizeMapHeight; i++) {
                for (int j = 0; j < this.sizeMapWidth; j++) {

                    p[i][j] = new Point(i, j, j+i, true);
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