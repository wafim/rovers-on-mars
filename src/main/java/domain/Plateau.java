package domain;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

    private Grid grid;

    private List<Rover> rovers = new ArrayList<>();

    public  Plateau(Grid grid){
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public void addRover(Rover rover) {
        this.rovers.add(rover);
    }
    public void setRovers(List<Rover> rovers) {
        this.rovers = rovers;
    }
}
