package com.wbd.seniutsagan.main;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static javax.swing.SwingUtilities.invokeAndWait;

public class Main {

    public static void main(String[] args) throws SQLException, InvocationTargetException, InterruptedException {
        Singleton.updateMenu();

        /*List<PozycjaMenuDTO> pozycjeMenu = Singleton.getPozycjeMenu();
        StringBuilder stringBuilder = new StringBuilder();
        for(PozycjaMenuDTO p : pozycjeMenu)
            stringBuilder.append(p.toString()).append('\n');
        System.out.println(stringBuilder.toString());*/

        // provide gui to be run on SWING thread
        invokeAndWait(ApplicationGUI::new);
    }
}
