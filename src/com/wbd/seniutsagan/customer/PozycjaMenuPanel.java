package com.wbd.seniutsagan.customer;

import com.wbd.seniutsagan.dto.PozycjaMenuDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class PozycjaMenuPanel extends JPanel implements MouseListener{

    private PozycjaMenuDTO pozycjaMenu;
    private JPanel orderPanel;
    private JPanel infoPanel;

    PozycjaMenuPanel(PozycjaMenuDTO pozycjaMenu) {
        this.pozycjaMenu = pozycjaMenu;

        JLabel nazwa = new JLabel(pozycjaMenu.getNazwa());
        nazwa.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel potrawy = new JLabel(pozycjaMenu.getPotrawy());
        potrawy.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(nazwa, BorderLayout.PAGE_START);
        infoPanel.add(potrawy, BorderLayout.PAGE_END);


        JLabel cena = new JLabel(String.format("%.2f zł",pozycjaMenu.getCena()));
        cena.setFont(new Font("Arial", Font.BOLD, 18));
        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        plusButton.setMargin(new Insets(0,0,0,0));
        minusButton.setMargin(new Insets(0,0,0,0));
        plusButton.setPreferredSize(new Dimension(20,20));
        minusButton.setPreferredSize(new Dimension(20,20));
        JTextField quantityField = new JTextField("0",2);
        orderPanel = new JPanel();
        orderPanel.add(cena);
        orderPanel.add(quantityField);
        orderPanel.add(plusButton);
        orderPanel.add(minusButton);


        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        add(infoPanel, BorderLayout.WEST);
        add(orderPanel, BorderLayout.EAST);

        setMaximumSize(new Dimension(10000, (int) getPreferredSize().getHeight()));
        addMouseListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.lightGray);
        orderPanel.setBackground(Color.lightGray);
        infoPanel.setBackground(Color.lightGray);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(null);
        orderPanel.setBackground(null);
        infoPanel.setBackground(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
