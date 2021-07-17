import ecs.ECSWindow;
import ecs.SimulationCanvas;
import ecs.controller.ApplicationController;


class Application
{
    public static void main(String[] args) {
        ApplicationController applicationController = new ApplicationController();
        ECSWindow window = new ECSWindow();
        window.add(new SimulationCanvas(applicationController));
    }
}