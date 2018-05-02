package org.muks.snake.businessobjects;


public enum Directions {
    NONE(0), RIGHT(1), LEFT(-1), UP(2), DOWN(-2);

    private final int value;

    private Directions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}