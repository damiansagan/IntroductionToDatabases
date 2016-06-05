package com.wbd.seniutsagan.customer;

import com.wbd.seniutsagan.dto.PozycjaMenuDTO;

import javax.swing.*;

class PozycjaMenuPanel extends JPanel{

    private PozycjaMenuDTO pozycjaMenu;

    PozycjaMenuPanel(PozycjaMenuDTO pozycjaMenu) {
        this.pozycjaMenu = pozycjaMenu;
        add(new JLabel(pozycjaMenu.toString()));
    }
}
