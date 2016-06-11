package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.Listeners;
import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dao.SQLPracownikDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;
import com.wbd.seniutsagan.main.Singleton;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private ConfirmButtonListener confirmButtonListener;
    private Map<String, String> collectPracownikData = new HashMap<String, String>();
    private ManagerPanel managerPanel;


    public ModifyPracownikDataPanel(int row, ManagerPanel managerPanel) {

        pracownikNr = row;
        this.managerPanel = managerPanel;
        System.out.println("Wybrałeś pracownika o numerze = " +pracownikNr);
        gbcLayout = new GridBagLayout();
        setLayout(gbcLayout);
        gbc = new GridBagConstraints();


        createFieldsToFill();
    }

    private void createFieldsToFill() {

        collectPracownikData.put("stanowisko","");
        collectPracownikData.put("imie","");
        collectPracownikData.put("nazwisko","");
        collectPracownikData.put("data","");
        collectPracownikData.put("pesel","");
        collectPracownikData.put("nrKonta","");
//        Iterator it = collectPracownikData.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            if(pair.getValue().equals("")){
//                System.out.println(pair.getKey() + " = empty" );
//            }
//            else {
//                System.out.println(pair.getKey() + " = " + pair.getValue());
//                it.remove(); // avoids a ConcurrentModificationException
//            }
//        }
        JLabel stanowiskoLabel = new JLabel("Stanowisko: ");
        JTextField stanowiskoTextField = new JTextField(20);
        stanowiskoTextField.setName("stanowiskoTextField");
        stanowiskoTextField.addFocusListener(new TextFieldsFocusListener(stanowiskoTextField, collectPracownikData));



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
        imieTextField.addFocusListener(new TextFieldsFocusListener(imieTextField, collectPracownikData));



        JLabel nazwiskoLabel = new JLabel("Nazwisko: ");
        JTextField nazwiskoTextField = new JTextField(30);
        nazwiskoLabel.setLabelFor(nazwiskoTextField);
        nazwiskoTextField.setName("nazwiskoTextField");
        nazwiskoTextField.addFocusListener(new TextFieldsFocusListener(nazwiskoTextField, collectPracownikData));


        JLabel dataLabel = new JLabel("Data urodzenia: ");
        JTextField dataTextField = new JTextField(20);
        dataTextField.setName("dataTextField");
        dataLabel.setLabelFor(dataTextField);
        dataTextField.addFocusListener(new TextFieldsFocusListener(dataTextField, collectPracownikData));

        JLabel peselLabel = new JLabel("PESEL: ");
        JTextField peselTextField = new JTextField(20);
        peselTextField.setName("peselTextField");
        peselLabel.setLabelFor(peselTextField);
        peselTextField.addFocusListener(new TextFieldsFocusListener(peselTextField, collectPracownikData));

        JLabel nrKontaLabel = new JLabel("Nr konta: ");
        JTextField nrKontaTextField = new JTextField(20);
        nrKontaTextField.setName("nrKontaTextField");
        nrKontaLabel.setLabelFor(nrKontaTextField);
        nrKontaTextField.addFocusListener(new TextFieldsFocusListener(nrKontaTextField, collectPracownikData));

        JLabel[] labels = {stanowiskoLabel,imieLabel,nazwiskoLabel,dataLabel,peselLabel,nrKontaLabel};
        JTextField[] textFields = {stanowiskoTextField,imieTextField,nazwiskoTextField,dataTextField,peselTextField,nrKontaTextField};

        addLabelTextRows(labels, textFields, gbcLayout, this);

        gbc.gridwidth = GridBagConstraints.LAST_LINE_END; //next-to-last
        gbc.fill = GridBagConstraints.NONE;      //reset to default
        gbc.weightx = 0.0; //reset to default
        JButton confirmBtn = new JButton("Confirm");
        confirmButtonListener = new ConfirmButtonListener(pracownikNr, managerPanel,this);
        confirmBtn.addActionListener(confirmButtonListener);

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


    public Map<String,String> getPracownikDataMap() {
        return collectPracownikData;
    }
}

    class TextFieldsFocusListener implements FocusListener {
        private JTextField textField;
        private Map<String, String> pracownikDataMap;

        TextFieldsFocusListener(JTextField textfield, Map<String, String> npracownikDataMap) {
            super();
            textField = textfield;
            pracownikDataMap = npracownikDataMap;
        }

        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {

            System.out.println(textField.getText());
            if (textField.getName().equals("stanowiskoTextField") )

            {
                boolean prawdziwe = verify(textField, 20);
                System.out.println("czy poprawnie wpisane stanowisko: " + prawdziwe);
                if(prawdziwe) {
                    pracownikDataMap.replace("stanowisko", textField.getText().trim());
                }
                //System.out.println("odebrane= " + pracownikDataMap.get("stanowisko"));

                if (!prawdziwe ){
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawną nazwę stanowiska!");

                }
            }

            if (
                    textField.getName().equals("imieTextField") )

            {
                boolean prawdziwe = verify(textField, 20);
                System.out.println("czy poprawnie wpisane imię: " + prawdziwe);
                if(prawdziwe) {
                    pracownikDataMap.replace("imie", textField.getText().trim());
                }
                if (!prawdziwe ){
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawne imię!");

                }
            }


            if (textField.getName().equals("nazwiskoTextField"))

            {
                boolean prawdziwe = verify(textField, 30);
                System.out.println("czy poprawnie wpisane nazwisko: " + prawdziwe);
                if(prawdziwe) {
                    pracownikDataMap.replace("nazwisko", textField.getText().trim());
                }
                if (!prawdziwe ){
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawne nazwisko(max 30 znaków)!");
                }
            }

            if (textField.getName().equals("peselTextField"))

            {
                boolean prawdziwe = verifyPESEL(textField, 11);
                System.out.println("czy poprawnie wpisany pesel " + prawdziwe);
                if(prawdziwe) {
                    pracownikDataMap.replace("pesel", textField.getText().trim());
                }
                if (!prawdziwe){
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawny numer PESEL (11 znaków)");
                }
            }

            if (textField.getName().equals("nrKontaTextField"))

            {
                boolean prawdziwe = verifyBankAccount(textField, 26);
                if(prawdziwe) {
                    pracownikDataMap.replace("nrKonta", textField.getText().trim());
                }
                System.out.println("czy poprawnie wpisany ne konta " + prawdziwe);
                if (!prawdziwe){
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawny nr Konta (26 znaków)");
                }
            }

            if (textField.getName().equals("dataTextField"))

            {
                boolean prawdziwe = isValidDate(textField.getText().trim());
                if(prawdziwe) {
                    pracownikDataMap.replace("data", textField.getText().trim());
                }
                System.out.println("czy poprawnie wpisana data " + prawdziwe);
//                if (!prawdziwe){
//                    JOptionPane.showMessageDialog(null, "Proszę podać poprawną datę (yyyy-mm-dd)!");
//                }
            }


        }

        public boolean verify(JComponent input, int countChars) {
            String name = input.getName();

            if (name.equals("stanowiskoTextField") || name.equals("imieTextField") || name.equals("nazwiskoTextField")) {
                String text = ((JTextField) input).getText().trim();
                return isAllLetters(text, countChars) ;

            }


            return false;

        }

        public boolean verifyPESEL(JComponent input, int countChars) {

            String text = ((JTextField) input).getText().trim();
            int number = text.length();
            if (number == 0 ){
                System.out.println("Pusty string");
                return true;

            }
            return isEqualNumbers(text, countChars);

        }

        public boolean verifyBankAccount(JComponent input, int countChars) {

            String text = ((JTextField) input).getText().trim();
            int number = text.length();
            if (number == 0 ){
                System.out.println("Pusty string");
                return true;

            }
            return isEqualNumbers(text, countChars);

        }

        public boolean isAllLetters(String name, int limitChars) {
            int number = name.length();
        if (number == 0 ){
                System.out.println("Pusty string");
                return true;

            }
            char[] chars = name.toCharArray();

            for (char c : chars) {
                if (!Character.isLetter(c)) {
                    return false;
                }
            }

            if (number <= limitChars) {
                return true;
            } else return false;
        }

        public boolean isAllNumbers(String name, int limitNum) {
            int number = name.length();
            if (number == 0 ){
                System.out.println("Pusty string");
                return true;

            }
            String regex = "[0-9]+";

            if (name.matches(regex) && (number <= limitNum)) {
                return true;
            } else return false;
        }

        public boolean isEqualNumbers(String name, int limitNum) {
            int number = name.length();
            if (number == 0 ){
                System.out.println("Pusty string");
                return true;

            }
            String regex = "[0-9]+";

            if (name.matches(regex) && (number == limitNum)) {
                return true;
            } else return false;
        }


        private boolean isValidDate(String dateString) {
            int number = dateString.length();
            if (number == 0 ){
                System.out.println("Pusty string");
                return true;

            }
            Date date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.parse(dateString);

                // return true;
                if (!dateString.equals(sdf.format(date))) {
                    JOptionPane.showMessageDialog(null, "Please the correct letter!","Computerized Automatic Teller Machine", 1);

                    return false;
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Proszę podać poprawną datę (yyyy-mm-dd)");
                ex.printStackTrace();
                return false;
            }

            return true;


        }

        public Map<String, String> getPracownikDataMap(){
            return pracownikDataMap;
        }
        public void setPracownikDataMap(Map<String, String> npracownikDataMap){
            pracownikDataMap = npracownikDataMap;
        }

    }

