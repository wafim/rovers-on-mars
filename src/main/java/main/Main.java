package main;

import service.RoverService;
import service.RoverServiceImpl;

public class Main {

    public static void main(String [] args) {
        RoverService roverService =new RoverServiceImpl();

        try {
            roverService.runRoversWithFileCommandeLine(args[0]);
            } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
