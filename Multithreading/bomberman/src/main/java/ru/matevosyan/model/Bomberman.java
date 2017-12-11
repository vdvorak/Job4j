package ru.matevosyan.model;

import ru.matevosyan.constants.Directions;
import ru.matevosyan.control.LogicOfTheGame;

/**
 * Created by Admin on 04.12.2017.
 */
public class Bomberman extends GamePlayer {

    public Bomberman(String name, LogicOfTheGame logic, GameBoard board) {
        super(name, logic, board);
    }

    @Override
    public String getName() {
        return "Bomberman";
    }

    @Override
    public void run() {
        boolean getToPosition = false;

        CellOfGameBoard cellOfGameBoard = new CellOfGameBoard(this.getLogic().getStartCellX(),
                this.getLogic().getStartCellY(), Directions.DOWN);
        while(!getToPosition) {
            getToPosition = this.getLogic().moveTo(cellOfGameBoard);
        }
        System.out.println("Get the lock and get in the position " + "where: \n X = " + this.getCurrentCellOfGameBoard().getX()
                + " Y = " + this.getCurrentCellOfGameBoard().getY() + "\n Direction was " + cellOfGameBoard.getDirection());
    }
}
