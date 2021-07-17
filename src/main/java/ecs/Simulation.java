package ecs;

import ecs.controller.ApplicationController;
import ecs.controller.MapController;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Simulation extends Canvas
{
    ApplicationController applicationController;
    MapController mapController;
    Image originalMap;
    Image scaledMap;
    int mapX = 0;
    int mapY = 0;
    int mouseClickedX = 0;
    int mouseClickedY = 0;
    float zoom = 1;

    public Simulation(ApplicationController applicationController) {
        this.applicationController = applicationController;
        this.mapController = applicationController.mapController;
        mapController.createMap(Constants.MAP_X_TILES, Constants.MAP_Y_TILES);
        this.originalMap = mapController.getConvertedMap();
        this.scaledMap = originalMap.getScaledInstance(Constants.BACKGROUND_MAP_IMAGE_WIDTH,Constants.BACKGROUND_MAP_IMAGE_HEIGHT, 0);
        setSize(Constants.BACKGROUND_MAP_IMAGE_WIDTH, Constants.BACKGROUND_MAP_IMAGE_HEIGHT);
        addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        mouseClickedX = e.getX();
                        mouseClickedY = e.getY();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                }
        );
        addMouseMotionListener(
                new MouseMotionListener() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        mapX = e.getX() - mouseClickedX;
                        mapY = e.getY() - mouseClickedY;
                        repaint();
                    }

                    @Override
                    public void mouseMoved(MouseEvent e) {

                    }
                }
        );
        addMouseWheelListener(
                new MouseWheelListener() {
                    @Override
                    public void mouseWheelMoved(MouseWheelEvent e) {
                        zoom += e.getUnitsToScroll() / 100.0;
                        scaledMap = originalMap.getScaledInstance((int)(Constants.BACKGROUND_MAP_IMAGE_WIDTH * zoom),(int)(Constants.BACKGROUND_MAP_IMAGE_WIDTH * zoom), 0);
                        repaint();
                    }
                }
        );
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(scaledMap, mapX, mapY, this);
    }



}
