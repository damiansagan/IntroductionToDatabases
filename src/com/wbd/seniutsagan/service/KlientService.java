package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.dao.KlientDAO;
import com.wbd.seniutsagan.dao.SQLKlientDAO;
import com.wbd.seniutsagan.dto.KlientDTO;

public class KlientService {
    private KlientDAO dao;

    public KlientService() {
        this.dao = new SQLKlientDAO();
    }

    public KlientDTO getKlient(String email, String password){
        return dao.getClient(email, password);
    }

    public boolean updateKlient(KlientDTO klientDTO){
        return dao.updateClient(klientDTO);
    }


}
