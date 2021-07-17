package ecs.controller;

import ecs.helper.MapGenerator;
import ecs.model.Application;
import ecs.model.Biome;

import java.awt.image.BufferedImage;

public class MapController {

    private final ApplicationController applicationController;
    private final Application model;

    public MapController(ApplicationController applicationController)
    {
        this.applicationController = applicationController;
        this.model = this.applicationController.application;
    }

    public void createMap(int sizeX, int sizeY)
    {
        model.map = MapGenerator.generateMap(sizeX, sizeY);
    }

    public Biome[][] getMap()
    {
        if(model.map == null)
        {
            throw new RuntimeException("No map");
        }
        return model.map;
    }

    public BufferedImage getConvertedMap()
    {
        if(model.map == null)
        {
            throw new RuntimeException("No map");
        }
        return MapGenerator.convertToImage(model.map);
    }
}
