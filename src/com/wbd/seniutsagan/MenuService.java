package com.wbd.seniutsagan;


import java.util.List;

class MenuService {
    private MenuDAO dao;

    MenuService(MenuDAO dao) {
        this.dao = dao;
    }


    List<PozycjaMenuDTO> getPozycjeMenu() {
        List<PozycjaMenuDTO> pozycjeMenu = dao.readAllPozycjaMenu();
        List<PotrawaDTO> potrawy = dao.readAllPotrawa();

        return pozycjeMenu;
    }
}
