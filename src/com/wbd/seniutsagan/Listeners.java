package com.wbd.seniutsagan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by monika03 on 04.06.16.
 */
public class Listeners {

    KierownikFrame frame;
    public PracownicyListener pracownicyListener;
    public WynagrodzeniaListener wynagrodzeniaListener;
    public ProduktyListener produktyListener;
    public MenuListener menuListener;
    public DodajListener dodajListener;
    public UsunListener usunListener;
    public ZmodyfikujListener zmodyfikujListener;
    public InfoListener infoListener;

    // mozna zrobic analogiczny konstruktor dla innych frames
    public Listeners(KierownikFrame kierownikFrame) {
        frame = kierownikFrame;

        pracownicyListener = new PracownicyListener();
        wynagrodzeniaListener = new WynagrodzeniaListener();
        produktyListener = new ProduktyListener();
        menuListener = new MenuListener();
        dodajListener = new DodajListener();

    }
}


class PracownicyListener implements ActionListener {
    PracownicyListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
        List<PracownikDTO> pracownicyList = null;
        try {
            pracownicyList = pracownicyDAO.readAllPracownik();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(PracownikDTO p : pracownicyList)
            stringBuilder.append(p.toString1()).append('\n');
        System.out.println(stringBuilder.toString());
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


class DodajListener implements ActionListener {
    DodajListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //  new InstructionsRead();
    }
}

class UsunListener implements ActionListener {
    UsunListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //  new InstructionsRead();
    }
}

class ZmodyfikujListener implements ActionListener {
    ZmodyfikujListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //  new InstructionsRead();
    }
}

class InfoListener implements ActionListener {
    InfoListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //  new InstructionsRead();
    }
}