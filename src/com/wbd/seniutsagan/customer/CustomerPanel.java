package com.wbd.seniutsagan.customer;


import javax.swing.*;
import java.awt.*;

public class CustomerPanel extends JPanel{
    private JPanel upperPanel, menuOrderPanel, reservationPanel, customerPanel;

    public CustomerPanel() {
        createUpperPanel();
        createMenuOrderPanel();
        createReservationPanel();
        createCustomerPanel();
        setLayout(new BorderLayout());
        add(upperPanel,BorderLayout.NORTH);
        add(menuOrderPanel,BorderLayout.CENTER);
    }

    private void createMenuOrderPanel() {
        menuOrderPanel=new MenuOrderPanel();
    }

    private void createReservationPanel() {
    }

    private void createCustomerPanel() {
    }

    private void createUpperPanel() {
        JButton orderButton = new JButton("Menu/Zamowienie");
        JButton reservationButton = new JButton("Rezerwacja");
        JButton accountButton = new JButton("Konto");

        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout());
        upperPanel.add(orderButton);
        upperPanel.add(reservationButton);
        upperPanel.add(accountButton);

        orderButton.addActionListener((e) -> {});
        reservationButton.addActionListener((e) -> {});
        accountButton.addActionListener((e) -> {});
    }




}
