package com.wbd.seniutsagan;


import java.util.List;

class Singleton {
 private static final Singleton INSTANCE = new Singleton();

    private MenuService menuService;
    private List<PozycjaMenuDTO> pozycjaMenuDTOList;

    private Singleton() {
        menuService = new MenuService(new SQLMenuDAO());
    }

    static List<PozycjaMenuDTO> updateMenu(){
        Singleton s = Singleton.getInstance();
        return s.pozycjaMenuDTOList = s.menuService.getPozycjeMenu();
    }

    static List<PozycjaMenuDTO> getPozycjeMenu() {
        return Singleton.getInstance().pozycjaMenuDTOList;
    }

    private static Singleton getInstance() {
        return INSTANCE;
    }
}
