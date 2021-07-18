package ecs.model;

import java.util.Arrays;
import java.util.Random;

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

    public CreatureAction lastAction;

    public Creature()
    {
        positionX = 0;
        positionY = 0;
        hp = 50;
        age = 0;
        maxAge = 10;
        foodLevel = 100;
        waterLevel = 100;
        fitnessLevel = 0;
        eyeAngle = 0;
        brain = new Brain(3, 9);
        lastAction = CreatureAction.NONE;
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

    public CreatureAction predict(Biome biomeAtEye)
    {
        //Input Order: 0, 0, foodLevel, waterLevel, hp, eyeAngle, eyeValueWater, eyeValueFood, 0
        double[] inputVector = new double[9];
        inputVector[0] = 0;
        inputVector[1] = 0;
        inputVector[2] = foodLevel;
        inputVector[3] = waterLevel;
        inputVector[4] = hp;
        inputVector[5] = eyeAngle;
        inputVector[6] = biomeAtEye == Biome.WATER ? 1 : 0;
        inputVector[7] = biomeAtEye == Biome.LAWN ? 1 : 0;
        inputVector[8] = 0;
        double[] prediction = brain.predict(inputVector);
        int maxIndex = 0;
        double maxValue = 0;
        for (int i = 0; i < prediction.length; i++) {
            if(maxValue < prediction[i])
            {
                maxIndex = i;
                maxValue = prediction[i];
            }
        }
        return CreatureAction.values()[maxIndex];
    }

    //TODO: Implement
    private class Brain
    {
        public double[][] neurons;
        public double[][] weights;

        /*
         * Weights: [layer][...]
         */
        public Brain(int layer, int size)
        {
            if(layer < 2 || size < 1){
                throw new IllegalArgumentException();
            }
            this.neurons = new double[layer][size];
            this.weights = new double[layer - 1][size * size];
            initRandom();
        }

        private void initRandom()
        {
            Random random = new Random();
            for (int layer = 0; layer < weights.length; layer++) {
                for (int w = 0; w < weights[layer].length; w++) {
                    weights[layer][w] = random.nextDouble() - 0.5; // for a value between -0.5 and 0.5
                }
            }
        }

        public double[] predict(double[] inputVector)
        {
            if(neurons[0].length != inputVector.length)
            {
                throw new IllegalArgumentException("Input vector has not same size");
            }

            //Fill input vector
            for (int i = 0; i < inputVector.length; i++) {
                neurons[0][i] = inputVector[i];
            }

            //Forwardpass
            for (int layer = 1; layer < neurons.length; layer++) {
                int size = neurons[layer].length;
                for (int neuron = 0; neuron < size; neuron++) {
                    double sum = 0;
                    for (int neuronLastLayer = 0; neuronLastLayer < neurons[layer - 1].length; neuronLastLayer++) {
                        sum += neurons[layer - 1][neuronLastLayer] * weights[layer - 1][(neuronLastLayer * size) + neuron];
                    }
                    //Applying ReLU
                    neurons[layer][neuron] = sum < 0 ? 0 : sum;
                }
            }

            //Applying Softmax on output
            double sum = Arrays.stream(neurons[neurons.length - 1]).sum();
            for (int neuron = 0; neuron < neurons[neurons.length - 1].length; neuron++) {
                neurons[neurons.length - 1][neuron] = neurons[neurons.length - 1][neuron] / sum;
            }

            return neurons[neurons.length - 1];
        }
    }
}