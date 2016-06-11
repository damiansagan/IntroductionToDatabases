package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.Listeners;
import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dao.SQLPracownikDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by monika03 on 11.06.16.
 */
public class AddPracownikPanel extends JPanel {

    private List<PracownikDTO> pracownicyList;
    private JScrollPane scrollPane;
    private JTable pracownicyTable;
    private PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
    private int pracownikModifyRow;
    private Listeners listeners;
    private GridBagConstraints gbc;
    private GridBagLayout gbcLayout;
    private PracownicyPanel pracownicyPanel;
    private AddButtonListener addButtonListener;
    private Map<String, String> collectPracownikData = new HashMap<String, String>();
    private ManagerPanel managerPanel;

    private JTextField stanowiskoTextField;
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;


    public AddPracownikPanel(ManagerPanel managerPanel) {

        this.managerPanel = managerPanel;
        gbcLayout = new GridBagLayout();
        setLayout(gbcLayout);
        gbc = new GridBagConstraints();

        createFieldsToFill();
    }

    private void createFieldsToFill() {

        collectPracownikData.put("stanowisko", "");
        collectPracownikData.put("imie", "");
        collectPracownikData.put("nazwisko", "");
        collectPracownikData.put("data", "");
        collectPracownikData.put("pesel", "");
        collectPracownikData.put("nrKonta", "");
        collectPracownikData.put("idKawiarnia", "");
        collectPracownikData.put("idLokal", "");

        JLabel stanowiskoLabel = new JLabel("Stanowisko: ");
        stanowiskoTextField = new JTextField(20);
        stanowiskoTextField.setName("stanowiskoTextField");
        stanowiskoTextField.setDragEnabled(true);
        StringVerifier stanowiskoVerifier = new StringVerifier();
        stanowiskoTextField.setInputVerifier(stanowiskoVerifier);
       // if(!stanowiskoVerifier.getResult()){

        //}


        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Modyfikacja danych pracowników"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        stanowiskoLabel.setLabelFor(stanowiskoTextField);

        JLabel imieLabel = new JLabel("Imię: ");
        imieTextField = new JTextField(20);
        imieLabel.setLabelFor(imieTextField);
        imieTextField.setName("imieTextField");
        imieTextField.setInputVerifier(new StringVerifier());


        JLabel nazwiskoLabel = new JLabel("Nazwisko: ");
        nazwiskoTextField = new JTextField(30);
        nazwiskoLabel.setLabelFor(nazwiskoTextField);
        nazwiskoTextField.setName("nazwiskoTextField");
        nazwiskoTextField.setInputVerifier(new StringVerifier());


        JLabel dataLabel = new JLabel("Data urodzenia: ");
        JTextField dataTextField = new JTextField(20);
        dataTextField.setName("dataTextField");
        dataLabel.setLabelFor(dataTextField);
        dataTextField.addFocusListener(new TextFieldsAddFocusListener(dataTextField, collectPracownikData));

        JLabel peselLabel = new JLabel("PESEL: ");
        JTextField peselTextField = new JTextField(20);
        peselTextField.setName("peselTextField");
        peselLabel.setLabelFor(peselTextField);
        peselTextField.addFocusListener(new TextFieldsAddFocusListener(peselTextField, collectPracownikData));

        JLabel nrKontaLabel = new JLabel("Nr konta: ");
        JTextField nrKontaTextField = new JTextField(20);
        nrKontaTextField.setName("nrKontaTextField");
        nrKontaLabel.setLabelFor(nrKontaTextField);
        nrKontaTextField.addFocusListener(new TextFieldsAddFocusListener(nrKontaTextField, collectPracownikData));
//

        JLabel idLokalLabel = new JLabel("Id lokalu: ");
        JTextField idLokalTextField = new JTextField(20);
        idLokalTextField.setName("idLokalTextField");
        idLokalLabel.setLabelFor(idLokalTextField);
        idLokalTextField.addFocusListener(new TextFieldsAddFocusListener(idLokalTextField, collectPracownikData));

        JLabel idKawiarniaLabel = new JLabel("Id kawiarni: ");
        JTextField idKawiarniaTextField = new JTextField(20);
        idKawiarniaTextField.setName("idKawiarniaTextField");
        idKawiarniaLabel.setLabelFor(idKawiarniaTextField);
        idKawiarniaTextField.addFocusListener(new TextFieldsAddFocusListener(idKawiarniaTextField, collectPracownikData));

        JLabel[] labels = {stanowiskoLabel, imieLabel, nazwiskoLabel, dataLabel, peselLabel, nrKontaLabel, idKawiarniaLabel, idLokalLabel};
        JTextField[] textFields = {stanowiskoTextField, imieTextField, nazwiskoTextField, dataTextField, peselTextField, nrKontaTextField, idKawiarniaTextField, idLokalTextField};

        addLabelTextRows(labels, textFields, gbcLayout, this);

        gbc.gridwidth = GridBagConstraints.LAST_LINE_END; //next-to-last
        gbc.fill = GridBagConstraints.NONE;      //reset to default
        gbc.weightx = 0.0; //reset to default
        JButton addBtn = new JButton("Add");
        addButtonListener = new AddButtonListener(managerPanel);
        addBtn.addActionListener(addButtonListener);

        this.add(addBtn, gbc);

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


    public class StringVerifier extends InputVerifier {
        private int countChars;
        boolean result;
        String notification;


        @Override
        public boolean verify(JComponent input) {
            JTextField tf = (JTextField) input;


            if (tf.getName().equals("stanowiskoTextField") ) {
                notification = "Proszę podać poprawną nazwę stanowiska!";
                countChars = 20;
            }
            else if( tf.getName().equals("imieTextField")){
                notification = "Proszę podać poprawne imię!";
                countChars = 20;

            }

            else if (tf.getName().equals("nazwiskoTextField")) {
                notification = "Proszę podać poprawne nazwisko!";

                countChars = 30;
            }
            String text = ((JTextField) input).getText().trim();
            result = isAllLetters(text, countChars);
            if(!result){
                JOptionPane.showMessageDialog(null, notification);
            }
            return result;


        }

        public boolean isAllLetters(String name, int limitChars) {
            int number = name.length();
            if (number == 0) {
                return false;
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

        public boolean getResult(){
            return result;
        }



    }


//    public boolean verifyPESEL(JComponent input, int countChars) {
//
//        String text = ((JTextField) input).getText().trim();
//
//        return isEqualNumbers(text, countChars);
//
//    }
//
//    public boolean verifyBankAccount(JComponent input, int countChars) {
//
//        String text = ((JTextField) input).getText().trim();
//
//
//        return isEqualNumbers(text, countChars);
//
//    }
//
//    public boolean verifyNumbers(JComponent input) {
//
//        String text = ((JTextField) input).getText().trim();
//
//        return isAllNumbers(text,2);
//
//    }
//
//    public boolean isAllLetters(String name, int limitChars) {
//        int number = name.length();
//        if(number == 0){
//            return false;
//        }
//        char[] chars = name.toCharArray();
//
//        for (char c : chars) {
//            if (!Character.isLetter(c)) {
//                return false;
//            }
//        }
//
//        if (number <= limitChars) {
//            return true;
//        } else return false;
//    }
//
//    public boolean isAllNumbers(String name, int limitNum) {
//        int number = name.length();
//
//        String regex = "[0-9]+";
//        if(number == 0){
//            System.out.println("Wchodze do isAllNumbers! ");
//            return false;
//        }
//
//
//        else if (name.matches(regex) && (number <= limitNum)) {
//            return true;
//        }
//        else return false;
//    }
//
//    public boolean isEqualNumbers(String name, int limitNum) {
//        int number = name.length();
//        String regex = "[0-9]+";
//        if(number == 0){
//            return false;
//        }
//
//
//        else if (name.matches(regex) && (number == limitNum)) {
//            return true;
//        }
//
//        else return false;
//    }
//
//
//    private boolean isValidDate(String dateString) {
//        int number = dateString.length();
//        if(number == 0){
//            return false;
//        }
//        Date date = null;
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            date = sdf.parse(dateString);
//
//            // return true;
//            if (!dateString.equals(sdf.format(date))) {
//                //    JOptionPane.showMessageDialog(null, "Please the correct letter!","Computerized Automatic Teller Machine", 1);
//
//                return false;
//            }
//        } catch (ParseException ex) {
//            // JOptionPane.showMessageDialog(null, "Proszę podać poprawną datę (yyyy-mm-dd)");
//            ex.printStackTrace();
//            return false;
//        }
//
//        return true;
//
//
//    }
//
//
//
//


    class TextFieldsAddFocusListener implements FocusListener {
        private JTextField textField;
        private Map<String, String> pracownikDataMap;

        TextFieldsAddFocusListener(JTextField textField, Map<String, String> npracownikDataMap) {
            super();
            this.textField = textField;
            System.out.println("Focus listener: " + textField.getName());

            pracownikDataMap = npracownikDataMap;
        }

        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {

            System.out.println("Wchodzę do focus lost: " + textField.getName());
            System.out.println("opposite comp: " + e.getOppositeComponent().getName());
            //  System.out.println(textField.getText());
//            if (textField.getName().equals("stanowiskoTextField") )
//
//
//            {
//                boolean prawdziwe = verify(textField, 20);
//                System.out.println("czy poprawnie wpisane stanowisko: " + prawdziwe);
//                if(prawdziwe) {
//                    pracownikDataMap.replace("stanowisko", textField.getText().trim());
//                }
//                //System.out.println("odebrane= " + pracownikDataMap.get("stanowisko"));
//
//                if (!prawdziwe ){
//                    JOptionPane.showMessageDialog(null, "Proszę podać poprawną nazwę stanowiska!");
//
//                }
//            }

//           if (
//                    textField.getName().equals("imieTextField") )
//
//            {
//                boolean prawdziwe = verify(textField, 20);
//                System.out.println("czy poprawnie wpisane imię: " + prawdziwe);
//                if(prawdziwe) {
//                    pracownikDataMap.replace("imie", textField.getText().trim());
//                }
//                if (!prawdziwe ){
//                    JOptionPane.showMessageDialog(null, "Proszę podać poprawne imię!");
//
//                }
//            }

//
//             if (textField.getName().equals("nazwiskoTextField"))
//
//            {
//                boolean prawdziwe = verify(textField, 30);
//                System.out.println("czy poprawnie wpisane nazwisko: " + prawdziwe);
//                if(prawdziwe) {
//                    pracownikDataMap.replace("nazwisko", textField.getText().trim());
//                }
//                if (!prawdziwe ){
//                    JOptionPane.showMessageDialog(null, "Proszę podać poprawne nazwisko(max 30 znaków)!");
//                }
//            }

            if (textField.getName().equals("peselTextField"))

            {
                boolean prawdziwe = verifyPESEL(textField, 11);
                System.out.println("czy poprawnie wpisany pesel " + prawdziwe);
                if (prawdziwe) {
                    pracownikDataMap.replace("pesel", textField.getText().trim());
                }
                if (!prawdziwe) {
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawny numer PESEL (11 znaków)");
                }
            }

            if (textField.getName().equals("nrKontaTextField"))

            {
                boolean prawdziwe = verifyBankAccount(textField, 26);
                if (prawdziwe) {
                    pracownikDataMap.replace("nrKonta", textField.getText().trim());
                }
                System.out.println("czy poprawnie wpisany nr konta " + prawdziwe);
                if (!prawdziwe) {
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawny nr Konta (26 znaków)");
                }
            }

            if (textField.getName().equals("dataTextField"))

            {
                boolean prawdziwe = isValidDate(textField.getText().trim());
                if (prawdziwe) {
                    pracownikDataMap.replace("data", textField.getText().trim());
                }
                System.out.println("czy poprawnie wpisana data " + prawdziwe);
                if (!prawdziwe) {
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawną datę (yyyy-mm-dd)!");
                }
            }

            if (textField.getName().equals("idLokalTextField"))

            {
                boolean prawdziwe = verifyNumbers(textField);
                if (prawdziwe) {
                    pracownikDataMap.replace("idLokal", textField.getText().trim());
                }
                System.out.println("czy poprawnie wpisane id lokal " + prawdziwe);
                if (!prawdziwe) {
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawne id lokalu!");
                }
            }

            if (textField.getName().equals("idKawiarniaTextField"))

            {
                boolean prawdziwe = verifyNumbers(textField);
                if (prawdziwe) {
                    pracownikDataMap.replace("idKawiarnia", textField.getText().trim());
                }
                System.out.println("czy poprawnie wpisane id kawiarnia " + prawdziwe);
                if (!prawdziwe) {
                    JOptionPane.showMessageDialog(null, "Proszę podać poprawne id kawiarni!");
                }
            }


        }

        public boolean verify(JComponent input, int countChars) {
            String name = input.getName();

            if (name.equals("stanowiskoTextField") || name.equals("imieTextField") || name.equals("nazwiskoTextField")) {
                String text = ((JTextField) input).getText().trim();
                return isAllLetters(text, countChars);

            }


            return false;

        }

        public boolean verifyPESEL(JComponent input, int countChars) {

            String text = ((JTextField) input).getText().trim();

            return isEqualNumbers(text, countChars);

        }

        public boolean verifyBankAccount(JComponent input, int countChars) {

            String text = ((JTextField) input).getText().trim();


            return isEqualNumbers(text, countChars);

        }

        public boolean verifyNumbers(JComponent input) {

            String text = ((JTextField) input).getText().trim();

            return isAllNumbers(text, 2);

        }

        public boolean isAllLetters(String name, int limitChars) {
            int number = name.length();
            if (number == 0) {
                return false;
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

            String regex = "[0-9]+";
            if (number == 0) {
                System.out.println("Wchodze do isAllNumbers! ");
                return false;
            } else if (name.matches(regex) && (number <= limitNum)) {
                return true;
            } else return false;
        }

        public boolean isEqualNumbers(String name, int limitNum) {
            int number = name.length();
            String regex = "[0-9]+";
            if (number == 0) {
                return false;
            } else if (name.matches(regex) && (number == limitNum)) {
                return true;
            } else return false;
        }


        private boolean isValidDate(String dateString) {
            int number = dateString.length();
            if (number == 0) {
                return false;
            }
            Date date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.parse(dateString);

                // return true;
                if (!dateString.equals(sdf.format(date))) {
                    //    JOptionPane.showMessageDialog(null, "Please the correct letter!","Computerized Automatic Teller Machine", 1);

                    return false;
                }
            } catch (ParseException ex) {
                // JOptionPane.showMessageDialog(null, "Proszę podać poprawną datę (yyyy-mm-dd)");
                ex.printStackTrace();
                return false;
            }

            return true;


        }

        public Map<String, String> getPracownikDataMap() {
            return pracownikDataMap;
        }

        public void setPracownikDataMap(Map<String, String> npracownikDataMap) {
            pracownikDataMap = npracownikDataMap;
        }

    }

    class AddButtonListener implements ActionListener {
        private PracownikDTO pracownikDTO;
        private List<PracownikDTO> pracownicyList;
        private PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
        private Map<String, String> modifyPracownikDataMap = new HashMap<String, String>();
        private ManagerPanel managerPanel;


        AddButtonListener(ManagerPanel managerPanel) {
            super();
            this.managerPanel = managerPanel;

            // wywolac impemenracje modifypracownikdatamap

        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            //    modifyPracownikDataMap = modifyPracownikDataPanel.getPracownikDataMap();
            //  modifyPracownikData(pracownicyRowListener, modifyPracownikDataMap, managerPanel);

        }

//        private void modifyPracownikData(int pracownikNr, Map<String, String> pracownikModifyMap, ManagerPanel managerPanel){
//            try {
//                System.out.println("Jestem w confirm button listenerze.");
//                pracownicyDAO.modifySelectedPracownik(pracownikNr, pracownikModifyMap);
//
//                // wyswietla pracownicyInfoPanel
//                if(managerPanel.getPracownicyInfoPanel()!=null) {
//                    managerPanel.getPracownicyInfoPanel().remove(managerPanel.getPracownicyInfoPanel());
//                }
//                PracownicyInfoPanel pracownicyInfoPanel = new PracownicyInfoPanel(pracownicyRowListener);
//                managerPanel.getContainerPanel().add(pracownicyInfoPanel, "PRACOWNICY_INFO");
//                managerPanel.swapView("PRACOWNICY_INFO");
//
//                // zamiast pracownicyInfoPanel moze wyswietlic PracownicyPanel
//                //  preparePracownicyData();
////            managerPanel.getPracownicyPanel().remove(managerPanel.getPracownicyPanel());
////            managerPanel.getContainerPanel().add(new PracownicyPanel(managerPanel.getManagerPanelListeners()), "PRACOWNICY");
////            managerPanel.swapView("PRACOWNICY");
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//        private void preparePracownicyData() {
//            pracownicyList = Singleton.updatePracownik();
//        }

    }
}

//}



