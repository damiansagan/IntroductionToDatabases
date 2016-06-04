package com.wbd.seniutsagan;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        MenuDAO menuDAO = new SQLMenuDAO();
        MenuService menuService = new MenuService(menuDAO);

        List<PozycjaMenuDTO> pozycjeMenu = menuService.getPozycjeMenu();
        StringBuilder stringBuilder = new StringBuilder();
        for(PozycjaMenuDTO p : pozycjeMenu)
            stringBuilder.append(p.toString()).append('\n');
        System.out.println(stringBuilder.toString());
    }
}
