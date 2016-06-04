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

        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);


        dodajButton = new JButton("DODAJ");
        dodajButton.setAlignmentY(1550);
        this.add(dodajButton);

        usunButton = new JButton("USUŃ");
        usunButton.setAlignmentY(1750);
        this.add(usunButton);

        zmodyfikujButton = new JButton("ZMODYFIKUJ");
        zmodyfikujButton.setAlignmentY(1850);
        this.add(zmodyfikujButton);

        infoButton = new JButton("INFO");
        infoButton.setAlignmentY(1950);
        this.add(infoButton);








    }


}
