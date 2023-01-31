package org.example;

public class ExamplePathFinder {

    private Vec2d startPos;
    private Vec2d endPos;
    private boolean[][] map;
    private int[][] costMap;

    public ExamplePathFinder(Vec2d startPos, Vec2d endPos, boolean[][] map) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.map = map;
    }


    public void generateSolution() {
        //Start at startPos and look in the 4 directions
        //Then assign cost values to those

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
