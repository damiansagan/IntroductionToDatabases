package com.wbd.seniutsagan;


import java.util.List;

class MenuService {
    private MenuDAO dao;

    MenuService(MenuDAO dao) {
        this.dao = dao;
    }

    List<PozycjaMenuDTO> getPozycjeMenu() {
        return dao.readAllPozycjaMenu();
    }
}
