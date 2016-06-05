package com.wbd.seniutsagan;

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

    public PracownicyListener pracownicyListener;
    public WynagrodzeniaListener wynagrodzeniaListener;
    public ProduktyListener produktyListener;
    public MenuListener menuListener;
    public DodajListener dodajListener;
    public UsunListener usunListener;
    public ZmodyfikujListener zmodyfikujListener;
    public InfoListener infoListener;

    // mozna zrobic analogiczny konstruktor dla innych frames
    public Listeners(KierownikFrame kierownikFrame, JPanel tablePanel) {
        frame = kierownikFrame;


        pracownicyListener = new PracownicyListener(tablePanel,kierownikFrame);
        wynagrodzeniaListener = new WynagrodzeniaListener();
        produktyListener = new ProduktyListener();
        menuListener = new MenuListener();
        dodajListener = new DodajListener();

    }
}





class PracownicyListener implements ActionListener {
    JPanel pracownikDataPanel;
    KierownikFrame frame;

    PracownicyListener(JPanel tablePanel,KierownikFrame kierownikFrame ) {
        super();
        frame = kierownikFrame;
        pracownikDataPanel = tablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
        List<PracownikDTO> pracownicyList = null;
        try {
            // zwraca result w postaci ArrayList
            pracownicyList = pracownicyDAO.readAllPracownik();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JTable pracownicyTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        Object [] pracownicyColumns = new Object[4];
        pracownicyColumns[0] = "Id";
        pracownicyColumns[1] = "Stanowisko";
        pracownicyColumns[2] = "Imię";
        pracownicyColumns[3] = "Nazwisko";

        model.setColumnIdentifiers(pracownicyColumns);

        Object [] rowData = new Object[4];
        for (int i=0; i<pracownicyList.size();i++){
            rowData[0] = pracownicyList.get(i).getId();
            rowData[1] = pracownicyList.get(i).getStanowisko();
            rowData[2] = pracownicyList.get(i).getImie();
            rowData[3] = pracownicyList.get(i).getNazwisko();

            model.addRow(rowData);
        }

        pracownicyTable.setModel(model);
        JPanel pracownicyPanel = new Cafe_Panel();
        pracownicyPanel.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane(pracownicyTable);
    //    if (pracownikDataPanel == null){
            pracownicyPanel.add(pane, BorderLayout.CENTER);
            pracownicyPanel.setName("second");

            System.out.println(frame.getContentPane().getComponent(3).getName());
           if(frame.getContentPane().getComponent(3).getName() == "first") {
               frame.remove(frame.getContentPane().getComponent(3));
               frame.add(pracownicyPanel, BorderLayout.CENTER);
               frame.pack();
               frame.repaint();
           }
       // }


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