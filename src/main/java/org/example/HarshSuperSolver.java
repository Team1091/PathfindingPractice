package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HarshSuperSolver {

    private Vec2d startPos;
    private Vec2d endPos;
    private boolean[][] map;
    private int[][] costMap;

    private List<Vec2d> frontierNodes;

    private Scanner wait;

    public HarshSuperSolver(Vec2d startPos, Vec2d endPos, boolean[][] map) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.map = map;
        frontierNodes = new ArrayList<>();

        wait = new Scanner(System.in);
    }


    public void generateSolution() {
        //Start at startPos and look in the 4 directions
        //Then assign cost values to those
        startTheCostMap();

        // initialize the frontier with the start node
        // make a list of closed nodes that we dont need to look at
        // make a grid of parents nodes to follow back

        // while there is something in the frontier
          // grab the closest node
          // find its neighbors
            // if we found the target then we are there, break
            // otherwise
             // add to frontier
             // add to the cost

        // set the foundPath to true


        boolean foundPath = false; //set to true when path is found

        var nextFrontier = new ArrayList<Vec2d>();
        nextFrontier.add(startPos);

        while(!foundPath) {
            frontierNodes = nextFrontier;

            nextFrontier = new ArrayList<>();

            while (frontierNodes.size() > 0) {
                var point = frontierNodes.get(0);

                var temp = findPathAroundPoint(point);

                costMap[point.x][point.y] = findLowestAdjacentCost(point) + 1;

                System.out.println(temp);

                for (Vec2d i : temp) {
                    nextFrontier.add(i);
                }



                frontierNodes.remove(0);

                if (point.isSameAs(endPos)) {
                    foundPath = true;
                }
            }

            printSolution();
            wait.nextLine();
        }
        //back track




    }

    public int findLowestAdjacentCost(Vec2d point) {
        var lowestCost = 50;

        if (point.isSameAs(startPos)) {
            return -1;
        }

        if (point.x + 1 < costMap.length && costMap[point.x + 1][point.y] < lowestCost && costMap[point.x + 1][point.y] != 0) {
            System.out.println("south");
            lowestCost = costMap[point.x + 1][point.y];
        }
        if (point.x - 1 > 0 && costMap[point.x - 1][point.y] < lowestCost && costMap[point.x - 1][point.y] != 0) {
            System.out.println("north");
            lowestCost = costMap[point.x - 1][point.y];
        }
        if (point.y + 1 < costMap.length && costMap[point.x][point.y + 1] < lowestCost && costMap[point.x][point.y + 1] != 0) {
            System.out.println("east");
            lowestCost = costMap[point.x][point.y + 1];
        }
        if (point.y - 1 > 0 && costMap[point.x][point.y - 1] < lowestCost && costMap[point.x][point.y - 1] != 0) {
            System.out.println("west");
            lowestCost = costMap[point.x][point.y - 1];
        }

        System.out.println("Finding cost for " + point + "\t Cost is " + lowestCost);

        return  lowestCost;
    }

    public List<Vec2d> findPathAroundPoint(Vec2d point) {
        var returner = new ArrayList<Vec2d>();

        if (point.x + 1 < costMap.length && costMap[point.x + 1][point.y] == 0) {
            returner.add(new Vec2d(point.x + 1, point.y));
        }
        if (point.x - 1 > 0 && costMap[point.x - 1][point.y] == 0) {
            returner.add(new Vec2d(point.x - 1, point.y));
        }
        if (point.y + 1 < costMap[0].length && costMap[point.x][point.y + 1] == 0) {
            returner.add(new Vec2d(point.x, point.y + 1));
        }
        if (point.y - 1 > 0 && costMap[point.x][point.y - 1] == 0) {
            returner.add(new Vec2d(point.x, point.y - 1));
        }

        return returner;
    }

    public void startTheCostMap() {
        //this function starts the cost map
        costMap = new int[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j]) {
                    costMap[i][j] = 99999;
                } else {
                    costMap[i][j] = 0;
                }

                if (i == startPos.x && j == startPos.y) {
                    costMap[i][j] = 0;
                } else if (i == endPos.x && j == endPos.y) {
                    costMap[i][j] = -9999;
                }
            }
        }
    }

    public void printSolution() {

        System.out.println("This is harsh's code");

        for (int x = 0; x < costMap.length; x++) {
            for (int y = 0; y < costMap[0].length; y++) {

                String character = String.valueOf(costMap[x][y]);
                if (map[x][y]) {
                    character = "X";
                }
                if (x == startPos.x && y == startPos.y) {
                    character = "S";
                } else if (x == endPos.x && y == endPos.y) {
                    character = "E";
                }

                if (character.equals("0")) {
                    character = "_";
                }

                System.out.print(character+"\t");
            }
            System.out.println("\t\t" + x);
        }

    }
}
