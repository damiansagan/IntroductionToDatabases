package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.dao.SQLZamowienieDAO;
import com.wbd.seniutsagan.dao.ZamowienieDAO;
import com.wbd.seniutsagan.dto.KlientDTO;
import com.wbd.seniutsagan.dto.ZamowienieDTO;

import java.util.List;

public class ZamowienieService {
    private final ZamowienieDAO dao;

    public ZamowienieService() {
        this.dao = new SQLZamowienieDAO();
    }

    public boolean order(ZamowienieDTO zamowienieDTO) {
        return !zamowienieDTO.getPozycjeMenu().isEmpty() && dao.pushOrder(zamowienieDTO);
    }

    public List<ZamowienieDTO> getAll(KlientDTO klientDTO){
        return dao.getAll(klientDTO);
    }
}