class ConfirmButtonListener implements ActionListener {
    private int pracownicyRowListener;
    private PracownikDTO pracownikDTO;
    private List<PracownikDTO> pracownicyList ;
    private PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
    private Map<String, String> modifyPraciwnikDataMap = new HashMap<String, String>();
    private ManagerPanel managerPanel;
    private ModifyPracownikDataPanel modifyPracownikDataPanel;

    ConfirmButtonListener(int pracownikNr, ManagerPanel managerPanel, ModifyPracownikDataPanel modifyPracownikDataPanel) {
        super();
        pracownicyRowListener = pracownikNr;
        this.managerPanel = managerPanel;
        this.modifyPracownikDataPanel = modifyPracownikDataPanel;
        // wywolac impemenracje modifypracownikdatamap

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        modifyPraciwnikDataMap = modifyPracownikDataPanel.getPracownikDataMap();
//        Iterator it = modifyPraciwnikDataMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            if(pair.getValue().equals("")){
//                System.out.println(pair.getKey() + " = empty" );
//            }
//            else {
//                System.out.println(pair.getKey() + " = " + pair.getValue());
//                it.remove(); // avoids a ConcurrentModificationException
//            }
            modifyPracownikData(pracownicyRowListener, modifyPraciwnikDataMap, managerPanel);

      //  }



    }
    public void setPracownicyRowListener(int num) { pracownicyRowListener= num; }

    private void modifyPracownikData(int pracownikNr, Map<String, String> pracownikModifyMap, ManagerPanel managerPanel){
        try {
            // zwraca result w postaci ArrayList
            //pracownicyList = pracownicyDAO.readAllPracownik();
            System.out.println("Jestem w confirm button listenerze.");
            pracownicyDAO.modifySelectedPracownik(pracownikNr, pracownikModifyMap);
            //pracownikDTO = pracownicyDAO;
            preparePracownicyData();
            managerPanel.getPracownicyPanel().remove(managerPanel.getPracownicyPanel());
            managerPanel.getContainerPanel().add(new PracownicyPanel(managerPanel.getManagerPanelListeners()), "PRACOWNICY");
            managerPanel.swapView("PRACOWNICY");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void preparePracownicyData() {
        pracownicyList = Singleton.updatePracownik();
    }

}

