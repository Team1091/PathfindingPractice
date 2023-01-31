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

    private int generationNum;

    private List<Vec2d> cardinalDirections;

    private String[][] solutionMap;

    private int solutionCost;

    public HarshSuperSolver(Vec2d startPos, Vec2d endPos, boolean[][] map) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.map = map;
        frontierNodes = new ArrayList<>();

        wait = new Scanner(System.in);
        generationNum = 0;

        cardinalDirections = new ArrayList<>();
        cardinalDirections.add(new Vec2d(1,0));
        cardinalDirections.add(new Vec2d(-1,0));
        cardinalDirections.add(new Vec2d(0,1));
        cardinalDirections.add(new Vec2d(0,-1));

        solutionCost = 0;
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
            generationNum += 1;

            nextFrontier = new ArrayList<>();

            while (frontierNodes.size() > 0) {
                var point = frontierNodes.get(0);

                var temp = findPathAroundPoint(point);

                costMap[point.x][point.y] = findLowestAdjacentCost(point) + 1;

                //System.out.println(temp);

                for (Vec2d i : temp) {
                    nextFrontier.add(i);
                }


                frontierNodes.remove(0);

                foundPath = lookForEnd(endPos);
                if (foundPath) break;

            }

            //printCostMap();
            //wait.nextLine();
        }
        //back track

        Vec2d position = endPos.getCopy();
        int costCounter = 0;


        //not doing solutionMap = costMap because I am not sure if int[][] is a primitive type
        //I DO NOT want a pointer between the 2
        solutionMap = new String[costMap.length][costMap[0].length];
        for (int i = 0; i < costMap.length; i++) {
            for (int j = 0; j < costMap[0].length; j++) {
                if (costMap[i][j] <= costMap.length*costMap[0].length) {
                    solutionMap[i][j] = "" + costMap[i][j];
                } else {
                    solutionMap[i][j] = "█";
                }

            }
        }


        while (!position.isSameAs(startPos)) {
            position = getLowestDirection(position);
            costCounter += 1;
            solutionMap[position.x][position.y] = "_";
            //printFinalSolution();
            //wait.nextLine();
        }

        solutionMap[startPos.x][startPos.y] = "S";
        solutionMap[endPos.x][endPos.y] = "E";

        solutionCost = costCounter;

        System.out.println("\n\nFound final solution of cost " + costCounter);


    }

    public Vec2d getLowestDirection(Vec2d point) {
        var lowestCost = Integer.MAX_VALUE;
        var lowestCoordinate = point;
        for (Vec2d direction : cardinalDirections) {
            var xSum = point.x + direction.x;
            var ySum = point.y + direction.y;
            if (xSum < costMap.length && ySum < costMap[0].length && xSum >= 0 && ySum >= 0) {//in map bounds
                if (costMap[xSum][ySum] != 0 && costMap[xSum][ySum] <= lowestCost) {
                    lowestCost = costMap[xSum][ySum];
                    lowestCoordinate = new Vec2d(xSum, ySum);
                }

                if (startPos.isSameAs(new Vec2d(xSum, ySum))) {
                    lowestCost = Integer.MIN_VALUE;
                    lowestCoordinate = new Vec2d(xSum, ySum);
                }
            }
        }

        return lowestCoordinate;
    }

    public boolean lookForEnd(Vec2d endPoint) {


        boolean returner = false;

        for (Vec2d direction : cardinalDirections) {
            var xSum = endPoint.x + direction.x;
            var ySum = endPoint.y + direction.y;
            if (xSum < costMap.length && ySum < costMap[0].length && xSum >= 0 && ySum >= 0) {//in map bounds
                if (costMap[xSum][ySum] != 0 && costMap[xSum][ySum] <= costMap.length * costMap[0].length) {
                    //System.out.println("Found a path touching endpoint");
                    returner = true;
                    //wait.nextLine();

                }
            }
        }

        return returner;
    }

    public int findLowestAdjacentCost(Vec2d point) {
        var lowestCost = Integer.MAX_VALUE;

        if (point.isSameAs(startPos)) {
            return -1;
        }

        if (generationNum == 2) {
            return 0;
        }

        if (point.x + 1 < costMap.length && costMap[point.x + 1][point.y] < lowestCost && costMap[point.x + 1][point.y] != 0) {
            //System.out.println("south");
            if (costMap[point.x + 1][point.y] > 0) lowestCost = costMap[point.x + 1][point.y];
        }
        if (point.x - 1 >= 0 && costMap[point.x - 1][point.y] < lowestCost && costMap[point.x - 1][point.y] != 0) {
            //System.out.println("north");
            if (costMap[point.x - 1][point.y] > 0) lowestCost = costMap[point.x - 1][point.y];

        }
        if (point.y + 1 < costMap.length && costMap[point.x][point.y + 1] < lowestCost && costMap[point.x][point.y + 1] != 0) {
            //System.out.println("east");
            if (costMap[point.x][point.y + 1] > 0) lowestCost = costMap[point.x][point.y + 1];

        }
        if (point.y - 1 >= 0 && costMap[point.x][point.y - 1] < lowestCost && costMap[point.x][point.y - 1] != 0) {
            //System.out.println("west");
            if (costMap[point.x][point.y - 1] > 0) lowestCost = costMap[point.x][point.y - 1];

        }

        //System.out.println("Finding cost for " + point + "\t Cost is " + lowestCost);

        return  lowestCost;
    }

    public List<Vec2d> findPathAroundPoint(Vec2d point) {
        var returner = new ArrayList<Vec2d>();

        if (point.x + 1 < costMap.length && costMap[point.x + 1][point.y] == 0) {
            returner.add(new Vec2d(point.x + 1, point.y));
        }
        if (point.x - 1 >= 0 && costMap[point.x - 1][point.y] == 0) {
            returner.add(new Vec2d(point.x - 1, point.y));
        }
        if (point.y + 1 < costMap[0].length && costMap[point.x][point.y + 1] == 0) {
            returner.add(new Vec2d(point.x, point.y + 1));
        }
        if (point.y - 1 >= 0 && costMap[point.x][point.y - 1] == 0) {
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
                    costMap[i][j] = Integer.MAX_VALUE;
                } else {
                    costMap[i][j] = 0;
                }

                if (i == startPos.x && j == startPos.y) {
                    costMap[i][j] = 0;
                } else if (i == endPos.x && j == endPos.y) {
                    costMap[i][j] = 0;
                }
            }
        }
    }

    public void printCostMap() {

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

    public void printFinalSolution() {
        System.out.println("HarshSuperSolver got this solution in " + generationNum + " attempts!");

        if (solutionMap != null) {
            for (String[] row : solutionMap) {
                for (String character : row) {
                    character += "\t";
                    System.out.print(character);
                }
                System.out.println("");
            }
        }

        System.out.println("The solution cost is " + solutionCost);
    }
}
