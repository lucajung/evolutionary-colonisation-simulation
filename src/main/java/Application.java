import ecs.Simulation;
import ecs.controller.ApplicationController;
import ecs.controller.MapController;
import ecs.mapgenerator.MapGenerator;
import ecs.model.Biome;

import java.io.IOException;

class Application
{
    public static void main(String[] args) throws IOException {
        ApplicationController applicationController = new ApplicationController();
        MapController mapController = applicationController.mapController;
        mapController.createMap(700, 700);
        new Simulation(mapController.getConvertedMap());
    }
}