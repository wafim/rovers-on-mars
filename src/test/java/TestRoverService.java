import domain.Grid;
import domain.Orientation;
import domain.Plateau;
import domain.Rover;
import org.junit.Test;
import service.PlateauService;
import service.PlateauServiceImpl;
import service.RoverService;
import service.RoverServiceImpl;

import java.util.Optional;

public class TestRoverService {


@Test
 public void testRoverExecution () throws Exception {

 PlateauService plateauService = new PlateauServiceImpl();
 RoverService roverService = new RoverServiceImpl();

 Plateau plateau = plateauService.create(new Grid(5,5));

 Rover rover = roverService.create("rover1",new Grid(1,2), 'N', "LMLMLMLMM");

 Optional movedRover = roverService.roverExecution(plateau, rover);

 rover = movedRover.isPresent() ? (Rover) movedRover.get() : null;

 assert rover.getGrid().getX() == 1;
 assert rover.getGrid().getY() == 3;
 assert rover.getOrientation().name().equals(String.valueOf("N"));

}
}
