package ecs.controller;

import ecs.model.Application;

public class ApplicationController {

    public final Application application;
    public final CreatureController creatureController;
    public final MapController mapController;
    public final IOController ioController;

    public ApplicationController() {
        this.application = new Application();
        this.creatureController = new CreatureController(this);
        this.mapController = new MapController(this);
        this.ioController = new IOController(this);
    }

}
