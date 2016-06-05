package com.wbd.seniutsagan.main;

import com.wbd.seniutsagan.customer.CustomerPanel;

import javax.swing.*;

class ApplicationGUI {
    private JFrame frame;

    ApplicationGUI() {
        createFrame();
        frame.add(new CustomerPanel());
        // show GUI
        frame.pack();
        frame.setVisible(true);
    }

    private void createFrame() {
        // set up frame
        frame = new JFrame();
        frame.setTitle("ApplicationGUI");
        // it will center frame
        frame.setLocationRelativeTo(null);
        // it will cause to exit application when cross is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
