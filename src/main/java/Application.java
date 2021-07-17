import ecs.Constants;
import ecs.Simulation;
import ecs.controller.ApplicationController;
import ecs.controller.MapController;

import java.io.IOException;

class Application
{
    public static void main(String[] args) throws IOException {
        ApplicationController applicationController = new ApplicationController();
        MapController mapController = applicationController.mapController;
        mapController.createMap(Constants.MAP_X_TILES, Constants.MAP_Y_TILES);
        new Simulation(mapController.getConvertedMap());
    }
}