package com.wbd.seniutsagan.service;

import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;


import java.sql.SQLException;
import java.util.List;

/**
 * Created by monika03 on 07.06.16.
 */
public class PracownicyData {
    private PracownicyDAO dao;

    public PracownicyData(PracownicyDAO dao) {
        this.dao = dao;
    }

    public List<PracownikDTO> getPracownik() {
        try {
            return dao.readMainPracownik();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
