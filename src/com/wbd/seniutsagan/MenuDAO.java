package com.wbd.seniutsagan;


import java.sql.SQLException;
import java.util.List;

interface MenuDAO {
    List<PozycjaMenuDTO> readAllPozycjaMenu() throws SQLException;
    List<PozycjaMenuDTO> readAllPotrawa();
}
