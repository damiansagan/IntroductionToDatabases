package com.wbd.seniutsagan;


import java.sql.SQLException;
import java.util.List;

interface MenuDAO {

    public List<PozycjaMenuDTO> readAllPozycjaMenu() throws SQLException;
    public List<PozycjaMenuDTO> readAllPotrawa();
}
