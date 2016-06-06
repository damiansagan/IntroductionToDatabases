package com.wbd.seniutsagan.customer;

import com.wbd.seniutsagan.dto.PozycjaMenuDTO;
import com.wbd.seniutsagan.dto.ZamowienieDTO;
import com.wbd.seniutsagan.main.Singleton;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class MenuOrderPanel extends JPanel {

    private List<PozycjaMenuDTO> pozycjeMenu;
    private Map<String,List<PozycjaMenuDTO>> pozycjeMenuRodzajami;
    private JScrollPane scrollPane;
    private JPanel scrollPanel;
    private ZamowienieDTO zamowienieDTO;

    MenuOrderPanel() {
        preparePozycjeMenu();
        createScrollPane();
        setLayout(new BorderLayout());
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
            v.forEach(p -> scrollPanel.add(new PozycjaMenuPanel(p)));
        });
        JButton orderButton = new JButton("Zarezerwuj");
        orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPanel.add(orderButton);

        scrollPane = new JScrollPane(scrollPanel);
        scrollPanel.setMaximumSize(new Dimension(300,300));
    }

    private void preparePozycjeMenu() {
        pozycjeMenu = Singleton.getPozycjeMenu();
        pozycjeMenuRodzajami = pozycjeMenu.stream().collect(Collectors.groupingBy(PozycjaMenuDTO::getRodzajOferty));
        /*pozycjeMenuRodzajami = new LinkedHashMap<>();
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
        });*/
    }


}
