package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dao.SQLPracownikDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;
import com.wbd.seniutsagan.main.Singleton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by monika03 on 08.06.16.
 */
public class PracownicyInfoPanel extends JPanel {
    private List<PracownikDTO> pracownicyList ;
    private JScrollPane scrollPane;
    private JScrollPane scrollPanel;
    private JTable pracownicyTable;
    private PracownikDTO pracownikDTO;
    private PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
    private int pracownikNr;


//    PracownicyInfoPanel() {
//        setLayout(new BorderLayout());
//        createPracownicyTable();
//        preparePracownicyData();
//        createScrollPane();
//        add(scrollPane, BorderLayout.CENTER);
//    }
    public PracownicyInfoPanel(int num) {
        setLayout(new BorderLayout());
        pracownikNr = num;
        createPracownicyTable();

        preparePracownicyData();
        createScrollPane();
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createPracownicyTable(){
        try {
            // zwraca result w postaci ArrayList
            //pracownicyList = pracownicyDAO.readAllPracownik();
            pracownikDTO = pracownicyDAO.readSelectedPracownik(pracownikNr);
            System.out.println(pracownikDTO.toString());
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

        Object[] pracownicyColumns = new Object[9];
        pracownicyColumns[0] = "Id";
        pracownicyColumns[1] = "Stanowisko";
        pracownicyColumns[2] = "Imię";
        pracownicyColumns[3] = "Nazwisko";
        pracownicyColumns[4] = "Data urodzenia";
        pracownicyColumns[5] = "PESEL";
        pracownicyColumns[6] = "Nr Konta";
        pracownicyColumns[7] = "Id kawiarni";
        pracownicyColumns[8] = "Id lokalu";



        tableModel.setColumnIdentifiers(pracownicyColumns);
        pracownicyTable.getTableHeader().setReorderingAllowed(false);
        Object[] rowData = new Object[9];
       // for (int i = 0; i < pracownicyList.size(); i++) {
        System.out.println(pracownikDTO.getId());
            rowData[0] = pracownikDTO.getId();
            rowData[1] = pracownikDTO.getStanowisko();
            rowData[2] = pracownikDTO.getImie();
            rowData[3] = pracownikDTO.getNazwisko();
            rowData[4] = pracownikDTO.getDataUrodzenia();
            rowData[5] = pracownikDTO.getPESEL();
            rowData[6] = pracownikDTO.getNrKonta();
            rowData[7] = pracownikDTO.getKawiarniaId();
            rowData[8] = pracownikDTO.getLokalId();


            tableModel.addRow(rowData);

      //  }

        pracownicyTable.setModel(tableModel);
        pracownicyTable.setRowSelectionAllowed(true);
        pracownicyTable.setColumnSelectionAllowed(false);
        // tutaj mouse listener nie jest potrzebny, jeśli chcemy tylko przejrzeć info
        pracownicyTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // getPoint +1, bo liczy od 0
                int row = pracownicyTable.rowAtPoint(e.getPoint()) + 1;
                System.out.println(row);

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

    private void preparePracownicyData() {
        pracownicyList = Singleton.updatePracownik();
    }

    public void setPracownikNr(int num){
        pracownikNr = num;
    }
    public int getPracownikNr(){
        return pracownikNr;
    }


}
