package ecs.mapgenerator;

import ecs.model.Biome;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MapGenerator {

    //TODO: Implement Perlin Noise Generator

    public static Biome[][] generateMap(int sizeX, int sizeY)
    {
        if(sizeX <= 0 || sizeY <= 0)
        {
            throw new IllegalArgumentException("Map dimensions can not be negative");
        }

        Random random = new Random();
        double z = random.nextDouble();
        Biome[][] map = new Biome[sizeX][sizeY];
        for (int x = 0; x < map.length; x++)
        {
            for (int y = 0; y < map[x].length; y++)
            {
                int noise =  (int)(ImprovedNoise.noise(x / 100.0, y / 100.0, z) * 100);
                Biome biome = Biome.WATER;
                if (noise > 0) {
                    biome = Biome.SAND;
                }
                if (noise > 5) {
                    biome = Biome.LAWN;
                }
                if (noise > 50) {
                    biome = Biome.STONE;
                }
                map[x][y] = biome;
            }
        }
        return map;
    }

    public static BufferedImage convertToImage(Biome[][] map){
        if(map.length < 1 || map[0].length < 1){
            throw new IllegalArgumentException("Illegal map dimensions");
        }
        BufferedImage image = new BufferedImage(map.length,map[0].length, BufferedImage.TYPE_INT_RGB);
        Color gray = new Color(125, 125, 125);
        Color yellow = new Color(255, 200, 3);
        Color blue = new Color(3, 56, 255);
        Color green = new Color(0, 156, 21);
        for(int x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                int rgb = switch(map[x][y]){
                    case WATER -> blue.getRGB();
                    case STONE -> gray.getRGB();
                    case SAND -> yellow.getRGB();
                    case LAWN -> green.getRGB();
                };
                image.setRGB(x, y, rgb);
            }
        }
        return image;
    }
}
