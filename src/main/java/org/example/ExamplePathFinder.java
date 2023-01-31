package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExamplePathFinder {

    private Vec2d startPos;
    private Vec2d endPos;
    private boolean[][] map;
    private int[][] costMap;

    private List<Vec2d> frontierNodes;

    public ExamplePathFinder(Vec2d startPos, Vec2d endPos, boolean[][] map) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.map = map;
        frontierNodes = new ArrayList<>();
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



        while(!foundPath) {
            frontierNodes = findAllFrontierPoints();

            //do calculations with frontier nodes
            //make sure the cost map reflects how many moves it takes to get there.
            //a value of 10 means you have to move 10 times...
            //initally the cost map will be all 0's, you need to fill it up in here

            for (Vec2d point : frontierNodes) {
                //go through every point on the frontier node

            }

            foundPath = false;
            //if you find the path, set this to true

        }

    }

    public List<Vec2d> findAllFrontierPoints() {

        //TODO implement
        //make this class return all the points that we are exploring
        return new ArrayList<>();
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

        for (int x = 0; x < costMap.length; x++) {
            for (int y = 0; y < costMap[0].length; y++) {

                String character = String.valueOf(costMap[x][y]);
                if (map[x][y]) {
                    character = "X ";
                }
                if (x == startPos.x && y == startPos.y) {
                    character = "S ";
                } else if (x == endPos.x && y == endPos.y) {
                    character = "E ";
                }

                System.out.print(character);
            }
            System.out.println("\t\t" + x);
        }

    }
}
