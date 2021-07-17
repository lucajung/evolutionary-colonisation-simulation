import ecs.ECSWindow;
import ecs.Simulation;
import ecs.SimulationCanvas;
import ecs.controller.ApplicationController;


class Application
{
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        Thread thread = new Thread(simulation);
        thread.start();
    }
}