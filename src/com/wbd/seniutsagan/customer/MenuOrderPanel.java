package com.wbd.seniutsagan.customer;

import com.wbd.seniutsagan.dto.PozycjaMenuDTO;
import com.wbd.seniutsagan.main.Singleton;

import javax.swing.*;
import java.util.List;

class MenuOrderPanel extends JPanel {

    private List<PozycjaMenuDTO> pozycjeMenu;

    public MenuOrderPanel() {
        pozycjeMenu = Singleton.getPozycjeMenu();
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        for(PozycjaMenuDTO p : pozycjeMenu)
            add(new PozycjaMenuPanel(p));
    }
}
