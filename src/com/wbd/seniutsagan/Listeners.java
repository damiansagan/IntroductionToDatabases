package com.wbd.seniutsagan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by monika03 on 04.06.16.
 */
public class Listeners {

    KierownikFrame frame;
    public PracownicyListener pracownicyListener;
    public WynagrodzeniaListener wynagrodzeniaListener;
    public ProduktyListener produktyListener;
    public MenuListener menuListener;

    // mozna zrobic analogiczny konstruktor dla innych frames
    public Listeners(KierownikFrame kierownikFrame) {
        frame = kierownikFrame;

        pracownicyListener = new PracownicyListener();
        wynagrodzeniaListener = new WynagrodzeniaListener();
        produktyListener = new ProduktyListener();
        menuListener = new MenuListener();

    }
}


class PracownicyListener implements ActionListener {
    PracownicyListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      //  new InstructionsRead();
    }
}

class WynagrodzeniaListener implements ActionListener {
    WynagrodzeniaListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //  new InstructionsRead();
    }
}

class ProduktyListener implements ActionListener {
    ProduktyListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //  new InstructionsRead();
    }
}

class MenuListener implements ActionListener {
    MenuListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //  new InstructionsRead();
    }
}