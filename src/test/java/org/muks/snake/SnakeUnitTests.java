package org.muks.snake;


import org.muks.snake.businessobjects.Board;
import org.muks.snake.businessobjects.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 * Solve the well known snake game.
 * Lazy snake tries to eat the offered food with out cutting its body in minimal movement.
 * <p>
 * Representation is purely for understanding.
 * Please design the game and make the necessary modifications to the test case.
 * You are encouraged to add relevant new test cases
 * <p>
 * Following are few of the conventions
 * Head of the Snake is marked as H, Body and tail is marked as X
 * Food is indicated by F
 */
public class SnakeUnitTests {
    private static Logger LOG = LoggerFactory.getLogger("TestLog");


    private Board BOARD;
    private int SNAKE_START_POS = 0;
    private int SNAKE_END_POS = 0;

    private int BOARD_X = 20;
    private int BOARD_Y = 20;

    @BeforeTest
    public void preTest() {
        MDC.put("logFileName", "testing");

        /** snake start position */
        this.BOARD = new Board().init(this.BOARD_X, this.BOARD_Y);  /** init board */
        LOG.info(this.BOARD.toString());
    }


    /**
     * Compare board cells, to check the equals() and hashCode() implementation
     */
    @Test
    public void CellCompareTest() {
        Cell firstCell = new Cell(0, 0);
        Cell secondCell = new Cell(0, 0);

        LOG.info("Length of snake: " + this.BOARD.getSnake().getLength());
        Assert.assertEquals(true, firstCell.equals(secondCell));
    }


    @Test
    public void MoveWithFoodIncreaseLengthTest() {
        LOG.info("Original length of the snake=" + this.BOARD.getSnake().getLength());
        Cell nextCell = new Cell(0, 1, true, true);
        this.BOARD.moveSnake(nextCell);

        nextCell = new Cell(0, 2, true, true);
        this.BOARD.moveSnake(nextCell);

        int expectedLength = 3;
        int actualLength = this.BOARD.getSnake().getLength();

        LOG.info("Actual Length: " + actualLength);
        Assert.assertEquals(actualLength, expectedLength);
    }


    @Test
    public void MoveWithoutFoodTest() {
        LOG.info("Original length of the snake=" + this.BOARD.getSnake().getLength());
        Cell nextCell = new Cell(0, 1, true, false);
        this.BOARD.moveSnake(nextCell);

        int expectedLength = 1;
        int actualLength = this.BOARD.getSnake().getLength();

        LOG.info("Actual Length: " + actualLength);
        Assert.assertEquals(actualLength, expectedLength);
    }

    @Test
    public void IncreasingLengthTest() {
        LOG.info("Start(): " + this.BOARD.getSnake().getLength());
        LOG.info("Snake (orig): " + this.BOARD.getSnake().toString() + "\n");

        Cell nextCell = new Cell(0, 1, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(1, 1, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());


        nextCell = new Cell(2, 1, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(3, 1, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(3, 2, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(3, 3, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());


        nextCell = new Cell(2, 3, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());


        nextCell = new Cell(1, 3, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(0, 3, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());


        nextCell = new Cell(0, 2, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info("Snake now1(): " + this.BOARD.toString());

    }


    /**
     * Assert based on successful crash of the snake to either of its body parts including head and tail of snake
     */
    @Test
    public void SnakeCrashGameOverTest() {
        LOG.info("Start(): " + this.BOARD.getSnake().getLength());
        LOG.info("Snake (orig): " + this.BOARD.getSnake().toString() + "\n");

        Cell nextCell = new Cell(0, 1, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(1, 1, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());


        nextCell = new Cell(2, 1, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(2, 2, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(2, 3, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(2, 4, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(1, 4, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());


        nextCell = new Cell(0, 4, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(0, 3, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());

        nextCell = new Cell(0, 2, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());


        nextCell = new Cell(0, 1, true, true);
        this.BOARD.moveSnake(nextCell);
        LOG.info(this.BOARD.toString());
    }

}
