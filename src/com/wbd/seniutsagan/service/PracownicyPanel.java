package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.Listeners;
import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;
import com.wbd.seniutsagan.dao.SQLPracownikDAO;
import com.wbd.seniutsagan.main.Singleton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.Marshaller;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by monika03 on 07.06.16.
 */
public class PracownicyPanel extends JPanel {

    private List<PracownikDTO> pracownicyList ;
    private String [] headings;
    private JScrollPane scrollPane;
    private JScrollPane scrollPanel;
    private JTable pracownicyTable;
    private PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
    private int row;
    private Listeners listeners ;


//    PracownicyPanel() {
//        setLayout(new BorderLayout());
//        createPracownicyTable();
//        preparePracownicyData();
//        createScrollPane();
//        add(scrollPane, BorderLayout.CENTER);
//    }
    public PracownicyPanel(Listeners passedListeners) {

        setLayout(new BorderLayout());
        createPracownicyTable();
        preparePracownicyData();
        createScrollPane();
        listeners = passedListeners;
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createPracownicyTable(){

        try {
            // zwraca result w postaci ArrayList
            pracownicyList = pracownicyDAO.readMainPracownik();

            ResultSet pracownicyResultSet = pracownicyDAO.resultSetMainPracownik();
            headings= pracownicyDAO.headings(pracownicyResultSet);
            //for (int i=0; i< headings.length; i++) {
              //  System.out.println("get1: " + headings[i]);
           // }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        //instance table model
        DefaultTableModel tableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        pracownicyTable = new JTable();

       // Object[] pracownicyColumns = new Object[headings.length];
        Object[] pracownicyColumns = new Object[4];
//        for (int i=0; i< headings.length; i++) {
//            pracownicyColumns[i] = headings[i];
//        }
        pracownicyColumns[0] = "Id";
        pracownicyColumns[1] = "Stanowisko";
        pracownicyColumns[2] = "Imię";
        pracownicyColumns[3] = "Nazwisko";

        tableModel.setColumnIdentifiers(pracownicyColumns);
        pracownicyTable.getTableHeader().setReorderingAllowed(false);
        Object[] rowData = new Object[4];
        for (int i = 0; i < pracownicyList.size(); i++) {
            rowData[0] = pracownicyList.get(i).getId();
            rowData[1] = pracownicyList.get(i).getStanowisko();
            rowData[2] = pracownicyList.get(i).getImie();
            rowData[3] = pracownicyList.get(i).getNazwisko();

            tableModel.addRow(rowData);

        }

        pracownicyTable.setModel(tableModel);
        pracownicyTable.setRowSelectionAllowed(true);
        pracownicyTable.setColumnSelectionAllowed(false);
        pracownicyTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // getPoint +1, bo liczy od 0
                int selectedRowIndex =pracownicyTable.getSelectedRow();
                int selectedColumnIndex = 0;
                Object selectedObject = pracownicyTable.getModel().getValueAt(selectedRowIndex,selectedColumnIndex);
                System.out.println("selecteeeed "+selectedObject);
               row = (int)selectedObject;
                System.out.println(row);
                listeners.setPracownicyRowClicked(row);
               // listeners.usunListener.setIdPracownik(row);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


    }


    private void createScrollPane() {
        scrollPane = new JScrollPane(pracownicyTable);

    }

    private void recreateScrollPane() {
        remove(scrollPane);
        createScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
    }

    public void preparePracownicyData() {
        pracownicyList = Singleton.updatePracownik();
    }

    public int getPracownikRowClicked(){
        return row;
    }


}
