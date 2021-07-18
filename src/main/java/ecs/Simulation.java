package ecs;

import ecs.controller.ApplicationController;

public class Simulation implements Runnable {

    ApplicationController applicationController;
    SimulationBackend simulationBackend;
    SimulationCanvas simulationCanvas;
    ECSWindow window;

    public Simulation() {
        this.applicationController = new ApplicationController();
        this.simulationCanvas = new SimulationCanvas(applicationController);
        this.simulationBackend = new SimulationBackend(applicationController);
        this.window = new ECSWindow();
        this.window.add(this.simulationCanvas);
    }

    @Override
    public void run() {
        final long OPTIMAL_TIME = 1000000000 / Constants.TARGET_CANVAS_FRAME_RATE;
        long loopTime;
        int frameCounter = 0;

        while (window.isShowing) {
            frameCounter++;
            loopTime = System.nanoTime();

            this.simulationBackend.move();

            if(frameCounter == Constants.REFRESH_CANVAS_FRAME) {
                this.simulationCanvas.repaint();
                frameCounter = 0;
            }

            try {
                long gameTime = (loopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                Thread.sleep(gameTime);
            } catch (Exception e) {

            }
        }
    }
}