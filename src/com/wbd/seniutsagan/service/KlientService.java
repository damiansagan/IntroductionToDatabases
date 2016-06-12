package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.dao.KlientDAO;

public class KlientService {
    KlientDAO dao;

    public KlientService(KlientDAO dao) {
        this.dao = dao;
    }


}
