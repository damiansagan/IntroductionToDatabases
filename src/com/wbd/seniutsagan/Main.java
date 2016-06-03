package com.wbd.seniutsagan;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        MenuDAO menuDAO = new SQLMenuDAO();
        List<PozycjaMenuDTO> pozycjeMenu = null;
        pozycjeMenu = menuDAO.readAllPozycjaMenu();
        StringBuilder stringBuilder = new StringBuilder();
        for(PozycjaMenuDTO p : pozycjeMenu)
            stringBuilder.append(p.toString()).append('\n');
        System.out.println(stringBuilder.toString());
    }
}
