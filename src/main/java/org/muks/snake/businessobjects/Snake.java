package org.muks.snake.businessobjects;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;


public class Snake {
    private static Logger LOG = LoggerFactory.getLogger(Snake.class);

    LinkedList<Cell> SNAKE = new LinkedList<>();
    Cell head;

    public Snake(Cell headCell) {
        head = headCell;
        SNAKE.add(head);
    }


    /**
     * Enabling unit tests: To check if the snake has grown in length or not
     * @return
     */
    public int getLength() {
        return this.SNAKE.size();
    }


    /**
     * getter - for further operations on the snake move and enabling unit test: Tail of the snake
     * @return
     */
    public Cell getTail() {
        return this.SNAKE.getLast();
    }

    public Cell get(int i) {
        return this.SNAKE.get(i);
    }

    /**
     * getter - for further operations on the snake move and enabling unit test: Head of the snake
     * @return
     */
    public Cell getHead() {
        return this.SNAKE.getFirst();
    }


    /**
     * snake traversing by moving occupied and empty cells - display of the cell shows the snake movement
     *
     * @param nextCell Handling snake eating food and growing in its length
     */
    public boolean move(Cell nextCell) {
        boolean moveSuccessful = true;

        if (!isGameAlive(nextCell))
            return false;

        moveOnWithFoodChecks(nextCell);

        return moveSuccessful;   /** -1 indicating GAME OVER, 0 ALL GOOD */
    }

    /**
     * move - based on food checks, handle snake body growth as well.
     * @param nextCell
     */
    private void moveOnWithFoodChecks(Cell nextCell) {
        SNAKE.addFirst(nextCell);

        /** keep the snake body growing when found food. */
        if (!nextCell.isFoodCell()) {
            Cell tail = SNAKE.removeLast();
            tail.type = Cell.CELL_TYPE_EMPTY;
        }
    }


    /**
     * Check if the game is still on at every move
     * @param nextCell
     * @return
     */
    private boolean isGameAlive(Cell nextCell) {
        boolean gameIsOn = true;
        for (int i = 0; i < this.SNAKE.size(); i++) {
            Cell currentCell = this.SNAKE.get(i);

            /** with the help of Comparator implementation */
            if (currentCell.equals(nextCell))
                gameIsOn = false;
        }

        LOG.info("gameIsOn: " + gameIsOn);
        return gameIsOn;
    }


    /**
     * print snake for debugging purpose
     * @return
     */
    public String toString() {
        StringBuilder snakeBody = new StringBuilder("tail -> ");
        for (int i = this.SNAKE.size()-1; i >=0 ; i--) {
            snakeBody.append(this.SNAKE.get(i).toString());

            if (i != 0) {
                snakeBody.append(" -> ");
            }
        }

        return snakeBody.append(" -> head ").toString();
    }

}
