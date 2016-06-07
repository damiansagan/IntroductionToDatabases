package com.wbd.seniutsagan.main;


import com.wbd.seniutsagan.dao.SQLMenuDAO;
import com.wbd.seniutsagan.dao.SQLZamowienieDAO;
import com.wbd.seniutsagan.dto.PozycjaMenuDTO;
import com.wbd.seniutsagan.service.MenuService;
import com.wbd.seniutsagan.service.ZamowienieService;

import java.util.List;

public class Singleton {
 private static final Singleton INSTANCE = new Singleton();

    private MenuService menuService;
    private ZamowienieService zamowienieService;
    private List<PozycjaMenuDTO> pozycjaMenuDTOList;
    private Integer loggedInCustomerID;

    private Singleton() {
        menuService = new MenuService(new SQLMenuDAO());
        zamowienieService = new ZamowienieService(new SQLZamowienieDAO());
        loggedInCustomerID=1; //FOR TESTS ONLY!!!
    }

    public static List<PozycjaMenuDTO> updateMenu(){
        Singleton s = Singleton.getInstance();
        return s.pozycjaMenuDTOList = s.menuService.getPozycjeMenu();
    }

    public static List<PozycjaMenuDTO> getPozycjeMenu() {
        return Singleton.getInstance().pozycjaMenuDTOList;
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

    public static Integer getLoggedInCustomerID() {
        return Singleton.getInstance().loggedInCustomerID;
    }

    public static void setLoggedInCustomerID(Integer customerID) {
        Singleton.getInstance().loggedInCustomerID=customerID;
    }
}
