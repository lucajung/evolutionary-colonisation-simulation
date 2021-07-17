package ecs;

import ecs.controller.ApplicationController;
import ecs.controller.CreatureController;
import ecs.model.Creature;

public class SimulationBackend {

    ApplicationController applicationController;
    CreatureController creatureController;

    public SimulationBackend(ApplicationController applicationController)
    {
        this.applicationController = applicationController;
        this.creatureController = applicationController.creatureController;
    }

    /*
     * Temp dem method
     */
    public void move() {
        for(Creature creature : creatureController.getCreatures())
        {
            creature.positionX += 1;
        }
    }
}
