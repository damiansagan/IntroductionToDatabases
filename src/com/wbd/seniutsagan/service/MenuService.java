package com.wbd.seniutsagan.service;


import com.wbd.seniutsagan.dao.SQLMenuDAO;
import com.wbd.seniutsagan.dto.PozycjaMenuDTO;
import com.wbd.seniutsagan.dao.MenuDAO;

import java.util.List;

public class MenuService {
    private MenuDAO dao;

    public MenuService() {
        this.dao = new SQLMenuDAO();
    }

    public List<PozycjaMenuDTO> getPozycjeMenu() {
        return dao.readAllPozycjaMenu();
    }
}
