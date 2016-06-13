package com.wbd.seniutsagan.dao;


import com.wbd.seniutsagan.dto.KlientDTO;
import com.wbd.seniutsagan.dto.PozycjaMenuDTO;
import com.wbd.seniutsagan.dto.ZamowienieDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLZamowienieDAO implements ZamowienieDAO{
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private static final String DB_USER = "DSAGAN";
    private static final String DB_PASSWORD = "dsagan";

    private static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
    }


    @Override
    public boolean pushOrder(ZamowienieDTO zamowienieDTO) {
        try (Connection con = getDBConnection())
        {
            con.setAutoCommit(false);
            try (Statement stm11 = con.createStatement();
                 PreparedStatement stm2 = con.prepareStatement("INSERT INTO Zamowienia (ID_ZAMOWIENIE, ID_KLIENT, ID_REZERWACJI) VALUES (?, ?, ?)");
                 PreparedStatement stm3 = con.prepareStatement("INSERT INTO POZYCJE_ZAMOWIEN (ID_ZAMOWIENIE, ID_POZYCJA_MENU, ILOSC) VALUES (?, ?, ?)")
                )
            {
                ResultSet rs1 = stm11.executeQuery("select Zamowienia_seq.nextval from dual");
                if(rs1.next())
                    zamowienieDTO.setId(rs1.getInt(1));
                else
                    throw new SQLException();
                stm2.setInt(1,zamowienieDTO.getId());
                stm2.setInt(2,zamowienieDTO.getIdKlienta());
                stm2.setObject(3,zamowienieDTO.getIdRezerwacji());
                stm2.executeUpdate();
                stm3.setInt(1,zamowienieDTO.getId());
                for (Map.Entry<PozycjaMenuDTO,Integer> entry : zamowienieDTO.getPozycjeMenu().entrySet()) {
                    stm3.setInt(2,entry.getKey().getId());
                    stm3.setInt(3,entry.getValue());
                    stm3.executeUpdate();
                }
                con.commit();
            }
            catch(SQLException ex)
            {
                con.rollback();
                throw ex;
            }finally {
                try{
                    con.setAutoCommit(true);
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public List<ZamowienieDTO> getAll(KlientDTO klientDTO){
        List<ZamowienieDTO> result;
        try ( Connection connection = getDBConnection();
              Statement stmt = connection.createStatement()
        ) {
            ResultSet rs = stmt.executeQuery("SELECT id_zamowienie, status, pozycje_zamowienia FROM zamowienia " +
                    " JOIN (SELECT" +
                    "        id_zamowienie," +
                    "        LISTAGG(pozycja, ', ') WITHIN GROUP (ORDER BY pozycja) AS pozycje_zamowienia" +
                    "      FROM" +
                    "        (SELECT id_zamowienie, ''||ilosc||'x '||nazwa||'' AS pozycja" +
                    "         FROM pozycje_zamowien JOIN pozycje_menu USING (id_pozycja_menu))" +
                    "      GROUP BY id_zamowienie) USING (id_zamowienie)" +
                    " WHERE id_klient=" + klientDTO.getId() +
                    " ORDER BY id_zamowienie DESC ");
            result = new ArrayList<>();
            while (rs.next()) {
                ZamowienieDTO current = new ZamowienieDTO(klientDTO);
                current.setId(rs.getInt("ID_ZAMOWIENIE"));
                current.setStatus(rs.getString("STATUS"));
                current.setPozycjeMenuAggregated(rs.getString("POZYCJE_ZAMOWIENIA"));
                result.add(current);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
