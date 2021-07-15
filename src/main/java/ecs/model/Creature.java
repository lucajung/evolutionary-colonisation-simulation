package ecs.model;

public class Creature
{
    /*
     * The x position on the map
     */
    public float positionX;

    /*
     * The y position on the map
     */
    public float positionY;

    /*
     * The current hp.
     * If the hp drop <= 0 the creature dies.
     */
    public int hp;

    /*
     * The current age
     */
    public int age;

    /*
     * The age at which the creature will die
     */
    public int maxAge;

    /*
     * Describes the current level of food in the craeature.
     * The foodLevel is always between 0 and 100.
     * If the foodLevel drops <= 0 the crature dies.
     */
    public int foodLevel;

    /*
     * Describes the current level of water in the craeature.
     * The waterLevel is always between 0 and 100.
     * If the waterLevel drops <= 0 the crature dies.
     */
    public int waterLevel;

    /*
     * Describes the fitness of the creature.
     * The value is >= 0.
     */
    public int fitnessLevel;
}