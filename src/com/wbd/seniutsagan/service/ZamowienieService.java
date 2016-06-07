package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.dao.ZamowienieDAO;
import com.wbd.seniutsagan.dto.ZamowienieDTO;

public class ZamowienieService {
    private ZamowienieDAO dao;

    public ZamowienieService(ZamowienieDAO dao) {
        this.dao = dao;
    }

    public void order(ZamowienieDTO zamowienieDTO){

    }
}
