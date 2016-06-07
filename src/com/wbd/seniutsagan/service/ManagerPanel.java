package com.wbd.seniutsagan.service;



import com.wbd.seniutsagan.KierownikFrame;
import com.wbd.seniutsagan.Listeners;
import com.wbd.seniutsagan.MakeKierownikButtonsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by monika03 on 07.06.16.
 */
public class ManagerPanel extends JPanel implements ActionListener {
    JPanel dbButtonsPanel, container, selectionButtonsPanel;
    MakeKierownikButtonsPanel kPanel;
    KierownikFrame frame ;
    CardLayout containerLayout;
    Listeners listeners;
    private String dbButtonNames[] = { "Dodaj", "Zmodyfikuj", "Usuń", "INFO"  };
    private String selectionButtonNames[] = { "Pracownicy", "Wynagrodzenia", "Produkty", "Menu"  };

    public ManagerPanel(KierownikFrame kframe) {
        frame = kframe;
        listeners = new Listeners(this, kframe);
        createUpperPanel();
        createContainerPanel();
        createSidePanel();
        setLayout(new BorderLayout());
        add(dbButtonsPanel, BorderLayout.PAGE_START);
        add(container, BorderLayout.CENTER);
       // add(selectionButtonsPanel, BorderLayout.WEST);
        add(kPanel, BorderLayout.WEST);
    }

    private void createContainerPanel() {
        containerLayout = new CardLayout();
        container = new JPanel(containerLayout);
        // TODO: dorobic te panele
        JPanel wynagrodzeniaPanel = new JPanel();
        wynagrodzeniaPanel.setBackground(Color.orange);
        JPanel produktyPanel = new JPanel();
        JPanel menuPanel = new JPanel();
        JPanel defaultPanel = new JPanel();
        container.add(new PracownicyPanel(5), "PRACOWNICY");
        container.add(wynagrodzeniaPanel, "WYNAGRODZENIA");
        container.add(produktyPanel, "PRODUKTY");
        container.add(menuPanel, "MENU");
        container.add(defaultPanel, "DEFAULT");
        containerLayout.show(container,"DEFAULT");


    }

    public void swapView(String key) {
        containerLayout.show(container, key);
    }

    public JPanel getContainerPanel(){
        return container;
    }

    public CardLayout getContainerLayout(){
        return containerLayout;
    }
    private void createUpperPanel() {
        JButton addButton = new JButton(dbButtonNames[0]);
        JButton modifyButton = new JButton(dbButtonNames[1]);
        JButton deleteButton = new JButton(dbButtonNames[2]);
        JButton infoButton = new JButton(dbButtonNames[3]);

        // TODO: zmienic argumenty w listenerach na listeners.(...)
        addButton.addActionListener(this);
        modifyButton.addActionListener(this);
        deleteButton.addActionListener(this);
        infoButton.addActionListener(this);


        dbButtonsPanel = new JPanel();
        dbButtonsPanel.add(addButton);
        dbButtonsPanel.add(modifyButton);
        dbButtonsPanel.add(deleteButton);
        dbButtonsPanel.add(infoButton);

    }

    private void createSidePanel() {
        kPanel = new MakeKierownikButtonsPanel(listeners);
       // new MakeKierownikButtonsPanel(kframe);
//        JButton pracownicyButton = new JButton(selectionButtonNames[0]);
//        JButton wynagrodzeniaButton = new JButton(selectionButtonNames[1]);
//        JButton produktyButton = new JButton(selectionButtonNames[2]);
//        JButton menuButton = new JButton(selectionButtonNames[3]);
//
//        pracownicyButton.addActionListener(this);
//        wynagrodzeniaButton.addActionListener(this);
//        produktyButton.addActionListener(this);
//        menuButton.addActionListener(this);
//
//
//        selectionButtonsPanel = new JPanel();
//        selectionButtonsPanel.add(pracownicyButton);
//        selectionButtonsPanel.add(wynagrodzeniaButton);
//        selectionButtonsPanel.add(produktyButton);
//        selectionButtonsPanel.add(menuButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout)(container.getLayout());
        cardLayout.show(container, e.getActionCommand());
    }
}
