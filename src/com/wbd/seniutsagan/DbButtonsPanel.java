package com.wbd.seniutsagan;

import javax.swing.*;
import java.awt.*;

/**
 * Created by monika03 on 04.06.16.
 */
public class DbButtonsPanel extends JPanel {

    private Listeners listeners;
    private JButton dodajButton;
    private JButton usunButton;
    private JButton zmodyfikujButton;
    private JButton infoButton;



    public DbButtonsPanel(KierownikFrame frame) {
        //       super();
        super();
        this.setBackground(Color.BLUE);
       // this.setMinimumSize(new Dimension(120, 30));
       // this.setPreferredSize(new Dimension(180, 80));

        listeners=frame.listeners;

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //this.setBorder(BorderFactory.createTitledBorder("cos"));

        BoxLayout layout = new BoxLayout(this, BoxLayout.LINE_AXIS);
        this.setLayout(layout);
        this.add(Box.createRigidArea(new Dimension(350,5)));

        // this.setLayout(new FlowLayout(FlowLayout.RIGHT));
      //  this.setAlignmentX(Component.RIGHT_ALIGNMENT);


        dodajButton = new JButton("DODAJ");
        //dodajButton.setPreferredSize(new Dimension(40, 80));
       // dodajButton.setAlignmentX(450);
       // dodajButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.add(dodajButton);


        usunButton = new JButton("USUŃ");
       // usunButton.setPreferredSize(new Dimension(40, 80));
       // usunButton.setAlignmentX(650);
       // usunButton.setAlignmentY(Component.RIGHT_ALIGNMENT);
        this.add(usunButton);


        zmodyfikujButton = new JButton("ZMODYFIKUJ");
       // zmodyfikujButton.setPreferredSize(new Dimension(40, 80));
       // zmodyfikujButton.setAlignmentX(850);
       // zmodyfikujButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.add(zmodyfikujButton);


        infoButton = new JButton("INFO");
       // infoButton.setPreferredSize(new Dimension(40, 80));
       // infoButton.setAlignmentX(950);
       // infoButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.add(infoButton);








    }


}
