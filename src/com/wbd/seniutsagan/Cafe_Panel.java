package com.wbd.seniutsagan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by monika03 on 04.06.16.
 */
public class Cafe_Panel extends JPanel {


    public Cafe_Panel() {
        //       super();
        super(new BorderLayout());

        this.setBackground(Color.GREEN);

        this.setMinimumSize(new Dimension(120, 30));
        this.setPreferredSize(new Dimension(180, 80));


        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


    }

}





