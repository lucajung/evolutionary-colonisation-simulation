package ecs;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ECSWindow extends Frame {

    public ECSWindow()
    {
        super("ECS");
        setLayout(null);
        setSize(Constants.BACKGROUND_MAP_IMAGE_WIDTH, Constants.BACKGROUND_MAP_IMAGE_HEIGHT);
        setVisible(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    dispose();
                }
            }
        );
    }
}
