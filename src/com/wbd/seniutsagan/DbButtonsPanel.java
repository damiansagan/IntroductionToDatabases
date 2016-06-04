package com.wbd.seniutsagan;

import javax.swing.*;
import java.awt.*;

/**
 * Created by monika03 on 04.06.16.
 */
public class DbButtonsPanel extends JPanel {

    private Listeners listeners;


    public DbButtonsPanel(KierownikFrame frame) {
        //       super();
        super();
        this.setBackground(Color.BLUE);
        this.setMinimumSize(new Dimension(120, 30));
        this.setPreferredSize(new Dimension(180, 80));

        listeners=frame.listeners;

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


    }


}
