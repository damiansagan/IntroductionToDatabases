package com.wbd.seniutsagan.dao;

import com.wbd.seniutsagan.dto.PracownikDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by monika03 on 05.06.16.
 */
public interface PracownicyDAO {

    List<PracownikDTO> readMainPracownik() throws SQLException;


    List<PracownikDTO> readAllPracownik()throws SQLException;

    // wczytuje wybranego pracownika
    PracownikDTO readSelectedPracownik(int num)throws SQLException;


    ResultSet resultSetMainPracownik() throws SQLException;

    String[] headings(ResultSet pracownicyResultSet);

    void modifySelectedPracownik (int num, Map<String, String> modifyPracownikMap) throws SQLException;


}
