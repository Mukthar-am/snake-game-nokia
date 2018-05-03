package org.muks.snake.businessobjects;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Board {
    private Logger LOG = LoggerFactory.getLogger(Board.class);

    private int ROWS, COLS;
    Cell[][] BOARD;
    Snake SNAKE;
    private Cell FOOD_CELL = null;


    public Board init(int rowCount, int columnCount) {
        this.ROWS = rowCount;
        this.COLS = columnCount;

        this.BOARD = new Cell[this.ROWS][this.COLS];
        for (int row = 0; row < this.ROWS; row++) {
            for (int column = 0; column < this.COLS; column++) {
                this.BOARD[row][column] = new Cell(row, column);
            }
        }


        /** init snake as well, over the board */
        Cell snakeInitCell = new Cell(0, 0, true);

        this.SNAKE = new Snake(snakeInitCell);
        this.BOARD[0][0] = snakeInitCell;

        return this;
    }

    public Snake getSnake() {
        return this.SNAKE;
    }

    public void moveSnake(Cell nextCell) {
        LOG.info("Moving to - (" + nextCell.getRow() + ", " + nextCell.getColumn() + ")");

        Cell moveToCell = this.BOARD[nextCell.getRow()][nextCell.getColumn()];
        moveToCell.setPartOfSnakeBody();
        if (moveToCell.equals(this.FOOD_CELL) || nextCell.isFoodCell())
            moveToCell.setFood();

        if (!moveToCell.isFoodCell()) {
            Cell snakeTail = this.SNAKE.getTail();
            this.BOARD[snakeTail.getRow()][snakeTail.getColumn()].release();
        }


        boolean moveSuccessful;
        moveSuccessful = this.SNAKE.move(moveToCell);

        if (moveSuccessful) {
            this.BOARD[moveToCell.getRow()][moveToCell.col] = moveToCell;

            if (moveToCell.isFoodCell()) {
                moveToCell.eatFood();
                this.FOOD_CELL = null;
            }

        } else {
            LOG.info("\n=== GAME OVER === \nYou crashed into yourself...!");
            System.exit(0);
        }
    }

    public boolean hasFood() {
        boolean foodFound = (this.FOOD_CELL == null) ? false : true;
        LOG.info("Food found:- " + foodFound);
        return foodFound;
    }


    public void generateFood() {
        int row = new Random().nextInt(this.ROWS);
        int column = new Random().nextInt(this.COLS);
        LOG.info("Generating food at (x,y): (" + row + "," + column + ")");

        this.FOOD_CELL = new Cell(row, column);

        this.BOARD[row][column].setFood();
    }

    public void generateFood(int row, int column) {
        FOOD_CELL = new Cell(row, column);

        LOG.info("Generating food at (x,y): (" + row + "," + column + ")");
        this.BOARD[row][column].setFood();
    }

    public Cell[][] getBoard() {
        return this.BOARD;
    }

    public String toString() {
        StringBuilder board = new StringBuilder();
        int rows = this.BOARD.length;
        int columns = this.BOARD[0].length;

        board.append("\n-----------------------------------------\n");
        for (int r = 0; r < rows; r++) {
            board.append("|");
            for (int c = 0; c < columns; c++) {

                board.append(this.BOARD[r][c].toString());

                if (c != columns - 1)
                    board.append(" ");
            }
            board.append("|");
            board.append("\n");
        }
        board.append("-----------------------------------------");

        return board.toString();
    }
}
