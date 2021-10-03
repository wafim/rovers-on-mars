package service;



import domain.Grid;
import domain.Plateau;
import domain.Rover;

import java.util.Optional;

public interface RoverService {


    /**
     * @param name
     * @param grid
     * @param orientation
     * @param action
     * @return Rover
     * @throws Exception
     */
    Rover create(String name, Grid grid, char orientation, String action) throws Exception;


    /**
     * @param rover
     * @param plateau
     * @return true if we can move
     */
    boolean canMove(Rover rover, Plateau plateau);


    /**
     * @param rover
     * @return
     */
    Rover move(Rover rover);


    /**
     * @param rover
     * @param rotation
     * @return
     * @throws Exception
     */
    Rover rotate(Rover rover, char rotation) throws Exception;

    /**
     * @param plateau
     * @param rover
     * @return
     * @throws Exception
     */
    Optional roverExecution(Plateau plateau, Rover rover) throws Exception;


    /**
     * @param file
     * @throws Exception
     */
    public void runRoversWithFileCommandeLine(String file) throws Exception ;
}
