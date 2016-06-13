package com.wbd.seniutsagan.dao;


import com.wbd.seniutsagan.dto.KlientDTO;

public interface KlientDAO {

    boolean updateClient(KlientDTO klientDTO);
    KlientDTO getClient(String email, String password);
}
