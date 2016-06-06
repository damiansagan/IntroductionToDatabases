package com.wbd.seniutsagan.customer;

import com.wbd.seniutsagan.dto.PozycjaMenuDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class PozycjaMenuPanel extends JPanel implements MouseListener{

    private PozycjaMenuDTO pozycjaMenu;

    PozycjaMenuPanel(PozycjaMenuDTO pozycjaMenu) {
        this.pozycjaMenu = pozycjaMenu;

        JLabel nazwa = new JLabel(pozycjaMenu.getNazwa());
        nazwa.setFont(new Font("Serif", Font.BOLD, 16));

        JLabel potrawy = new JLabel(pozycjaMenu.getPotrawy());
        potrawy.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel cena = new JLabel(String.format("%.2f zł",pozycjaMenu.getCena()));
        cena.setFont(new Font("Arial", Font.BOLD, 18));

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        add(nazwa, BorderLayout.PAGE_START);
        add(potrawy, BorderLayout.WEST);
        add(cena, BorderLayout.EAST);

        setMaximumSize(new Dimension(10000, (int) getPreferredSize().getHeight()));
        addMouseListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.lightGray);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
