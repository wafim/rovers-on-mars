package service;



import domain.*;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class RoverServiceImpl implements RoverService {
    @Override
    public Rover create(String name, Grid grid, char orientation, String action) throws Exception {
        if (grid.getX() < 0 || grid.getY() < 0) {
            throw new Exception("Rover's grid can't be negative.");
        } else if (!Orientation.contains(String.valueOf(orientation))) {
            throw new Exception("Rover's orientation must be 'N', 'E', 'S' or 'W'");
        }
        return new Rover(name, grid, Orientation.valueOf(String.valueOf(orientation)), action);
    }

    @Override
    public boolean canMove(Rover rover, Plateau plateau) {

        Grid currentGrid = new Grid(rover.getGrid().getX(), rover.getGrid().getY());
        if (rover.getOrientation().equals(Orientation.N) && currentGrid.getY() + 1 > plateau.getGrid().getY() || occupiedPosition(new Grid(currentGrid.getX(),currentGrid.getY() + 1), plateau))
            return false;
        else if (rover.getOrientation().equals(Orientation.E) && currentGrid.getX() + 1 > plateau.getGrid().getX() || occupiedPosition(new Grid(currentGrid.getX() + 1,currentGrid.getY()), plateau))
            return false;
        else if (rover.getOrientation().equals(Orientation.S) && currentGrid.getY() - 1 < 0 || occupiedPosition(new Grid(currentGrid.getX(),currentGrid.getY() - 1), plateau)) return false;
        else if (rover.getOrientation().equals(Orientation.W) && currentGrid.getX() - 1 < 0 || occupiedPosition(new Grid(currentGrid.getX() -1,currentGrid.getY()), plateau)) return false;
        return true;
    }

    private boolean occupiedPosition(Grid currentGrid, Plateau plateau) {

      boolean occupiedPosition = false;

      if(plateau.getRovers().stream().anyMatch(rover -> rover.getGrid().getX() == currentGrid.getX() && rover.getGrid().getY() == currentGrid.getY())){
          System.out.println("Position " +currentGrid.getX()+ ","+ currentGrid.getY()+  " is already occupied ");
          occupiedPosition=  true;
      }
      return occupiedPosition;
    }

    @Override
    public Rover move(Rover rover) {
        Grid grid = rover.getGrid();
        switch (rover.getOrientation()) {

            case N:
                grid.setY(rover.getGrid().getY() + 1);
                break;
            case E:
                grid.setX(grid.getX() + 1);
                break;
            case S:
                grid.setY(grid.getY() - 1);
                break;
            case W:
                grid.setX(grid.getX() - 1);
                break;
        }
        return rover;
    }

    @Override
    public Rover rotate(Rover rover, char rotation) throws Exception {

        if (!Rotation.contains(String.valueOf(rotation))) {
            throw new Exception("Rotation must be 'R' or 'L'");
        } else if (Rotation.valueOf(String.valueOf(rotation)).equals(Rotation.R)) {
            rover.setOrientation(moveToRight(rover.getOrientation()));
        } else {
            rover.setOrientation(moveToLeft(rover.getOrientation()));
        }

        return rover;
    }


    private static Orientation moveToLeft(Orientation orientation) {
        if (orientation.equals(Orientation.N)) return Orientation.W;
        if (orientation.equals(Orientation.W)) return Orientation.S;
        if (orientation.equals(Orientation.S)) return Orientation.E;
        return Orientation.N;
    }


    private static Orientation moveToRight(Orientation orientation) {
        if (orientation.equals(Orientation.N)) return Orientation.E;
        if (orientation.equals(Orientation.E)) return Orientation.S;
        if (orientation.equals(Orientation.S)) return Orientation.W;
        return Orientation.N;

    }

    public Optional roverExecution(Plateau plateau, Rover rover) throws Exception {

            for (int i = 0; i < rover.getAction().length(); i++) {
                if (rover.getAction().charAt(i) == 'M') {
                    if (canMove(rover, plateau)) {
                        rover = move(rover);
                    }
                } else {
                    rover = rotate(rover, rover.getAction().charAt(i));
                }
            }
        plateau.addRover(rover);
        return Optional.of(rover);
    }

    public void runRoversWithFileCommandeLine(String file) throws Exception {


        PlateauService plateauService = new PlateauServiceImpl();
        Scanner reader = new Scanner(new FileInputStream(file));
        List<String> lines = new ArrayList<>();
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());

        }
        if(!lines.isEmpty()){
            char []  lignePlateau = lines.get(0).replaceAll("\\s","").toCharArray();
            Plateau plateau = plateauService.create(new Grid(Integer.parseInt(String.valueOf(lignePlateau[0])),Integer.parseInt(String.valueOf(lignePlateau[1]))));
            for(int i = 1 ; i < lines.size(); i = i + 2){

                int j = 1;
                char [] ligneGrid = lines.get(i).replaceAll("\\s","").toCharArray();
                Grid gridRover = new Grid(Integer.parseInt(String.valueOf(ligneGrid[0])), Integer.parseInt(String.valueOf(ligneGrid[1])));

                Orientation orientation = Orientation.valueOf(String.valueOf(ligneGrid[2]));

                String actions = lines.get(i+1).replaceAll("\\s","");
                Rover rover = new Rover("Rover"+j, gridRover, orientation, actions);
                Optional finalPositionRover = roverExecution(plateau, rover);

                if (finalPositionRover.isPresent()){
                    rover = (Rover) finalPositionRover.get();
                    System.out.println(rover.getGrid().getX()+" "+rover.getGrid().getY()+" "+rover.getOrientation().name());
                }
                j ++;
            }

        }
    }
}
