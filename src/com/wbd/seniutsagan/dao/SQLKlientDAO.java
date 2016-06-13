package com.wbd.seniutsagan.dao;

import com.wbd.seniutsagan.dto.KlientDTO;

import java.sql.*;

public class SQLKlientDAO implements KlientDAO {
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private static final String DB_USER = "DSAGAN";
    private static final String DB_PASSWORD = "dsagan";

    private static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
    }

    @Override
    public boolean updateClient(KlientDTO klientDTO) {
        try (Connection con = getDBConnection();
             PreparedStatement statement = con.prepareStatement("update klienci SET " +
                     "imie=?, nazwisko=?, email_klient=?, nr_telefonu=?, password=?" +
                     "where id_klient=?"))
        {
            statement.setString(1,klientDTO.getImie());
            statement.setString(2,klientDTO.getNazwisko());
            statement.setString(3,klientDTO.getEmail());
            statement.setString(4,klientDTO.getTelefonuNumer());
            statement.setString(5,klientDTO.getPassword());
            statement.setInt(6,klientDTO.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public KlientDTO getClient(String email, String password) {
        KlientDTO result = null;
        try ( Connection connection = getDBConnection();
              PreparedStatement stmt = connection.prepareStatement("SELECT * from klienci " +
                      "WHERE EMAIL_KLIENT=? and PASSWORD=?")
        ) {
            stmt.setString(1,email);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                result = new KlientDTO(rs.getInt("ID_KLIENT"));
                result.setImie(rs.getString("IMIE"));
                result.setNazwisko(rs.getString("NAZWISKO"));
                //result.setEmail(rs.getString("EMAIL_KLIENT"));
                result.setEmail(email);
                result.setTelefonuNumer(rs.getString("NR_TELEFONU"));
                //result.setPassword(rs.getString("PASSWORD"));
                result.setPassword(password);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
