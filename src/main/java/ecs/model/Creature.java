package ecs.model;

public class Creature
{
    /*
     * The x position on the map
     */
    public int positionX;

    /*
     * The y position on the map
     */
    public int positionY;

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

    /*
     * Describes the angle of the eye relative to the body.
     * The value is >= 0 and < 360. 0 is on top and increments clockwise
     */
    public int eyeAngle;

    public Brain brain;

    public Creature()
    {
        positionX = 0;
        positionY = 0;
        hp = 100;
        age = 0;
        maxAge = 10;
        foodLevel = 100;
        waterLevel = 100;
        fitnessLevel = 0;
        eyeAngle = 0;
        brain = new Brain();
    }

    public Creature(int maxAge)
    {
        this();
        this.maxAge = maxAge;
    }

    public Creature(int positionX, int positionY)
    {
        this();
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Creature(int positionX, int positionY, int maxAge)
    {
        this();
        this.positionX = positionX;
        this.positionY = positionY;
        this.maxAge = maxAge;
    }

    //TODO: Implement
    private class Brain
    {
        public int[][] neurons;
        public double[][][] weights;

        public Brain()
        {
            this.neurons = new int[3][7];
        }
    }
}