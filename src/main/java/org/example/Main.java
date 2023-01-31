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

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println("\t\t" + i);
        }


        System.out.println("Hello world!");
    }

}