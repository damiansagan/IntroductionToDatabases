package com.wbd.seniutsagan.main;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static javax.swing.SwingUtilities.invokeAndWait;

public class Main {

    public static void main(String[] args) throws SQLException, InvocationTargetException, InterruptedException {
        invokeAndWait(ApplicationGUI::new);
    }
}
