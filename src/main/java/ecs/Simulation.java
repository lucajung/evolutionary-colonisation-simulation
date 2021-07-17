package ecs;

import ecs.controller.ApplicationController;

public class Simulation implements Runnable {

    ApplicationController applicationController;
    SimulationBackend simulationBackend;
    SimulationCanvas simulationCanvas;

    public Simulation() {
        this.applicationController = new ApplicationController();
        this.simulationCanvas = new SimulationCanvas(applicationController);
        this.simulationBackend = new SimulationBackend(applicationController);
        ECSWindow window = new ECSWindow();
        window.add(this.simulationCanvas);
    }

    @Override
    public void run() {
        final int TARGET_FPS = 30;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        long loopTime;

        while (true) {
            loopTime = System.nanoTime();

            this.simulationBackend.move();
            this.simulationCanvas.repaint();

            try {
                long gameTime = (loopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                Thread.sleep(gameTime);
            } catch (Exception e) {

            }
        }
    }
}