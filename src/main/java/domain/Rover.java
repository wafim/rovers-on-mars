package domain;

public class Rover {

    private String name;
    private Grid grid;
    private Orientation orientation;
    private String action;

    public Rover(String name, Grid grid, Orientation orientation, String action){

        this.name =  name;
        this.grid =  grid;
        this.orientation =  orientation;
        this.action =  action;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
