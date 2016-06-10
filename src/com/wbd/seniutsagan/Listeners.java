package com.wbd.seniutsagan;

import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dao.SQLPracownikDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;
import com.wbd.seniutsagan.service.ManagerPanel;
import com.wbd.seniutsagan.service.ModifyPracownikDataPanel;
import com.wbd.seniutsagan.service.PracownicyInfoPanel;
import com.wbd.seniutsagan.service.PracownicyPanel;

import javax.swing.*;
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
    private int pracownicyRowClicked;

    // mozna zrobic analogiczny konstruktor dla innych frames
    public Listeners(ManagerPanel managerPanel, KierownikFrame kframe) {
        frame = kframe;
        this.managerPanel = managerPanel;


        pracownicyListener = new PracownicyListener( managerPanel, frame);
        zmodyfikujListener = new ZmodyfikujListener();
        wynagrodzeniaListener = new WynagrodzeniaListener();
        produktyListener = new ProduktyListener();
        menuListener = new MenuListener();
        dodajListener = new DodajListener();
        infoListener = new InfoListener();

    }

    class PracownicyListener implements ActionListener {
        private PracownicyPanel pracownicyPanel ;
        JPanel pracownikDataPanel;
        KierownikFrame frame;
        Listeners listeners;
        int i = 0;

        PracownicyListener(ManagerPanel managerPanel, KierownikFrame frame) {
            super();
            //frame = kierownikFrame;
            i++;
            listeners = managerPanel.getManagerPanelListeners();
            pracownicyPanel = new PracownicyPanel(listeners);

        }


        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            managerPanel.swapView("PRACOWNICY");

            PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
            List<PracownikDTO> pracownicyList = null;
            try {
                // zwraca result w postaci ArrayList
                pracownicyList = pracownicyDAO.readMainPracownik();
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
        private int pracownicyRowListener;
        ZmodyfikujListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

          ModifyPracownikDataPanel pracownikDataPanel = new ModifyPracownikDataPanel(pracownicyRowListener);
            managerPanel.getContainerPanel().add(pracownikDataPanel, "PRACOWNICY_INFO");
            //managerPanel.getPracownicyInfoPanel().setPracownikNr(pracownicyRowListener);
            managerPanel.swapView("PRACOWNICY_INFO");

        }
        public void setPracownicyRowListener(int num) { pracownicyRowListener= num; }
    }

    class InfoListener implements ActionListener {
        private int pracownicyRowListener;
        InfoListener() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
          //  if (pracownicyRowListener == 3) {


            PracownicyInfoPanel pracownicyInfoPanel = new PracownicyInfoPanel(pracownicyRowListener);
            managerPanel.getContainerPanel().add(pracownicyInfoPanel, "PRACOWNICY_INFO");
            //managerPanel.getPracownicyInfoPanel().setPracownikNr(pracownicyRowListener);
                managerPanel.swapView("PRACOWNICY_INFO");
          //  }

        }

        public void setPracownicyRowListener(int num) { pracownicyRowListener= num; }

    }


    public void setPracownicyRowClicked(int num) {
        pracownicyRowClicked= num;
    infoListener.setPracownicyRowListener(num);
        zmodyfikujListener.setPracownicyRowListener(num);
    }

}