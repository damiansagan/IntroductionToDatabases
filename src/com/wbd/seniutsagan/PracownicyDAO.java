package com.wbd.seniutsagan;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by monika03 on 05.06.16.
 */
public interface PracownicyDAO {

    List<PracownikDTO> readAllPracownik() throws SQLException;


}
