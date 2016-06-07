package com.wbd.seniutsagan.customer;

import com.wbd.seniutsagan.dto.PozycjaMenuDTO;
import com.wbd.seniutsagan.dto.ZamowienieDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class PozycjaMenuPanel extends JPanel implements MouseListener{

    private Integer counter = 0;
    private PozycjaMenuDTO pozycjaMenu;
    private ZamowienieDTO zamowienie;
    private JPanel orderPanel;
    private JPanel infoPanel;
    private JTextField quantityField = new JTextField(counter.toString(),2);

    PozycjaMenuPanel(PozycjaMenuDTO pozycjaMenu, ZamowienieDTO zamowienie) {
        this.pozycjaMenu = pozycjaMenu;
        this.zamowienie = zamowienie;
        createInfoPanel();
        createOrderPanel();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        add(infoPanel, BorderLayout.WEST);
        add(orderPanel, BorderLayout.EAST);
        setMaximumSize(new Dimension(10000, (int) getPreferredSize().getHeight()));
        addMouseListener(this);
    }

    private void createInfoPanel() {
        JLabel nazwa = new JLabel(pozycjaMenu.getNazwa());
        nazwa.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel potrawy = new JLabel(pozycjaMenu.getPotrawy());
        potrawy.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(nazwa, BorderLayout.PAGE_START);
        infoPanel.add(potrawy, BorderLayout.PAGE_END);
    }

    private void createOrderPanel() {
        JLabel cena = new JLabel(String.format("%.2f zł",pozycjaMenu.getCena()));
        cena.setFont(new Font("Arial", Font.BOLD, 18));
        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        plusButton.setMargin(new Insets(0,0,0,0));
        minusButton.setMargin(new Insets(0,0,0,0));
        plusButton.setPreferredSize(new Dimension(20,20));
        minusButton.setPreferredSize(new Dimension(20,20));
        orderPanel = new JPanel();
        orderPanel.add(cena);
        orderPanel.add(quantityField);
        orderPanel.add(plusButton);
        orderPanel.add(minusButton);
        quantityField.setEditable(false);
        quantityField.addMouseListener(this);
        plusButton.addMouseListener(this);
        minusButton.addMouseListener(this);
        plusButton.addActionListener(e -> updateCounter(1));
        minusButton.addActionListener(e -> updateCounter(-1));
    }

    private void updateCounter(int i) {
        if(!validateCounter(counter+i)) return;
        if(i>0)
            zamowienie.addPozycjaMenu(pozycjaMenu);
        else
            zamowienie.removePozycjaMenu(pozycjaMenu);
        counter+=i;
        quantityField.setText(counter.toString());
        orderPanel.repaint();
    }

    private boolean validateCounter(Integer integer) {
        return (integer >= 0 && integer <100);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.lightGray);
        orderPanel.setBackground(Color.lightGray);
        infoPanel.setBackground(Color.lightGray);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(null);
        orderPanel.setBackground(null);
        infoPanel.setBackground(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getComponent() instanceof JPanel)
            updateCounter(1);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getComponent() instanceof JPanel) {
            setBackground(Color.green);
            orderPanel.setBackground(Color.green);
            infoPanel.setBackground(Color.green);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getComponent() instanceof JPanel)
            mouseExited(e);
    }
}
