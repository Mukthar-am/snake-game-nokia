package org.muks.snake.businessobjects;

import java.util.Comparator;
import java.util.Objects;

public class Cell implements Comparator<Cell> {
    public final static int CELL_TYPE_EMPTY = 0;
    public final static int CELL_TYPE_FOOD = 10;
    public final static int CELL_TYPE_SNAKE_NODE = 20;

    public final int row, col;
    /**
     * cell co-ordinates in terms of (x, y)
     */
    public int type;
    private boolean isFoodCell = false;

    private boolean isSnakeBody = false;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Cell(int row, int col, boolean isPartOfSnakeBody) {
        this.row = row;
        this.col = col;
        this.isSnakeBody = isPartOfSnakeBody;
    }

    public Cell(int row, int col, boolean isPartOfSnakeBody, boolean isFoodCell) {
        this.row = row;
        this.col = col;
        this.isSnakeBody = isPartOfSnakeBody;
        this.isFoodCell = isFoodCell;
    }

    public void setPartOfSnakeBody() {
        this.isSnakeBody = true;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.col;
    }

    public void release() {
        this.isSnakeBody = false;
    }
    /**
     * Set if its good or not
     */
    public void setFood() {
        this.isFoodCell = true;

    }

    public boolean isFoodCell() {
        return this.isFoodCell;
    }

    public boolean eatFood() {
        return this.isFoodCell = false;
    }

    /**
     * Will have to implement this if we use LinkedList.contains() method. For now, equals() and hashCode() does all the checks.
     *
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Cell o1, Cell o2) {
        return 0;
    }


    /**
     * equals method comparing internal properties of the object
     *
     * Limiting it to just the x & y co-ordinates for a cell compare on the move api/method
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Cell)) {
            return false;
        }

        Cell cell = (Cell) o;

        return (cell.row == this.row && cell.col == this.col);
    }

    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.col);
    }

    public String print() {
        return "(" + this.row + ", " + this.col + ")";
    }


    public String toString() {
        if (isSnakeBody)
            return "s";
        else if (isFoodCell)
            return "(f)";
        else if (isSnakeBody && isFoodCell)
            return "sf";
        else
            return "-";
    }

}
