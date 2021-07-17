import ecs.Constants;
import ecs.ECSWindow;
import ecs.Simulation;
import ecs.controller.ApplicationController;
import ecs.controller.MapController;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Application
{
    public static void main(String[] args) {
        ApplicationController applicationController = new ApplicationController();
        ECSWindow window = new ECSWindow();
        window.add(new Simulation(applicationController));
    }
}