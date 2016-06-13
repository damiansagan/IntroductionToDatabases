package com.wbd.seniutsagan.main;


import com.wbd.seniutsagan.customer.CustomerPanel;
import com.wbd.seniutsagan.dto.KlientDTO;
import com.wbd.seniutsagan.service.KlientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel{
    private JTextField emailField = new JTextField(60);
    private JPasswordField passwordField = new JPasswordField(60);
    private JButton loginButton = new JButton("Zaloguj");
    private JFrame parentFrame;

    public LoginPanel(JFrame parentFrame) {
        this.parentFrame=parentFrame;
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        add(Box.createRigidArea(new Dimension(0,30)));
        add(prepareTitleLabel("Witaj w naszej restauracji!",35));
        add(prepareTitleLabel("Hakuna Matata!",35));
        add(Box.createRigidArea(new Dimension(0,60)));
        Font font = new Font("SansSerif", Font.BOLD, 30);
        emailField.setFont(font);
        emailField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        add(prepareTitleLabel("Login (e-mail):",28));
        add(emailField);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(prepareTitleLabel("Hasło:",28));
        add(passwordField);
        add(Box.createRigidArea(new Dimension(0,10)));

        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loginButton);
        ActionListener loginListener = e -> new Thread(() -> {
            KlientDTO tmpKlient = new KlientService().getKlient(emailField.getText(),passwordField.getText());
            if(tmpKlient!=null){
                Singleton.setLoggedInCustomer(tmpKlient);
                parentFrame.getContentPane().remove(this);
                parentFrame.getContentPane().add(new CustomerPanel(parentFrame));
                parentFrame.getContentPane().revalidate();
            }
            /*else if(new KlientService().getKlient(emailField.getText(),passwordField.getText())!=null){
                tutaj coś od pracownika
            }*/
            else{
                JOptionPane.showMessageDialog(null, "Nie znaleziono użytkownika o podanych parametrach", "Niepowodzenie!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }).start();
        loginButton.addActionListener(loginListener);
        emailField.addActionListener(loginListener);
        passwordField.addActionListener(loginListener);
    }


    private Component prepareTitleLabel(String title, int size) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.PLAIN, size));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
}
