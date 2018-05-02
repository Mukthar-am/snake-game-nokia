package org.muks.snake.game;

import org.muks.snake.businessobjects.Board;
import org.muks.snake.businessobjects.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Manager {
    private static Logger LOG = LoggerFactory.getLogger(Manager.class);

    private Board BOARD;
    private boolean GAME_IS_ON = true;
    private int BOARD_X = 20;
    private int BOARD_Y = 20;


    private int point_x;
    private int point_y;


    public static void main(String[] args) {
        new Manager().start();
    }


    private void start() {
        /** for kill -9 */
        Runtime.getRuntime().addShutdownHook(new ProcessorHook());

        initialize();


        /** scan user inputs on the snake move directions */
        Scanner sc = new Scanner(System.in);

        while (GAME_IS_ON) {
            System.out.println("\n:- Enter your move OR end the game? (r - right, u - Up, l - Left, d - down)");
            String move = sc.next().toString();
            move = move.trim();

            if (move.equalsIgnoreCase("end")) {
                LOG.info("Terminating the game now...");
                this.GAME_IS_ON = false;
            }
            else {
                Cell nextCell = direction(move);
                this.BOARD.moveSnake(nextCell);
            }

            if (!this.BOARD.hasFood()) {
                LOG.info("DOESn't have food...");
                this.BOARD.generateFood();

            }

            LOG.info(this.BOARD.toString());
        }

        sc.close();
    }

    private void initialize() {
        /** snake start position */
        LOG.info("Initializing game board....");
        this.BOARD = new Board().init(this.BOARD_X, this.BOARD_Y);  /** initialize board */

        if (!this.BOARD.hasFood())
            this.BOARD.generateFood();

        LOG.info("Setting food - " + this.BOARD.hasFood());
        //this.BOARD.generateFood(0,2);

        LOG.info("\n\nInitialized the game board: " + this.BOARD.toString());
    }



    private Cell direction(String direction) {
        LOG.info("Direction to move = " + direction);
        Cell snakeHead = this.BOARD.getSnake().getHead();

        int head_x = snakeHead.getRow();
        int head_y = snakeHead.getColumn();



        /** no need to implement backward case */
        switch (direction.toLowerCase()) {
            case "r":
                if ( (head_y + 1) < this.BOARD_Y )
                    head_y++;
                else
                    head_y = 0;

                break;

            case "u":
                if ( (head_x - 1) > 0 )
                    head_x--;
                else
                    head_x = this.BOARD_X-1;

                break;

            case "l":
                if (head_y - 1 > 0)
                    head_y--;
                else
                    head_y = this.BOARD_Y-1;

                break;

            case "d":
                if (head_x + 1 < this.BOARD_X)
                    head_x++;
                else
                    head_x = 0;

                break;

            default:
                this.point_x += 1;
                this.point_y += 1;
        }

        /** Return the next Cell as per the move chosen */
        LOG.info("Now creating move-to-cell @ (" + head_x + ", " + head_y + ")");
        return new Cell(head_x, head_y, true, false);
    }


    public class ProcessorHook extends Thread {
        @Override
        public void run() {
            System.out.println("Shutdown hook() called, shutting down now.");
        }
    }
}
