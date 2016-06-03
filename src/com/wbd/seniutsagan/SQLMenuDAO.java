package com.wbd.seniutsagan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class SQLMenuDAO implements MenuDAO {
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
            ResultSet rs = stmt.executeQuery("select * from Pozycje_Menu");
            while (rs.next()) {
                PozycjaMenuDTO position = new PozycjaMenuDTO(rs.getInt("ID_POZYCJA_MENU"));
                position.setRodzajOferty(rs.getString("RODZAJ_OFERTY"));
                position.setNazwa(rs.getString("NAZWA"));
                position.setCena(rs.getString("CENA"));
                position.setKawiarniaId(rs.getInt("ID_KAWIARNIA"));
                result.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<PozycjaMenuDTO> readAllPotrawa() {
        return null;
    }

}
