package ecs;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Simulation {

    public Simulation(BufferedImage img)
    {
        ImageIcon icon=new ImageIcon(img.getScaledInstance(Constants.BACKGROUND_MAP_IMAGE_WIDTH,Constants.BACKGROUND_MAP_IMAGE_HEIGHT, 0));
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(Constants.BACKGROUND_MAP_IMAGE_WIDTH, Constants.BACKGROUND_MAP_IMAGE_HEIGHT);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
