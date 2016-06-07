package com.wbd.seniutsagan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by monika03 on 04.06.16.
 */
public class Cafe_Panel extends JPanel {

    CardLayout cl = new CardLayout();

    public Cafe_Panel() {
        //       super();
      //  super();
        setLayout(cl);
        JPanel defaultPanel = new JPanel();
        defaultPanel.setBackground(Color.cyan);
        JPanel wynagrodzeniaPanel = new JPanel();
        wynagrodzeniaPanel.setBackground(Color.gray);
        JPanel produktyPanel = new JPanel() ;
        produktyPanel.setBackground(Color.pink);
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.orange);
        JPanel pracownicyPanel = new JPanel();
        menuPanel.setBackground(Color.orange);

        add(defaultPanel, "default panel");
        add(pracownicyPanel,"pracownicy panel");
        add(wynagrodzeniaPanel,"wynagrodzenia panel");
        add(produktyPanel,"produkty panel");
        add(menuPanel,"menu panel");

        this.setSize(new Dimension(180, 80));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cl.show(this,"default panel");
        //this.setBackground(Color.GREEN);

        this.setMinimumSize(new Dimension(120, 30));



    }

    @Override
    public CardLayout getLayout() {
        return cl;
    }
}





