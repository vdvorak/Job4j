package ru.matevosyan.models;

import ru.matevosyan.exceptions.FigureNotFoundException;
import ru.matevosyan.exceptions.ImpossibleMoveException;
import ru.matevosyan.exceptions.OccupiedWayException;

/**
 * Created Board for describe Chess board.
 * Created on 27.01.2017.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Board {

    /**
     * figures is an array that hold figures.
     */

    protected Figure[] figures = new Figure[6];

    /**
     * Board constructor.
     * @param figures assign to current instance variable this.figures.
     */

    public Board(Figure[] figures) {
        this.figures = figures;
    }

    /**
     * Move method that check figure movement and return false if figure can't really move.
     * and if figure can really move than invoke clone method and move to destination cell.
     * @param source is start figure position.
     * @param dist is figure destination.
     * @return false if figure can't move or if can move to destination cell.
     * @throws ImpossibleMoveException if figure can't move.
     * @throws OccupiedWayException when figure cell is occupied.
     * @throws FigureNotFoundException call when figure not found.
     */

    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        int sourceX = source.getX();
        int sourceY = source.getY();
        int distX = dist.getX();
        int distY = dist.getY();

        if (((distX | sourceX) < 8 & (distY | sourceY) < 8) & (((distX | sourceX) >= 0 & (distY | sourceY) >= 0))) {
            int figureX = 0;
            int figureY = 0;

            int i = 0;
            do {
                figureX = this.figures[i].getPosition().getX();
                figureY = this.figures[i].getPosition().getY();
                if (figureX == sourceX && figureY == sourceY) {
                    //int j = 0;
                    //do {
                        figureX = this.figures[i].getPosition().getX();
                        figureY = this.figures[i].getPosition().getY();
                        if (figureX == sourceX || figureY == sourceY) {
                            try {
                                Cell[] figureSteps = this.figures[i].way(dist);

                                //for (Cell steps : figureSteps) {
                                    int stepX = figureSteps[figureSteps.length - 1].getX();
                                    int stepY = figureSteps[figureSteps.length - 1].getY();

                                    if (stepX == figureX && stepY == figureY) {
                                        throw new OccupiedWayException("Occupied way");
                                    } else {
                                        this.figures[i] = this.figures[i].clone(dist);
                                    }
                                //}

                            } catch (ImpossibleMoveException ime) {
                                System.out.printf("%s%n", "Impossible movement");
                            }
                        }
                        //j++;
                    //} while (j < this.figures.length);

                } else if (i < this.figures.length) {
                    i++;
                } else {
                    throw new FigureNotFoundException("Figure not found");
                }

            } while (i < this.figures.length);

        } else {
            throw new ImpossibleMoveException("Try again");
        }

        return false;
    }
}

