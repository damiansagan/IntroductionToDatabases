package com.wbd.seniutsagan.service;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by monika03 on 07.06.16.
 */
public class ManagerPanel extends JPanel implements ActionListener {
    JPanel dbButtonsPanel, container, selectButtonsPanel;
    private String dbButtonNames[] = { "Dodaj", "Zmodyfikuj", "Usuń",  };

    public ManagerPanel() {
        createUpperPanel();
        createContainerPanel();
        setLayout(new BorderLayout());
       // add(upperPanel, BorderLayout.PAGE_START);
        add(container, BorderLayout.CENTER);
    }

    private void createContainerPanel() {
        container = new JPanel(new CardLayout());
//        container.add(new MenuOrderPanel(), buttonNames[0]);
//        container.add(new ReservationPanel(), buttonNames[1]);
//        container.add(new AccountPanel(), buttonNames[2]);
    }

    private void createUpperPanel() {
//        JButton orderButton = new JButton(buttonNames[0]);
//        JButton reservationButton = new JButton(buttonNames[1]);
//        JButton accountButton = new JButton(buttonNames[2]);

//        orderButton.addActionListener(this);
//        reservationButton.addActionListener(this);
//        accountButton.addActionListener(this);
//
//        upperPanel = new JPanel();
//        upperPanel.add(orderButton);
//        upperPanel.add(reservationButton);
//        upperPanel.add(accountButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout)(container.getLayout());
        cardLayout.show(container, e.getActionCommand());
    }
}
