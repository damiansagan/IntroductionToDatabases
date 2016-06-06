package com.wbd.seniutsagan.dao;

import com.wbd.seniutsagan.dto.PozycjaMenuDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLMenuDAO implements MenuDAO {
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private static final String DB_USER = "DSAGAN";
    private static final String DB_PASSWORD = "dsagan";

    private static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
    }

    public List<PozycjaMenuDTO> readAllPozycjaMenu() {
        List<PozycjaMenuDTO> result = null;
        try ( Connection connection = getDBConnection();
              Statement stmt = connection.createStatement()
        ) {
            result = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("select id_pozycja_menu, rodzaj_oferty, nazwa, cena, potrawy from pozycje_menu join (SELECT id_pozycja_menu, LISTAGG(nazwa, ', ') WITHIN GROUP (ORDER BY nazwa) AS potrawy FROM  potrawy GROUP BY id_pozycja_menu) USING (id_pozycja_menu) ORDER BY rodzaj_oferty");
            while (rs.next()) {
                PozycjaMenuDTO position = new PozycjaMenuDTO(rs.getInt("ID_POZYCJA_MENU"), rs.getString("RODZAJ_OFERTY"), rs.getString("NAZWA"),rs.getDouble("CENA"),rs.getString("POTRAWY"));
                result.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
