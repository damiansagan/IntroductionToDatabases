package com.wbd.seniutsagan.customer;

import com.wbd.seniutsagan.dto.PozycjaMenuDTO;
import com.wbd.seniutsagan.dto.ZamowienieDTO;
import com.wbd.seniutsagan.main.Singleton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class MenuOrderPanel extends JPanel {

    private List<PozycjaMenuDTO> pozycjeMenu;
    private Map<String,List<PozycjaMenuDTO>> pozycjeMenuRodzajami;
    private JScrollPane scrollPane;
    private JPanel scrollPanel;
    private ZamowienieDTO zamowienieDTO = new ZamowienieDTO(Singleton.getLoggedInCustomerID());

    MenuOrderPanel() {
        setLayout(new BorderLayout());
        preparePozycjeMenu();
        createScrollPane();
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createScrollPane() {
        scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
        pozycjeMenuRodzajami.forEach((k,v) -> {
            JLabel label = new JLabel(k);
            label.setFont(new Font("Arial", Font.PLAIN, 19));
            label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            scrollPanel.add(label);
            v.forEach(p -> scrollPanel.add(new PozycjaMenuPanel(p,zamowienieDTO)));
        });
        JButton orderButton = new JButton("Zarezerwuj");
        orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPanel.add(Box.createRigidArea(new Dimension(0,10)));
        scrollPanel.add(orderButton);
        scrollPane = new JScrollPane(scrollPanel);
        orderButton.addActionListener(e -> processOrder());
    }

    private void processOrder() {
        if(zamowienieDTO.getPozycjeMenu().isEmpty()){
            JOptionPane.showMessageDialog(null, "Niestety, zamówienie jest puste i nie zostało złożone.", "Na pewno?",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(Singleton.getZamowienieService().order(zamowienieDTO)){
            zamowienieDTO=new ZamowienieDTO(Singleton.getLoggedInCustomerID());
            recreateScrollPane();
            JOptionPane.showMessageDialog(null, "Zamówienie zostało złożone.", "Dziękujemy!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Błąd. Zamówienie nie zostało złożone.", "UWAGA!",
                    JOptionPane.ERROR_MESSAGE);
    }

    private void recreateScrollPane() {
        remove(scrollPane);
        createScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
    }

    private void preparePozycjeMenu() {
        pozycjeMenu = Singleton.updateMenu();
        //pozycjeMenuRodzajami = pozycjeMenu.stream().collect(Collectors.groupingBy(PozycjaMenuDTO::getRodzajOferty));
        pozycjeMenuRodzajami = new LinkedHashMap<>();
        pozycjeMenu.forEach(p -> {
            List<PozycjaMenuDTO> rodzajList = pozycjeMenuRodzajami.get(p.getRodzajOferty());
            if(rodzajList==null){
                rodzajList = new ArrayList<>();
                rodzajList.add(p);
                pozycjeMenuRodzajami.put(p.getRodzajOferty(),rodzajList);
            }
            else{
                rodzajList.add(p);
            }
        });
    }


}
