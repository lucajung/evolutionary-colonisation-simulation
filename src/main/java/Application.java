import ecs.Simulation;

class Application
{
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        Thread thread = new Thread(simulation);
        thread.start();
    }
}