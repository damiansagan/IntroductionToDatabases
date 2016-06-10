package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.Listeners;
import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dao.SQLPracownikDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;
import com.wbd.seniutsagan.main.Singleton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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
    private int pracownikNr;
    private PracownicyPanel pracownicyPanel;


    public ModifyPracownikDataPanel(int row) {

        pracownikNr = row;
        System.out.println("Wybrałeś pracownika o numerze = " +pracownikNr);
        gbcLayout = new GridBagLayout();
        setLayout(gbcLayout);
        gbc = new GridBagConstraints();

        createFieldsToFill();
    }

    private void createFieldsToFill() {
        JLabel stanowiskoLabel = new JLabel("Stanowisko: ");
        JTextField stanowiskoTextField = new JTextField(20);
        stanowiskoTextField.setName("stanowiskoTextField");
        stanowiskoTextField.addFocusListener(new TextFieldsFocusListener(stanowiskoTextField));


       // stanowiskoTextField.setSize(200, 24);
       // stanowiskoLabel.setBounds(110, 20, 130, 20);

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Modyfikacja danych pracowników"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));

        stanowiskoLabel.setLabelFor(stanowiskoTextField);
       // add(stanowiskoLabel);

        JLabel imieLabel = new JLabel("Imię: ");
        JTextField imieTextField = new JTextField(20);
        imieLabel.setLabelFor(imieTextField);
        imieTextField.setName("imieTextField");
        imieTextField.addFocusListener(new TextFieldsFocusListener(imieTextField));



        JLabel nazwiskoLabel = new JLabel("Nazwisko: ");
        JTextField nazwiskoTextField = new JTextField(30);
        nazwiskoLabel.setLabelFor(nazwiskoTextField);
        nazwiskoTextField.setName("nazwiskoTextField");
        nazwiskoTextField.addFocusListener(new TextFieldsFocusListener(nazwiskoTextField));


        JLabel dataLabel = new JLabel("Data urodzenia: ");
        JTextField dataTextField = new JTextField(20);
        dataTextField.setName("dataTextField");
        dataLabel.setLabelFor(dataTextField);
        dataTextField.addFocusListener(new TextFieldsFocusListener(dataTextField));

        JLabel peselLabel = new JLabel("PESEL: ");
        JTextField peselTextField = new JTextField(20);
        peselTextField.setName("peselTextField");
        peselLabel.setLabelFor(peselTextField);
        peselTextField.addFocusListener(new TextFieldsFocusListener(peselTextField));

        JLabel nrKontaLabel = new JLabel("Nr konta: ");
        JTextField nrKontaTextField = new JTextField(20);
        nrKontaTextField.setName("nrKontaTextField");
        nrKontaLabel.setLabelFor(nrKontaTextField);
        nrKontaTextField.addFocusListener(new TextFieldsFocusListener(nrKontaTextField));

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
        c.insets = new Insets(10, 10, 10, 10);
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

    public boolean verify(JComponent input) {
        String name = input.getName();

        if(name.equals("stanowiskoTextField") || name.equals("imieTextField"))
        {
            String text = ((JTextField) input).getText().trim();
            if (isAllLetters(text)) return true;
            else return false;
        }
        if(name.equals("tf2"))
        {
            //other condition
            return true;
        }

        return true;
    }

    public boolean isAllLetters(String name) {
        int number = name.length();
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        if (number <= 20){return true;}
        else return false;
    }



}

    class TextFieldsFocusListener implements FocusListener{
        private JTextField textField;

        TextFieldsFocusListener(JTextField textfield){
            super();
            textField = textfield;
        }

        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {

            System.out.println(textField.getText());
            if (textField.getName().equals("stanowiskoTextField") || textField.getName().equals("imieTextField"))

            {
                boolean prawdziwe = verify(textField, 20);
                System.out.println("czy poprawnie wpisane : " + prawdziwe);
            }
            if (textField.getName().equals("nazwiskoTextField") )

            {
                boolean prawdziwe = verify(textField, 20);
                System.out.println("czy poprawnie wpisane nazwisko: " + prawdziwe);
            }

            if (textField.getName().equals("peselTextField") )

            {
                boolean prawdziwe = verifyPESEL(textField, 11);
                System.out.println("czy poprawnie wpisany pesel " + prawdziwe);
            }



        }

        public boolean verify(JComponent input, int countChars) {
            String name = input.getName();

            if(name.equals("stanowiskoTextField") || name.equals("imieTextField"))
            {
                String text = ((JTextField) input).getText().trim();
                if (isAllLetters(text, countChars)) return true;
                else return false;
            }

            return false;

        }

        public boolean verifyPESEL(JComponent input, int countChars) {

             String text = ((JTextField) input).getText().trim();
                return isAllNumbers(text, countChars) ;

        }


        public boolean isAllLetters(String name, int limitChars) {
            int number = name.length();
            char[] chars = name.toCharArray();

            for (char c : chars) {
                if(!Character.isLetter(c)) {
                    return false;
                }
            }

            if (number <= limitChars){return true;}
            else return false;
        }

        public boolean isAllNumbers(String name, int limitNum) {
            int number = name.length();
            String regex = "[0-9]+";

            if(name.matches(regex) && (number <= limitNum)){
                return true;
            }

//            if (number <= limitNum){return true;}
            else return false;
        }




    }

//    public class Varchar20Verifier extends InputVerifier {
//        @Override
//        public boolean verify(JComponent input) {
//            String text = ((JTextField) input).getText().trim();
//            if (text.isEmpty()) return false;
//            if (text.matches(".*\\d.*")) return false;
//
//            return true;
//        }


