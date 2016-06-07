package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.KierownikFrame;
import com.wbd.seniutsagan.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by monika03 on 07.06.16.
 */
public class KierownikButtonsPanel extends JPanel {

    private KierownikListeners listeners;
    static String pracownicyString = "Pracownicy";
    static String produktyString = "Produkty";
    static String wynagrodzeniaString = "Wynagrodzenia";
    static String menuString = "Menu";

    public KierownikButtonsPanel()
    {
        super();
        // super(new BorderLayout());
        listeners= new KierownikListeners();
//        this.setBackground(Color.WHITE);

        // this.setMinimumSize(new Dimension(120, 30));
        //  this.setPreferredSize(new Dimension(180, 80));

        //In initialization code:
        //Create the radio buttons.

        JRadioButton pracownicyButton = new JRadioButton(pracownicyString);
        pracownicyButton.setMnemonic(KeyEvent.VK_B);
        pracownicyButton.setActionCommand(pracownicyString);

        JRadioButton produktyButton = new JRadioButton(produktyString);
        produktyButton.setMnemonic(KeyEvent.VK_B);
        produktyButton.setActionCommand(produktyString);
        produktyButton.setSelected(true);

        JRadioButton wynagrodzeniaButton = new JRadioButton(wynagrodzeniaString);
        wynagrodzeniaButton.setMnemonic(KeyEvent.VK_B);
        wynagrodzeniaButton.setActionCommand(wynagrodzeniaString);

        JRadioButton menuButton = new JRadioButton(menuString);
        pracownicyButton.setMnemonic(KeyEvent.VK_B);
        pracownicyButton.setActionCommand(menuString);

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(pracownicyButton);
        group.add(produktyButton);
        group.add(wynagrodzeniaButton);
        group.add(menuButton);

        //Register a listener for the radio buttons.
        pracownicyButton.addActionListener(listeners.pracownicyListener);
        wynagrodzeniaButton.addActionListener(listeners.wynagrodzeniaListener);
        produktyButton.addActionListener(listeners.produktyListener);
        menuButton.addActionListener(listeners.menuListener);



        //Put the radio buttons in a column in a panel.
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(pracownicyButton);
        radioPanel.add(wynagrodzeniaButton);
        radioPanel.add(produktyButton);
        radioPanel.add(menuButton);


        add(radioPanel, BorderLayout.WEST);
        // dodac cos jeszcze, co chce, zeby bylo wyswietlane
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


    }




}
