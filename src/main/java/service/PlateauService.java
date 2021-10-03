package service;


import domain.Grid;
import domain.Plateau;

public interface PlateauService {


    /**
     * @param grid
     * @return
     * @throws Exception
     */
    Plateau create(Grid grid) throws Exception;

}
