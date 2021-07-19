package ecs;

import ecs.controller.ApplicationController;
import ecs.controller.CreatureController;
import ecs.controller.MapController;
import ecs.model.Creature;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SimulationCanvas extends Canvas
{
    ApplicationController applicationController;
    CreatureController creatureController;
    MapController mapController;
    Image originalMap;
    Image scaledMap;
    int mapX = (int)(Constants.BACKGROUND_MAP_IMAGE_HEIGHT * 0.1);
    int mapY = (int)(Constants.BACKGROUND_MAP_IMAGE_HEIGHT * 0.1);
    int mapOffsetX = (int)(Constants.BACKGROUND_MAP_IMAGE_HEIGHT * 0.1);
    int mapOffsetY = (int)(Constants.BACKGROUND_MAP_IMAGE_HEIGHT * 0.1);
    int mouseClickedX = 0;
    int mouseClickedY = 0;
    float zoom = 0.8f;

    public SimulationCanvas(ApplicationController applicationController) {
        this.applicationController = applicationController;
        this.creatureController = applicationController.creatureController;
        this.mapController = applicationController.mapController;
        this.mapController.createMap(Constants.MAP_X_TILES, Constants.MAP_Y_TILES);
        this.originalMap = mapController.getConvertedMap();
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
                        mapOffsetX = mapX;
                        mapOffsetY = mapY;
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
                        mapX = e.getX() - mouseClickedX + mapOffsetX;
                        mapY = e.getY() - mouseClickedY + mapOffsetY;
                        repaint();
                    }

                    @Override
                    public void mouseMoved(MouseEvent e) {

                    }
                }
        );
        addMouseWheelListener(
                e -> {
                    zoom += e.getUnitsToScroll() / 100.0;
                    zoom = zoom < 0.2 ? 0.2f : zoom;
                    repaint();
                }
        );
    }

    @Override
    public void paint(Graphics g)
    {
        drawMap(g);
        drawCreatures(g);
    }

    public void drawMap(Graphics g)
    {
        scaledMap = originalMap.getScaledInstance((int)(Constants.BACKGROUND_MAP_IMAGE_WIDTH * zoom),(int)(Constants.BACKGROUND_MAP_IMAGE_WIDTH * zoom), 0);
        g.drawImage(scaledMap, mapX, mapY, this);
    }

    public void drawCreatures(Graphics g)
    {
        for (Creature creature: creatureController.getCreatures())
        {
            switch (creature.lastAction)
            {
                case MOVE_TOP, MOVE_RIGHT, MOVE_BOTTOM, MOVE_LEFT -> {
                    g.setColor(new Color(210, 33, 33));
                }
                case MOVE_EYE_LEFT, MOVE_EYE_RIGHT -> {
                    g.setColor(new Color(210, 33, 192));
                }
                case EAT -> {
                    g.setColor(new Color(65, 210, 33));
                }
                case DRINK -> {
                    g.setColor(new Color(33, 127, 210));
                }
                case NONE -> {
                    g.setColor(new Color(168, 168, 168));
                }
            }
            g.fillOval(mapX + (int)(creature.positionX * zoom) - (Constants.CREATURE_DIAMETER / 2), mapY + (int)(creature.positionY * zoom) - (Constants.CREATURE_DIAMETER / 2), (int)(Constants.CREATURE_DIAMETER * zoom), (int)(Constants.CREATURE_DIAMETER * zoom));
        }
    }
}
