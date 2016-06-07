package com.wbd.seniutsagan.main;


import com.wbd.seniutsagan.PracownikDTO;
import com.wbd.seniutsagan.SQLPracownikDAO;
import com.wbd.seniutsagan.dao.SQLMenuDAO;
import com.wbd.seniutsagan.dao.SQLZamowienieDAO;
import com.wbd.seniutsagan.dto.PozycjaMenuDTO;
import com.wbd.seniutsagan.service.MenuService;
import com.wbd.seniutsagan.service.PracownicyData;
import com.wbd.seniutsagan.service.ZamowienieService;

import java.util.List;

public class Singleton {
 private static final Singleton INSTANCE = new Singleton();

    private MenuService menuService;
    private ZamowienieService zamowienieService;
    private PracownicyData pracownicyData;
    private List<PozycjaMenuDTO> pozycjaMenuDTOList;
    private List<PracownikDTO> pracownikDTOList;

    private Singleton() {
        menuService = new MenuService(new SQLMenuDAO());
        pracownicyData = new PracownicyData(new SQLPracownikDAO());
        zamowienieService = new ZamowienieService(new SQLZamowienieDAO());
    }

    public static List<PozycjaMenuDTO> updateMenu(){
        Singleton s = Singleton.getInstance();
        return s.pozycjaMenuDTOList = s.menuService.getPozycjeMenu();
    }

    public static List<PracownikDTO> updatePracownik(){
        Singleton s = Singleton.getInstance();
        return s.pracownikDTOList = s.pracownicyData.getPracownik();
    }

    public static List<PozycjaMenuDTO> getPozycjeMenu() {
        return Singleton.getInstance().pozycjaMenuDTOList;
    }

    public static List<PracownikDTO> getPracownik() {
        return Singleton.getInstance().pracownikDTOList;
    }

    private static Singleton getInstance() {
        return INSTANCE;
    }

    public static MenuService getMenuService() {
        return Singleton.getInstance().menuService;
    }

    public static ZamowienieService getZamowienieService() {
        return Singleton.getInstance().zamowienieService;
    }
}
