package ecs;

import ecs.controller.ApplicationController;
import ecs.controller.CreatureController;
import ecs.controller.MapController;
import ecs.model.Biome;
import ecs.model.Creature;
import ecs.model.CreatureAction;

import javax.swing.text.html.HTMLDocument;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class SimulationBackend {

    ApplicationController applicationController;
    CreatureController creatureController;
    MapController mapController;

    public SimulationBackend(ApplicationController applicationController)
    {
        this.applicationController = applicationController;
        this.creatureController = applicationController.creatureController;
        this.mapController = applicationController.mapController;
    }

    /*
     * Temp dem method
     */
    public void move() {
        Iterator<Creature> iterator = creatureController.getCreatures().iterator();
        while(iterator.hasNext()) {
            Creature creature = iterator.next();
            //CreatureAction creatureAction = creature.predict(mapController.getBiomeAt(creature.positionX, creature.positionY));
            CreatureAction creatureAction = creature.predict(Biome.LAWN);
            switch (creatureAction) {
                case MOVE_TOP -> {
                    if (creature.positionY > 0) {
                        creature.positionY -= 1;
                        creature.hp += 5;
                    }
                }
                case MOVE_RIGHT -> {
                    if (creature.positionX < Constants.BACKGROUND_MAP_IMAGE_WIDTH - 1) {
                        creature.positionX += 1;
                        creature.hp += 5;
                    }
                }
                case MOVE_BOTTOM -> {
                    if (creature.positionY < Constants.BACKGROUND_MAP_IMAGE_HEIGHT - 1) {
                        creature.positionY += 1;
                        creature.hp += 5;
                    }
                }
                case MOVE_LEFT -> {
                    if (creature.positionX > 1) {
                        creature.positionX -= 1;
                        creature.hp += 5;
                    }
                }
                case MOVE_EYE_LEFT -> {
                    if (creature.eyeAngle > 0) {
                        creature.eyeAngle -= 1;
                    } else {
                        creature.eyeAngle = 359;
                    }
                }
                case MOVE_EYE_RIGHT -> {
                    if (creature.eyeAngle < 358) {
                        creature.eyeAngle += 1;
                    } else {
                        creature.eyeAngle = 0;
                    }
                }
                case EAT -> {
                    if (creature.foodLevel < 99) {
                        creature.foodLevel += 5;
                    }
                }
                case DRINK -> {
                    if (creature.waterLevel < 99) {
                        creature.waterLevel += 5;
                    }
                }
                case NONE -> {
                }
            }
            creature.lastAction = creatureAction;
            creature.foodLevel -= 1;
            creature.waterLevel -= 1;
            creature.hp -= 1;
            if (creature.hp <= 0 || creature.foodLevel <= 0 || creature.waterLevel <= 0) {
                iterator.remove();
            }
        }

        int size = creatureController.getCreatures().size();
        if(size < 10)
        {
            addRandomCreatures(10 - size);
        }
    }

    private int getMaxHpCreature()
    {
        return creatureController.getCreatures().stream().max(Comparator.comparing(creature -> creature.hp)).get().hp;
    }

    /*
     * dev method
     */
    private void addRandomCreatures(int number)
    {
        Random random = new Random();
        for (int i = 0; i < number; i++)
        {
            Creature creature = new Creature(
                    random.nextInt(Constants.BACKGROUND_MAP_IMAGE_WIDTH),
                    random.nextInt(Constants.BACKGROUND_MAP_IMAGE_HEIGHT)
            );
            creatureController.addCreature(creature);
        }
    }
}
