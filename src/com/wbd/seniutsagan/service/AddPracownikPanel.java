package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.Listeners;
import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dao.SQLPracownikDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;
import com.wbd.seniutsagan.main.Singleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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
    private Map<String, String> collectPracownikData = new LinkedHashMap<String, String>();
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
       // stanowiskoTextField.setDragEnabled(true);
        StringVerifier stanowiskoVerifier = new StringVerifier();
        stanowiskoTextField.setInputVerifier(stanowiskoVerifier);
        stanowiskoTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                collectPracownikData.put("stanowisko",stanowiskoTextField.getText().trim());

            }
        });


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
        imieTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                collectPracownikData.replace("imie",imieTextField.getText().trim());
            }
        });



        JLabel nazwiskoLabel = new JLabel("Nazwisko: ");
        nazwiskoTextField = new JTextField(30);
        nazwiskoLabel.setLabelFor(nazwiskoTextField);
        nazwiskoTextField.setName("nazwiskoTextField");
        nazwiskoTextField.setInputVerifier(new StringVerifier());
        nazwiskoTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                collectPracownikData.replace("nazwisko",nazwiskoTextField.getText().trim());
            }
        });



        JLabel dataLabel = new JLabel("Data urodzenia: ");
        JTextField dataTextField = new JTextField(20);
        dataTextField.setName("dataTextField");
        dataLabel.setLabelFor(dataTextField);
        dataTextField.setInputVerifier(new BirthDateVerifier());
        dataTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                collectPracownikData.replace("data",dataTextField.getText().trim());

            }
        });


        JLabel peselLabel = new JLabel("PESEL: ");
        JTextField peselTextField = new JTextField(20);
        peselTextField.setName("peselTextField");
        peselLabel.setLabelFor(peselTextField);
        peselTextField.setInputVerifier(new PeselVerifier());
        peselTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                collectPracownikData.replace("pesel",peselTextField.getText().trim());

            }
        });


        JLabel nrKontaLabel = new JLabel("Nr konta: ");
        JTextField nrKontaTextField = new JTextField(20);
        nrKontaTextField.setName("nrKontaTextField");
        nrKontaLabel.setLabelFor(nrKontaTextField);
        nrKontaTextField.setInputVerifier(new BankAccountVerifier());
        nrKontaTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                collectPracownikData.replace("nrKonta",nrKontaTextField.getText().trim());

            }
        });


        JLabel idKawiarniaLabel = new JLabel("Id kawiarni: ");
        JTextField idKawiarniaTextField = new JTextField(20);
        idKawiarniaTextField.setName("idKawiarniaTextField");
        idKawiarniaLabel.setLabelFor(idKawiarniaTextField);
        idKawiarniaTextField.setInputVerifier(new IdVerifier());
        idKawiarniaTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                collectPracownikData.replace("idKawiarnia",idKawiarniaTextField.getText().trim());

            }
        });



        JLabel idLokalLabel = new JLabel("Id lokalu: ");
        JTextField idLokalTextField = new JTextField(20);
        idLokalTextField.setName("idLokalTextField");
        idLokalLabel.setLabelFor(idLokalTextField);
        idLokalTextField.setInputVerifier(new IdVerifier());
        idLokalTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                collectPracownikData.replace("idLokal",idLokalTextField.getText().trim());

            }
        });




        JLabel[] labels = {stanowiskoLabel, imieLabel, nazwiskoLabel, dataLabel, peselLabel, nrKontaLabel, idKawiarniaLabel, idLokalLabel};
        JTextField[] textFields = {stanowiskoTextField, imieTextField, nazwiskoTextField, dataTextField, peselTextField, nrKontaTextField, idKawiarniaTextField, idLokalTextField};

        addLabelTextRows(labels, textFields, gbcLayout, this);

        gbc.gridwidth = GridBagConstraints.LAST_LINE_END; //next-to-last
        gbc.fill = GridBagConstraints.NONE;      //reset to default
        gbc.weightx = 0.0; //reset to default
        JButton addBtn = new JButton("Add");
        addButtonListener = new AddButtonListener(managerPanel, this);
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


            if (tf.getName().equals("stanowiskoTextField")) {
                notification = "Proszę podać poprawną nazwę stanowiska!";
                countChars = 20;
            } else if (tf.getName().equals("imieTextField")) {
                notification = "Proszę podać poprawne imię!";
                countChars = 20;

            } else if (tf.getName().equals("nazwiskoTextField")) {
                notification = "Proszę podać poprawne nazwisko!";

                countChars = 30;
            }
            String text = ((JTextField) input).getText().trim();
            result = isAllLetters(text, countChars);
            if (!result) {
                JOptionPane.showMessageDialog(null, notification);
            }
            return result;


        }

        private boolean isAllLetters(String name, int limitChars) {
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


    }


    public class PeselVerifier extends InputVerifier {
        private boolean result;

        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText().trim();
            result = isEqualNumbers(text, 11);
            if (!result) {
                JOptionPane.showMessageDialog(null, "Proszę podać poprawny numer PESEL (11 znaków)");
            }
            return result;
        }

       private boolean isEqualNumbers(String name, int limitNum) {
            int number = name.length();
            String regex = "[0-9]+";
            if (number == 0) {
                return false;
            } else if (name.matches(regex) && (number == limitNum)) {
                return true;
            } else return false;
        }


    }


    public class BankAccountVerifier extends InputVerifier {
        private boolean result;

        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText().trim();
            result = isEqualNumbers(text, 26);
            if (!result) {
                JOptionPane.showMessageDialog(null, "Proszę podać poprawny nr Konta (26 znaków)!");
            }
            return result;

        }

        private boolean isEqualNumbers(String name, int limitNum) {
            int number = name.length();
            String regex = "[0-9]+";
            if (number == 0) {
                return false;
            } else if (name.matches(regex) && (number == limitNum)) {
                return true;
            } else return false;
        }
    }

    public class BirthDateVerifier extends InputVerifier {
        private boolean result;

        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText().trim();
            result = isValidDate(text);
            if (!result) {
                JOptionPane.showMessageDialog(null, "Proszę podać poprawną datę!");
            }
            return result;
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
}

    public class IdVerifier extends InputVerifier{
        private boolean result;

        @Override
        public boolean verify(JComponent input) {
            result = verifyNumbers(input);
            if (!result) {
                JOptionPane.showMessageDialog(null, "Proszę podać poprawne ID!");
            }
            else{

            };
            return result;
        }

    private boolean verifyNumbers(JComponent input) {

        String text = ((JTextField) input).getText().trim();

        return isAllNumbers(text,2);

    }

        private boolean isAllNumbers(String name, int limitNum) {
        int number = name.length();

        String regex = "[0-9]+";
        if(number == 0){
            System.out.println("Wchodze do isAllNumbers! ");
            return false;
        }


        else if (name.matches(regex) && (number <= limitNum)) {
            return true;
        }
        else return false;
    }

    }

        public Map<String, String> getPracownikDataMap() {
            return collectPracownikData;
        }

        public void setPracownikDataMap(Map<String, String> npracownikDataMap) {
            collectPracownikData = npracownikDataMap;
        }

    }


    class AddButtonListener implements ActionListener {

        private List<PracownikDTO> pracownicyList;
        private PracownicyDAO pracownicyDAO = new SQLPracownikDAO();
        private Map<String, String> addPracownikDataMap = new LinkedHashMap<String, String>();
        private ManagerPanel managerPanel;
        private AddPracownikPanel addPracownikPanel;



        AddButtonListener(ManagerPanel managerPanel, AddPracownikPanel addPracownikPanel) {
            super();
            this.managerPanel = managerPanel;
            this.addPracownikPanel = addPracownikPanel;


        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            addPracownikDataMap = addPracownikPanel.getPracownikDataMap();
            addPracownikData( addPracownikDataMap, managerPanel);

        }

        private void addPracownikData( Map<String, String> pracownikAddMap, ManagerPanel managerPanel){
            try {
                System.out.println("Jestem w add button listenerze.");
                pracownicyDAO.addPracownik(pracownikAddMap);

                // wyswietla pracownicyInfoPanel
                if(managerPanel.getPracownicyPanel()!=null) {
                    managerPanel.getPracownicyPanel().remove(managerPanel.getPracownicyPanel());
                }
                preparePracownicyData();
                PracownicyPanel pracownicyPanel = new PracownicyPanel(managerPanel.getManagerPanelListeners());
                managerPanel.getContainerPanel().add(pracownicyPanel, "PRACOWNICY");
                managerPanel.swapView("PRACOWNICY");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        private void preparePracownicyData() {
            pracownicyList = Singleton.updatePracownik();
        }

    }



