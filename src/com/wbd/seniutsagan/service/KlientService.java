package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.dao.KlientDAO;
import com.wbd.seniutsagan.dao.SQLKlientDAO;
import com.wbd.seniutsagan.dto.KlientDTO;

public class KlientService {
    KlientDAO dao;

    public KlientService() {
        this.dao = new SQLKlientDAO();
    }

    public KlientDTO getKlient(int id){
        return dao.getClient(id);
    }

    public boolean updateKlient(KlientDTO klientDTO){
        return dao.updateClient(klientDTO);
    }


}
