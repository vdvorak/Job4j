package ru.matevosyan.control;

import ru.matevosyan.constants.Directions;
import ru.matevosyan.model.Bomberman;
import ru.matevosyan.model.CellOfGameBoard;
import ru.matevosyan.model.GameBoard;
import ru.matevosyan.model.GamePlayer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 03.12.2017.
 */
public class LogicOfTheGame implements LogicDescription {
    private GamePlayer gamePlayer;
    private final GameBoard gameBoard;
    private Random random = new Random();

    public LogicOfTheGame(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.gamePlayer = new Bomberman("Bomberman", this, gameBoard);
//        currentCellOfGameBoard = new CellOfGameBoard(getStartCellX(), getStartCellY(), Directions.NONE);
    }

    public int getStartCellX() {
        return random.nextInt(gameBoard.getBoardSize());
    }

    public int getStartCellY() {
        return random.nextInt(gameBoard.getBoardSize());
    }

//    public CellOfGameBoard getStartCell() {
//        return currentCellOfGameBoard;
//    }


    public Directions randomDirection() {
        List<Directions> directionsList = Arrays.asList(Directions.LEFT, Directions.RIGHT,
                Directions.UP, Directions.DOWN);
        int sizeForRandom = directionsList.size() - 1;
        int index = new Random().nextInt(sizeForRandom);

        return directionsList.get(index);
    }

    @Override
    public Directions changeToOppositeDirection(Directions direction) {
        switch (direction) {
            case UP:
                return Directions.DOWN;
            case DOWN:
                return Directions.UP;
            case LEFT:
                return Directions.RIGHT;
            case RIGHT:
                return Directions.LEFT;
        }
        return direction;
    }

    @Override
    public boolean checkBorderGame(final int x, final int y) {
        boolean isInScope = false;
        final int boardSize = gameBoard.getBoardSize();

        if((x < boardSize && y < boardSize) && (x >= 0 && y >=0)) {
            isInScope = true;
        }

        return isInScope;
    }

//    @Override
    public void takeCell(CellOfGameBoard startCell) {
        if (!this.gameBoard.getGameBoard()[startCell.getX()][startCell.getY()].isLocked()) {
            this.gameBoard.getGameBoard()[startCell.getX()]
                    [startCell.getY()].lock();
        } else {
            this.takeCell(new CellOfGameBoard(getStartCellX(), getStartCellY(), startCell.getDirection()));
        }
    }

    public CellOfGameBoard changeDirection(Directions direction, int x, int y) {
        int xChanged;
        int yChanged;
        CellOfGameBoard cell = new CellOfGameBoard(x, y, direction);
        if (direction == Directions.LEFT) {
            xChanged = x - 1;
            cell = new CellOfGameBoard(xChanged, y, direction);
        } else if (direction == Directions.RIGHT) {
            xChanged = x + 1;
            cell = new CellOfGameBoard(xChanged, y, direction);
        } else if (direction == Directions.UP) {
            yChanged = y - 1;
            cell = new CellOfGameBoard(x, yChanged, direction);
        } else if (direction == Directions.DOWN) {
            yChanged = y + 1;
            cell = new CellOfGameBoard(x, yChanged, direction);
        }
        return cell;
    }


    public boolean moveTo(CellOfGameBoard cellOfGameBoard) {

        boolean getLockAndGetIn = true;
        Directions direction = cellOfGameBoard.getDirection();
        int x = cellOfGameBoard.getX();
        int y = cellOfGameBoard.getY();
        CellOfGameBoard cellOfGameBoardForUnlock = this.changeDirection(cellOfGameBoard.getDirection(), x, y);
        CellOfGameBoard tempCell = new CellOfGameBoard(x, y, direction);
        int xChanged = cellOfGameBoardForUnlock.getX();
        int yChanged = cellOfGameBoardForUnlock.getY();


        while (getLockAndGetIn) {
            if (!this.checkBorderGame(xChanged, yChanged) || this.gameBoard.getGameBoard()[xChanged][yChanged].isLocked()) {
                Directions newOppositeDirection = this.randomDirection();
                CellOfGameBoard newCellOfGameBoard = new CellOfGameBoard(x, y, newOppositeDirection);
                this.moveTo(newCellOfGameBoard);
            } else {

                try {

                    if (this.gamePlayer.getBoard().getGameBoard()[xChanged][yChanged].tryLock(500, TimeUnit.MICROSECONDS)) {
                        this.gamePlayer.getBoard().getGameBoard()[tempCell.getX()][tempCell.getY()].unlock();
                        System.out.println("Step to lock " + xChanged + " " + yChanged);
                        System.out.println("and unlock " + tempCell.getX() + " " + tempCell.getY());
                        this.gamePlayer.setCellOfGameBoard(new CellOfGameBoard(xChanged, yChanged, Directions.NONE));
                        getLockAndGetIn = false;
                    } else {
                        Directions newOppositeDirection = this.randomDirection();
                        CellOfGameBoard newCellOfGameBoard = new CellOfGameBoard(x, y, newOppositeDirection);
                        this.moveTo(newCellOfGameBoard);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return getLockAndGetIn;
    }
}
