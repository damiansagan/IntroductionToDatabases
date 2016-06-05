package com.wbd.seniutsagan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by monika03 on 05.06.16.
 */
public class SQLPracownikDAO implements PracownicyDAO {

    private static final String DB_CONNECTION = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private static final String DB_USER = "MSENIUT";
    private static final String DB_PASSWORD = "mseniut";

    private static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }

    @Override
    public List<PracownikDTO> readAllPracownik() throws SQLException {
        List<PracownikDTO> result = null;
        try (Connection connection = getDBConnection();
             Statement stmt = connection.createStatement()
        ) {
            result = new ArrayList<>();
//            ResultSet rs = stmt.executeQuery("select * from Pracownicy");
//            while (rs.next()) {
//                PracownikDTO pracownik = new PracownikDTO(rs.getInt("ID_PRACOWNIK"));
//                pracownik.setStanowisko(rs.getString("STANOWISKO"));
//                pracownik.setImie(rs.getString("IMIE"));
//                pracownik.setNazwisko(rs.getString("NAZWISKO"));
//                pracownik.setDataUrodzenia(rs.getDate("DATA_URODZENIA"));
//                pracownik.setPESEL(rs.getString("PESEL"));
//                pracownik.setNrKonta(rs.getString("NR_KONTA"));
//                pracownik.setKawiarniaId(rs.getInt("ID_KAWIARNIA"));
//                pracownik.setLokalId(rs.getInt("ID_LOKAL"));
//
//                result.add(pracownik);
//            }
            ResultSet rs = stmt.executeQuery("select ID_PRACOWNIK, STANOWISKO, IMIE, NAZWISKO from Pracownicy order by ID_PRACOWNIK");
            while (rs.next()){
                PracownikDTO pracownik = new PracownikDTO(rs.getInt("ID_PRACOWNIK"));
                pracownik.setStanowisko(rs.getString("STANOWISKO"));
                pracownik.setImie(rs.getString("IMIE"));
                pracownik.setNazwisko(rs.getString("NAZWISKO"));
                result.add(pracownik);
//                PracownikDTO pracownik = new PracownikDTO(rs.getInt("ID_PRACOWNIK"));
//                pracownik.setStanowisko(rs.getString("STANOWISKO"));
//                pracownik.setImie(rs.getString("IMIE"));
//                pracownik.setNazwisko(rs.getString("NAZWISKO"));
//                pracownik.setDataUrodzenia(rs.getDate("DATA_URODZENIA"));
//                pracownik.setPESEL(rs.getString("PESEL"));
//                pracownik.setNrKonta(rs.getString("NR_KONTA"));
//                pracownik.setKawiarniaId(rs.getInt("ID_KAWIARNIA"));
//                pracownik.setLokalId(rs.getInt("ID_LOKAL"));
//
//                result.add(pracownik);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

}
