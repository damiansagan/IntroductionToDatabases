package com.wbd.seniutsagan.dao;

import com.wbd.seniutsagan.dto.KlientDTO;
import com.wbd.seniutsagan.dto.ZamowienieDTO;

import java.util.List;

public interface ZamowienieDAO {

    boolean pushOrder(ZamowienieDTO zamowienieDTO);
    List<ZamowienieDTO> getAll(KlientDTO klientDTO);
}
