package com.wbd.seniutsagan.dao;


import com.wbd.seniutsagan.dto.PozycjaMenuDTO;

import java.util.List;

public interface MenuDAO {
    List<PozycjaMenuDTO> readAllPozycjaMenu();
}
