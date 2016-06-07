package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.KierownikFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by monika03 on 07.06.16.
 */
public class dbButtons extends JPanel {
    private JButton dodajButton;
    private JButton usunButton;
    private JButton zmodyfikujButton;
    private JButton infoButton;



    public dbButtons() {
        //       super();
        super();
        this.setBackground(Color.BLUE);
        // this.setMinimumSize(new Dimension(120, 30));
        // this.setPreferredSize(new Dimension(180, 80));

      //  listeners=frame.listeners;

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //this.setBorder(BorderFactory.createTitledBorder("cos"));


        BoxLayout layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
        this.setLayout(layout);
        this.add(Box.createRigidArea(new Dimension(350,5)));

        // this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //  this.setAlignmentX(Component.RIGHT_ALIGNMENT);


        dodajButton = new JButton("DODAJ");
        this.add(dodajButton);


        usunButton = new JButton("USUŃ");
        this.add(usunButton);


        zmodyfikujButton = new JButton("ZMODYFIKUJ");
        this.add(zmodyfikujButton);


        infoButton = new JButton("INFO");
        this.add(infoButton);








    }

}
