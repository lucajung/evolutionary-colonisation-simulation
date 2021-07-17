import ecs.Simulation;
import ecs.mapgenerator.MapGenerator;
import ecs.model.Biome;

import java.io.IOException;

class Application
{
    public static void main(String[] args) throws IOException {
        Biome[][] map = MapGenerator.generateMap(800,700);
        new Simulation(MapGenerator.convertToImage(map));
    }
}