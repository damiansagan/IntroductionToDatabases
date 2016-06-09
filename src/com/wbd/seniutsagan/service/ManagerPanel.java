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
    PracownicyInfoPanel pracownicyInfoPanel;
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
       // pracownicyInfoPanel = new PracownicyInfoPanel();
        container.add(new PracownicyPanel(listeners), "PRACOWNICY");
       // container.add(new PracownicyInfoPanel(), "PRACOWNICY_INFO");
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

    public Listeners getManagerPanelListeners(){
        return listeners;
    }

    public CardLayout getContainerLayout(){
        return containerLayout;
    }
    private void createUpperPanel() {
        JButton addButton = new JButton(dbButtonNames[0]);
        JButton modifyButton = new JButton(dbButtonNames[1]);
        JButton deleteButton = new JButton(dbButtonNames[2]);
        JButton infoButton = new JButton(dbButtonNames[3]);


        addButton.addActionListener(listeners.dodajListener);
        modifyButton.addActionListener(listeners.zmodyfikujListener);
        deleteButton.addActionListener(listeners.usunListener);
        infoButton.addActionListener(listeners.infoListener);


        dbButtonsPanel = new JPanel();
        dbButtonsPanel.add(addButton);
        dbButtonsPanel.add(modifyButton);
        dbButtonsPanel.add(deleteButton);
        dbButtonsPanel.add(infoButton);

    }

    private void createSidePanel() {

        kPanel = new MakeKierownikButtonsPanel(listeners);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout)(container.getLayout());
        cardLayout.show(container, e.getActionCommand());
    }

    public PracownicyInfoPanel getPracownicyInfoPanel(){
        return pracownicyInfoPanel;
    }




}
