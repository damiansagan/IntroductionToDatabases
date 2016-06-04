package com.wbd.seniutsagan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by monika03 on 04.06.16.
 */
public class MakeKierownikButtonsPanel extends JPanel{


        private Listeners listeners;
        static String pracownicyString = "Pracownicy";
        static String produktyString = "Produkty";
        static String wynagrodzeniaString = "Wynagrodzenia";
        static String menuString = "Menu";

        /**
         * method filling buttons panel with buttons and necessary elements
         * @param frame - main frame of the program
         */
        public MakeKierownikButtonsPanel(KierownikFrame frame)
        {
     //       super();
            super(new BorderLayout());
            listeners=frame.listeners;
//        this.setBackground(Color.WHITE);

            this.setMinimumSize(new Dimension(120, 30));
            this.setPreferredSize(new Dimension(180, 80));

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


            add(radioPanel, BorderLayout.LINE_START);
           // dodac cos jeszcze, co chce, zeby bylo wyswietlane
            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));


        }





//            startButton.addActionListener(listeners.start);
//            photocell1.addActionListener(listeners.photo1);
//            languageButton.addActionListener(listeners.languageListener);
//
//            photocell2.addActionListener(listeners.photo2);
//            pomiarButton.addActionListener(listeners.measure);
//
//
//            this.add(startButton);
//            this.add(pomiarButton);
////        this.add(saveButton);
//            this.add(languageButton);
//            this.add(photocell1);
//            this.add(photocell2);

        }
