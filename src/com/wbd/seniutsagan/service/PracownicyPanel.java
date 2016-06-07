package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.PracownicyDAO;
import com.wbd.seniutsagan.PracownikDTO;
import com.wbd.seniutsagan.SQLPracownikDAO;
import com.wbd.seniutsagan.main.Singleton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by monika03 on 07.06.16.
 */
public class PracownicyPanel extends JPanel{

    private List<PracownikDTO> pracownicyList ;
   // private Map<String,List<PozycjaMenuDTO>> pozycjeMenuRodzajami;
    private JScrollPane scrollPane;
    private JScrollPane scrollPanel;
    private JTable pracownicyTable;
    private PracownicyDAO pracownicyDAO = new SQLPracownikDAO();


    PracownicyPanel() {
        setLayout(new BorderLayout());
        createPracownicyTable();
        preparePracownicyData();
        createScrollPane();
        add(scrollPane, BorderLayout.CENTER);
    }
    public PracownicyPanel(int k) {
        setLayout(new BorderLayout());
        createPracownicyTable();
        preparePracownicyData();
        createScrollPane();
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createPracownicyTable(){
        try {
            // zwraca result w postaci ArrayList
            pracownicyList = pracownicyDAO.readAllPracownik();
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

        Object[] pracownicyColumns = new Object[4];
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
//        pozycjeMenu = Singleton.updateMenu();
//        //pozycjeMenuRodzajami = pozycjeMenu.stream().collect(Collectors.groupingBy(PozycjaMenuDTO::getRodzajOferty));
//        pozycjeMenuRodzajami = new LinkedHashMap<>();
//        pozycjeMenu.forEach(p -> {
//            List<PozycjaMenuDTO> rodzajList = pozycjeMenuRodzajami.get(p.getRodzajOferty());
//            if(rodzajList==null){
//                rodzajList = new ArrayList<>();
//                rodzajList.add(p);
//                pozycjeMenuRodzajami.put(p.getRodzajOferty(),rodzajList);
//            }
//            else{
//                rodzajList.add(p);
//            }
//        });
    }





}
