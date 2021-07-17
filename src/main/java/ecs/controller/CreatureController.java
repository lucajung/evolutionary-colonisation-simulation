package ecs.controller;

import ecs.model.Application;
import ecs.model.Creature;

import java.util.List;

public class CreatureController {

    private final ApplicationController applicationController;
    private final Application model;

    public CreatureController(ApplicationController applicationController)
    {
        this.applicationController = applicationController;
        this.model = this.applicationController.application;
    }

    public void addCreature(Creature creature)
    {
        if(creature == null)
        {
            throw new IllegalArgumentException("Creature can not be null");
        }
        model.creatures.add(creature);
    }

    public List<Creature> getCreatures()
    {
        if(model.creatures == null)
        {
            throw new RuntimeException();
        }
        return model.creatures;
    }
}
