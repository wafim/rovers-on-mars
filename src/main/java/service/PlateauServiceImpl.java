package service;


import domain.Grid;
import domain.Plateau;

public class PlateauServiceImpl implements PlateauService {
    @Override
    public Plateau create(Grid grid) throws Exception {
        if (grid.getX() < 0 || grid.getY() < 0) {
            throw new Exception("Plateau's grid can't be negative.");
        }
        return new Plateau(grid);
    }

}
