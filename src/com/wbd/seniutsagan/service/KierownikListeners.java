package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.*;
import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dao.SQLPracownikDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by monika03 on 07.06.16.
 */
public class KierownikListeners {



    PracownicyListener pracownicyListener;
    WynagrodzeniaListener wynagrodzeniaListener;
    ProduktyListener produktyListener;
    MenuListener menuListener;
    DodajListener dodajListener;
    UsunListener usunListener;
    ZmodyfikujListener zmodyfikujListener;
    InfoListener infoListener;

    // mozna zrobic analogiczny konstruktor dla innych frames
    public KierownikListeners() {



        pracownicyListener = new PracownicyListener();
        wynagrodzeniaListener = new WynagrodzeniaListener();
        produktyListener = new ProduktyListener();
        menuListener = new MenuListener();
        dodajListener = new DodajListener();

    }
}



class PracownicyListener implements ActionListener {
   // JPanel pracownikDataPanel;
   // KierownikFrame frame;

    PracownicyListener( ) {
        super();
     //   frame = kierownikFrame;
       // pracownikDataPanel = tablePanel;
    }


    //instance table model
    DefaultTableModel tableModel2 = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
        List<PracownikDTO> pracownicyList = null;
        try {
            // zwraca result w postaci ArrayList
            pracownicyList = pracownicyDAO.readMainPracownik();
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

        tableModel2.setColumnIdentifiers(pracownicyColumns);
        pracownicyTable.getTableHeader().setReorderingAllowed(false);
        Object [] rowData = new Object[4];
        for (int i=0; i<pracownicyList.size();i++){
            rowData[0] = pracownicyList.get(i).getId();
            rowData[1] = pracownicyList.get(i).getStanowisko();
            rowData[2] = pracownicyList.get(i).getImie();
            rowData[3] = pracownicyList.get(i).getNazwisko();

            tableModel2.addRow(rowData);

        }

        pracownicyTable.setModel(tableModel2);
        pracownicyTable.setRowSelectionAllowed(true);
        pracownicyTable.setColumnSelectionAllowed(false);

        JPanel pracownicyPanel = new Cafe_Panel();
        pracownicyPanel.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane(pracownicyTable);
        pracownicyPanel.add(pane, BorderLayout.CENTER);
        pracownicyPanel.setName("second");

      //  System.out.println(frame.getContentPane().getComponent(3));
        // if(frame.getContentPane().getComponent(3).getName() == "first") {
        // frame.getContentPane().add(pracownicyPanel, BorderLayout.CENTER);
      //  frame.getContentPane().remove(frame.getContentPane().getComponent(3));
      //  frame.getContentPane().revalidate();

        // frame.getContentPane().remove(frame.getContentPane().getComponent(3));
        // frame.remove(frame.getContentPane().getComponent(3));

        // frame.getContentPane().repaint();
        // frame.getContentPane().revalidate();
        //  frame.pack();
        //  frame.repaint();
        // }
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
