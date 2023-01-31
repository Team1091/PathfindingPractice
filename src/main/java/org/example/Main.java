package org.example;

public class Main {
    public static void main(String[] args) {
        final boolean[][] blocks = new boolean[10][10];

        final var startPos = new Vec2d(5, 2);
        final var endPos = new Vec2d(7, 2);

        // display
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                blocks[x][y] = false;
            }
        }

        blocks[6][3] = true;
        blocks[6][1] = true;
        blocks[6][2] = true;

        System.out.println("Below is the original map");

        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[0].length; y++) {

                String character = "_\t";
                if (blocks[x][y]) {
                    character = "X\t";
                }
                if (x == startPos.x && y == startPos.y) {
                    character = "S\t";
                } else if (x == endPos.x && y == endPos.y) {
                    character = "E\t";
                }

                System.out.print(character);
            }
            System.out.println("\t\t" + x);
        }


        var example = new B3N(startPos, endPos, blocks);

        //example.generateSolution();
        //example.printSolution();

        var Harsh = new HarshSuperSolver(startPos, endPos, blocks);
        Harsh.generateSolution();
        Harsh.printSolution();

    }

}