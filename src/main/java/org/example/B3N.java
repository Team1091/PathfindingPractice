package org.example;

import java.util.*;

public class B3N {

    private Vec2d startPos;
    private Vec2d endPos;
    private boolean[][] map;
    private int[][] costMap;

    //   private List<Vec2d> frontierNodes;

    public B3N(Vec2d startPos, Vec2d endPos, boolean[][] map) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.map = map;
        // frontierNodes = new ArrayList<>();
    }


    public void generateSolution() {
        //Start at startPos and look in the 4 directions
        //Then assign cost values to those
        startTheCostMap();

        // initialize the frontier with the start node
        List<Vec2d> fronteir = new ArrayList<>();
        fronteir.add(startPos);
        // make a list of closed nodes that we dont need to look at
        List<Vec2d> closed = new ArrayList<>();
        // make a grid of parents nodes to follow back
        Vec2d[][] parent = new Vec2d[map.length][map[0].length];

        // while there is something in the frontier
        while (!fronteir.isEmpty()) {
            var closestNode = fronteir.stream().min(Comparator.comparingInt((p) -> costMap[p.x][p.y])).get();

            if (closestNode.x == endPos.x && closestNode.y == endPos.y) {
                List<Vec2d> result = new ArrayList<>();
                Vec2d current = closestNode;
                while (current != null) {
                    System.out.println(current);
                    current = parent[current.x][current.y];
                }

                return;
            }

            fronteir.remove(closestNode);
            closed.add(closestNode);
            var expanded = expand(closestNode).stream().filter((it) -> !closed.contains(it)).toList();
            for (Vec2d node : expanded) {
                System.out.println(node);
                if (!map[node.x][node.y]) {
                    fronteir.add(node);
                    costMap[node.x][node.y] = costMap[closestNode.x][closestNode.y] + 1;
                    parent[node.x][node.y] = closestNode;
                }


            }


        }
        // if it gets here we cant find it


    }

    private List<Vec2d> expand(Vec2d closestNode) {
        return Arrays.asList(
                        new Vec2d(closestNode.x + 1, closestNode.y),
                        new Vec2d(closestNode.x - 1, closestNode.y),
                        new Vec2d(closestNode.x, closestNode.y + 1),
                        new Vec2d(closestNode.x, closestNode.y - 1)

                ).stream()
                .filter((it) -> it.x > 0 && it.x < costMap.length && it.y > 0 && it.y < costMap[0].length)
                .toList();
    }


    public void startTheCostMap() {
        //this function starts the cost map
        costMap = new int[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                costMap[i][j] = 99999;

                if (i == startPos.x && j == startPos.y) {
                    costMap[i][j] = 0;
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
