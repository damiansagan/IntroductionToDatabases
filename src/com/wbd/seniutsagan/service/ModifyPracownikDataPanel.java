package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.Listeners;
import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dao.SQLPracownikDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;
import com.wbd.seniutsagan.main.Singleton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by monika03 on 09.06.16.
 */
public class ModifyPracownikDataPanel extends JPanel {

    private List<PracownikDTO> pracownicyList ;
    private JScrollPane scrollPane;
    private JTable pracownicyTable;
    private PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
    private int pracownikModifyRow;
    private Listeners listeners ;
    private GridBagConstraints gbc;
    private GridBagLayout gbcLayout;


    public ModifyPracownikDataPanel() {
        gbcLayout = new GridBagLayout();
        setLayout(gbcLayout);
        gbc = new GridBagConstraints();

        createFieldsToFill();
   //     createPracownicyTable();
     //   preparePracownicyData();
     //   createScrollPane();
    //    listeners = passedListeners;
       // add(scrollPane, BorderLayout.CENTER);
    }

    private void createFieldsToFill() {
        JLabel stanowiskoLabel = new JLabel("Stanowisko: ");
        JTextField stanowiskoTextField = new JTextField(20);
       // stanowiskoTextField.setSize(200, 24);
       // stanowiskoLabel.setBounds(110, 20, 130, 20);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Modyfikacja danych pracowników"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));

        stanowiskoLabel.setLabelFor(stanowiskoTextField);
        add(stanowiskoLabel);

        JLabel imieLabel = new JLabel("Imię: ");
        JTextField imieTextField = new JTextField(20);

        JLabel nazwiskoLabel = new JLabel("Nazwisko: ");
        JTextField nazwiskoTextField = new JTextField(20);

        JLabel dataLabel = new JLabel("Data urodzenia: ");
        JTextField dataTextField = new JTextField(20);

        JLabel peselLabel = new JLabel("PESEL: ");
        JTextField peselTextField = new JTextField(20);

        JLabel nrKontaLabel = new JLabel("Nr konta: ");
        JTextField nrKontaTextField = new JTextField(20);

        JLabel[] labels = {stanowiskoLabel,imieLabel,nazwiskoLabel,dataLabel,peselLabel,nrKontaLabel};
        JTextField[] textFields = {stanowiskoTextField,imieTextField,nazwiskoTextField,dataTextField,peselTextField,nrKontaTextField};

        addLabelTextRows(labels, textFields, gbcLayout, this);

        gbc.gridwidth = GridBagConstraints.LAST_LINE_END; //next-to-last
        gbc.fill = GridBagConstraints.NONE;      //reset to default
        gbc.weightx = 0.0; //reset to default
        JButton confirmBtn = new JButton("Confirm");
        this.add(confirmBtn,gbc);

    }
    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = gbc;

        c.anchor = GridBagConstraints.NORTH;
       c.insets=new Insets(10,10,10,10);
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {

            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0; //reset to default
           // c.weighty = ;
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.2;
           // c.weighty = 0.5;

            container.add(textFields[i], c);

        }
    }


}
