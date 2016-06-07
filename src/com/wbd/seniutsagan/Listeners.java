package com.wbd.seniutsagan;

import com.wbd.seniutsagan.service.ManagerPanel;
import com.wbd.seniutsagan.service.PracownicyPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by monika03 on 04.06.16.
 */
public class Listeners {

    KierownikFrame frame;
    ManagerPanel managerPanel;

    public PracownicyListener pracownicyListener;
    public WynagrodzeniaListener wynagrodzeniaListener;
    public ProduktyListener produktyListener;
    public MenuListener menuListener;
    public DodajListener dodajListener;
    public UsunListener usunListener;
    public ZmodyfikujListener zmodyfikujListener;
    public InfoListener infoListener;

    // mozna zrobic analogiczny konstruktor dla innych frames
    public Listeners(ManagerPanel managerPanel, KierownikFrame kframe) {
        frame = kframe;
        this.managerPanel = managerPanel;


        pracownicyListener = new PracownicyListener( managerPanel, frame);
        wynagrodzeniaListener = new WynagrodzeniaListener();
        produktyListener = new ProduktyListener();
        menuListener = new MenuListener();
        dodajListener = new DodajListener();

    }

    class PracownicyListener implements ActionListener {
        private PracownicyPanel pracownicyPanel ;
        JPanel pracownikDataPanel;
        KierownikFrame frame;
        int i = 0;

        PracownicyListener(ManagerPanel managerPanel, KierownikFrame frame) {
            super();
            //frame = kierownikFrame;
            i++;
            pracownicyPanel = new PracownicyPanel(i);

        }


//        //instance table model
//        DefaultTableModel tableModel = new DefaultTableModel() {
//
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                //all cells false
//                return false;
//            }
//        };

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            managerPanel.swapView("PRACOWNICY");

            PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
            List<PracownikDTO> pracownicyList = null;
            try {
                // zwraca result w postaci ArrayList
                pracownicyList = pracownicyDAO.readAllPracownik();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            StringBuilder stringBuilder = new StringBuilder();


            for (PracownikDTO p : pracownicyList)
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
            managerPanel.swapView("WYNAGRODZENIA");

        }
    }

    class ProduktyListener implements ActionListener {
        ProduktyListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            managerPanel.swapView("PRODUKTY");
        }
    }

    class MenuListener implements ActionListener {
        MenuListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            managerPanel.swapView("PRODUKTY");
        }
    }


    class DodajListener implements ActionListener {
        DodajListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        }
    }

    class UsunListener implements ActionListener {
        UsunListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

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
           // managerPanel.swapView("I");
        }
    }
}