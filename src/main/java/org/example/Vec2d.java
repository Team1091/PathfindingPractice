package org.example;

public class Vec2d {
    final int x;
    final int y;

    Vec2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSameAs(Vec2d other) {
        return (x == other.x && y == other.y);
    }

    @Override
    public String toString(){
        return "["+x+", "+y+"]";
    }

}
