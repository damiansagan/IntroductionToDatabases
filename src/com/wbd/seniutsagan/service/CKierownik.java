package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.Cafe_Panel;
import com.wbd.seniutsagan.DbButtonsPanel;
import com.wbd.seniutsagan.Listeners;
import com.wbd.seniutsagan.MakeKierownikButtonsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by monika03 on 07.06.16.
 */
public class CKierownik {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;

    public String language;
    public KierownikListeners listeners;

    JFrame frame = new JFrame("Kierownik main window");
    JPanel panelCont = new JPanel();
    Cafe_Panel cafePanel ;
    KierownikButtonsPanel kierownikButtonsPanel ;
    DbButtonsPanel dbButtonsPanel ;
    CardLayout cl = new CardLayout();

    public CKierownik()
    {
        panelCont.setLayout(cl);
        cafePanel = new Cafe_Panel() ;
        kierownikButtonsPanel = new KierownikButtonsPanel();
        listeners = new KierownikListeners();
       // dbButtonsPanel = new DbButtonsPanel(frame);


    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CKierownik();
            }
        });
    }

}
