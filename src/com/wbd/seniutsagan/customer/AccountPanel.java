package com.wbd.seniutsagan.customer;

import com.wbd.seniutsagan.dto.KlientDTO;
import com.wbd.seniutsagan.dto.ZamowienieDTO;
import com.wbd.seniutsagan.main.Singleton;
import com.wbd.seniutsagan.service.KlientService;
import com.wbd.seniutsagan.service.ZamowienieService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.List;

class AccountPanel extends JPanel {

    private JScrollPane historyScrollPane;
    private JPanel accountParameters;
    private JTextField imieField, nazwiskoField, emailField, telefonField;
    private JPasswordField passwordField;
    private JButton applyButton;
    private KlientDTO currentAccount = Singleton.getLoggedInCustomer();
    private KlientService klientService = new KlientService();
    private ZamowienieService zamowienieService = new ZamowienieService();

    AccountPanel() {
        setLayout(new BorderLayout());


        addComponentListener ( new ComponentAdapter()
        {
            public void componentShown ( ComponentEvent e )
            {
                new Thread(() -> updateContent()).start();
            }

            public void componentHidden ( ComponentEvent e )
            {}
        } );
    }

    private void updateContent() {
        if(accountParameters!=null) remove(accountParameters);
        if(historyScrollPane!=null) remove(historyScrollPane);
        JLabel loadingLabel = new JLabel("Trwa aktualizowanie danych, proszę czekać...");
        loadingLabel.setHorizontalAlignment(JLabel.CENTER);
        add(loadingLabel, BorderLayout.CENTER);
        revalidate();
        prepareAccountParametersPanel();
        Thread t1 = new Thread(() -> checkoutCustomerData());
        Thread t2 = new Thread(() -> prepareHistoryScrollPane());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        remove(loadingLabel);
        add(accountParameters, BorderLayout.PAGE_START);
        add(historyScrollPane, BorderLayout.CENTER);
        revalidate();
    }

    private void checkoutCustomerData() {
        currentAccount=klientService.getKlient(currentAccount.getEmail(),currentAccount.getPassword());
        imieField.setText(currentAccount.getImie());
        nazwiskoField.setText(currentAccount.getNazwisko());
        emailField.setText(currentAccount.getEmail());
        telefonField.setText(currentAccount.getTelefonuNumer());
        passwordField.setText(currentAccount.getPassword());
    }

    private void prepareAccountParametersPanel() {
        accountParameters = new JPanel();
        accountParameters.setLayout(new BoxLayout(accountParameters,BoxLayout.PAGE_AXIS));
        JLabel imieLabel = new JLabel("Imie:");
        JLabel nazwiskoLabel = new JLabel("Nazwisko:");
        JLabel emailLabel = new JLabel("Adres e-mail:");
        JLabel telefonLabel = new JLabel("Numer telefonu:");
        JLabel passwordLabel = new JLabel("Hasło do konta:");

        imieField = new JTextField(60);
        nazwiskoField = new JTextField(60);
        emailField = new JTextField(60);
        telefonField = new JTextField(60);
        passwordField = new JPasswordField(60);

        imieField.setMaximumSize( imieField.getPreferredSize() );
        nazwiskoField.setMaximumSize( nazwiskoField.getPreferredSize() );
        emailField.setMaximumSize( emailField.getPreferredSize() );
        telefonField.setMaximumSize( telefonField.getPreferredSize() );
        passwordField.setMaximumSize( passwordField.getPreferredSize() );

        applyButton = new JButton("Zatwierdź zmiany");

        accountParameters.add(prepareTitleLabel("Dane użytkowika:"));
        accountParameters.add(imieLabel);
        accountParameters.add(imieField);
        accountParameters.add(nazwiskoLabel);
        accountParameters.add(nazwiskoField);
        accountParameters.add(emailLabel);
        accountParameters.add(emailField);
        accountParameters.add(telefonLabel);
        accountParameters.add(telefonField);
        accountParameters.add(passwordLabel);
        accountParameters.add(passwordField);
        accountParameters.add(Box.createRigidArea(new Dimension(0,10)));
        accountParameters.add(applyButton);
        accountParameters.add(Box.createRigidArea(new Dimension(0,10)));
        accountParameters.add(prepareTitleLabel("Historia zamówień:"));
        //accountParameters.setBackground(Color.cyan);
        //accountParameters.setMaximumSize(new Dimension(200,accountParameters.getPreferredSize().height));
        accountParameters.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        addListenersForDataPushing();
    }

    private void addListenersForDataPushing() {
        ActionListener actionListener = e ->
            new Thread(() -> {
                showDialog(updateRemoteAccount());
            }).start();

        imieField.addActionListener(actionListener);
        nazwiskoField.addActionListener(actionListener);
        emailField.addActionListener(actionListener);
        telefonField.addActionListener(actionListener);
        passwordField.addActionListener(actionListener);
        applyButton.addActionListener(actionListener);
    }

    private boolean updateRemoteAccount() {
        currentAccount.setImie(imieField.getText());
        currentAccount.setNazwisko(nazwiskoField.getText());
        currentAccount.setEmail(emailField.getText());
        currentAccount.setTelefonuNumer(telefonField.getText());
        currentAccount.setPassword(passwordField.getText());
        return klientService.updateKlient(currentAccount);
    }

    private Component prepareTitleLabel(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.PLAIN, 19));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return label;
    }

    private void showDialog(boolean status){
        if(!status){
            JOptionPane.showMessageDialog(null, "Błąd. Zmiany nie zostały zapisane. \nSprawdź swoje połączenie internetowe " +
                    "lub użyj innego zestawu danych. \nMaksymalne ilości znaków dla Imie/Nazwisko/E-mail/Telefon wynoszą odpowiednio "+
                    "20/30/30/9.", "UWAGA!",
                    JOptionPane.ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Zmiany zostały zaktualizowane.", "Sukces!",
                    JOptionPane.INFORMATION_MESSAGE);
    }

    private void prepareHistoryScrollPane() {
        List<ZamowienieDTO> zamowienieDTOList = zamowienieService.getAll(Singleton.getLoggedInCustomer());
        final String[] columnNames = { "Nr", "Status", "Pozycje zamówienia" };
        Object[][] data = new Object[zamowienieDTOList.size()][columnNames.length];
        for (int i = 0; i < zamowienieDTOList.size(); i++) {
            ZamowienieDTO zam = zamowienieDTOList.get(i);
            data[i][0] = zam.getId();
            data[i][1] = zam.getStatus();
            data[i][2] = zam.getPozycjeMenuAggregated();
        }

        JTable historyTable = new JTable(data, columnNames) {
            public String getToolTipText(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());

                Object value = getValueAt(row, column);
                return value == null ? null : value.toString();
            }
        };
        historyTable.setPreferredScrollableViewportSize(historyTable.getPreferredSize());
        historyTable.getColumnModel().getColumn(0).setMaxWidth(40);
        historyTable.getColumnModel().getColumn(0).setMinWidth(40);
        historyTable.getColumnModel().getColumn(1).setMinWidth(100);
        historyTable.getColumnModel().getColumn(1).setMaxWidth(100);
        historyScrollPane = new JScrollPane(historyTable);
    }
}
