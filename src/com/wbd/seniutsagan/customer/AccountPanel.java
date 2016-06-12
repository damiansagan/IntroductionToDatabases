package com.wbd.seniutsagan.customer;

import com.wbd.seniutsagan.dao.SQLKlientDAO;
import com.wbd.seniutsagan.dto.KlientDTO;
import com.wbd.seniutsagan.main.Singleton;
import com.wbd.seniutsagan.service.KlientService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

class AccountPanel extends JPanel {

    private JScrollPane scrollPane;
    private JPanel scrollPanel;

    private JPanel accountParameters;
    private JPanel orderHistory;
    private JTextField imieField, nazwiskoField, emailField, telefonField;
    private JButton applyButton;
    private KlientDTO currentAccount = Singleton.getLoggedInCustomer();
    private KlientService klientService = new KlientService(new SQLKlientDAO());
    private boolean hasBeenChanged = false;

    AccountPanel() {
        setLayout(new BorderLayout());
        prepareAccountParameters();
        prepareOrderHistory();
        scrollPanel=new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel,BoxLayout.PAGE_AXIS));
        scrollPanel.add(accountParameters);
        scrollPanel.add(orderHistory);
        scrollPane = new JScrollPane(scrollPanel);
        add(scrollPane,BorderLayout.CENTER);
    }

    private void prepareAccountParameters() {
        accountParameters = new JPanel();
        accountParameters.setLayout(new BoxLayout(accountParameters,BoxLayout.PAGE_AXIS));
        accountParameters.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));

        JLabel imieLabel = new JLabel("Imie:");
        JLabel nazwiskoLabel = new JLabel("Nazwisko:");
        JLabel emailLabel = new JLabel("Adres e-mail:");
        JLabel telefonLabel = new JLabel("Numer telefonu:");

        imieField = new JTextField(60);
        nazwiskoField = new JTextField(60);
        emailField = new JTextField(60);
        telefonField = new JTextField(60);

        imieField.setMaximumSize( imieField.getPreferredSize() );
        nazwiskoField.setMaximumSize( nazwiskoField.getPreferredSize() );
        emailField.setMaximumSize( emailField.getPreferredSize() );
        telefonField.setMaximumSize( telefonField.getPreferredSize() );

        applyButton = new JButton("Zatwierdź zmiany");

        accountParameters.add(prepareTitleLabel("Dane użytkowika"));
        accountParameters.add(imieLabel);
        accountParameters.add(imieField);
        accountParameters.add(nazwiskoLabel);
        accountParameters.add(nazwiskoField);
        accountParameters.add(emailLabel);
        accountParameters.add(emailField);
        accountParameters.add(telefonLabel);
        accountParameters.add(telefonField);
        accountParameters.add(Box.createRigidArea(new Dimension(0,10)));
        accountParameters.add(applyButton);
        accountParameters.add(Box.createRigidArea(new Dimension(0,10)));
        accountParameters.add(prepareTitleLabel("Historia zamówień"));

        addListeners();
    }

    private void addListeners() {
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hasBeenChanged=true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                hasBeenChanged=true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                hasBeenChanged=true;
            }
        };

        ActionListener actionListener = e -> System.out.println(hasBeenChanged);

        imieField.getDocument().addDocumentListener(documentListener);
        nazwiskoField.getDocument().addDocumentListener(documentListener);
        emailField.getDocument().addDocumentListener(documentListener);
        telefonField.getDocument().addDocumentListener(documentListener);

        imieField.addActionListener(actionListener);
        nazwiskoField.addActionListener(actionListener);
        emailField.addActionListener(actionListener);
        telefonField.addActionListener(actionListener);
        applyButton.addActionListener(actionListener);
    }

    private Component prepareTitleLabel(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.PLAIN, 19));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        //label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private void prepareOrderHistory() {
        orderHistory = new JPanel();
    }
}
