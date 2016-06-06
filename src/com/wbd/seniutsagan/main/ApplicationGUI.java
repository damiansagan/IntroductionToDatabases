package com.wbd.seniutsagan.main;

import com.wbd.seniutsagan.customer.CustomerPanel;

import javax.swing.*;
import java.awt.*;

class ApplicationGUI {
    private JFrame frame;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 1000;

    ApplicationGUI() {
        createFrame();
        frame.getContentPane().add(new CustomerPanel());
        // show GUI
        frame.setVisible(true);
    }

    private void createFrame() {
        // set up frame
        frame = new JFrame();
        frame.setTitle("ApplicationGUI");
        // it will center frame
        frame.setLocationRelativeTo(null);
        //frame.pack();
        Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dimensions.width / 2 - WINDOW_WIDTH / 2, dimensions.height
                / 2 - WINDOW_HEIGHT / 2, WINDOW_WIDTH, WINDOW_HEIGHT);
        // it will cause to exit application when cross is clicked
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
