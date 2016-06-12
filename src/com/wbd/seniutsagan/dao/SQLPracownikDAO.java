package com.wbd.seniutsagan.dao;

import com.wbd.seniutsagan.dao.PracownicyDAO;
import com.wbd.seniutsagan.dto.PracownikDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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


    public ResultSet query(String query){
        ResultSet results;
        try{
            // the following assumes that you have a method to return
            // the current db Connection
            Statement statement = getDBConnection().createStatement();
            results = statement.executeQuery(query);
        } catch(Exception e) {
            // NEVER ignore Exceptions. At the very least I usually
            // wrap them in an unchecked Exception, viz:
            throw new RuntimeException(e);
        }
        return results;
    }


    public String[] headings(ResultSet resultSet){
        String[] col;
        try {
            ResultSetMetaData metadata = resultSet.getMetaData();
            col = new String[metadata.getColumnCount()];
            int numcols = metadata.getColumnCount();
            for(int count = 0; count < numcols; count++) {
                col[count] = metadata.getColumnLabel(count + 1);
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return col;
    }

// s

//    public static ArrayList<PracownikDTO> getData(ResultSet resultSet){
//        // List for simplicity.
//        ArrayList<PracownikDTO> data = new ArrayList<PracownikDTO>();
//        try {
//            int numcols = resultSet.getMetaData().getColumnCount();
//
//            while (resultSet.next()) {
//                PracownikDTO pracownik ;
//                for (int i = 0; i < rowData.length; ++i) {
//                    rowData[i] = resultSet.getObject(i+1);
//                }
//                data.add(rowData);
//            }
//        } catch(Exception e) {
//            throw new RuntimeException(e);
//        }
//        return data.toArray();
//    }


    @Override
    public List<PracownikDTO> readMainPracownik() throws SQLException {
        List<PracownikDTO> result = null;

        try (Connection connection = getDBConnection();
             Statement stmt = connection.createStatement()
        ) {


            result = new ArrayList<>();

        ResultSet rs1 = query("select ID_PRACOWNIK, STANOWISKO, IMIE, NAZWISKO from Pracownicy order by ID_PRACOWNIK");
        while (rs1.next()) {
            PracownikDTO pracownik = new PracownikDTO(rs1.getInt("ID_PRACOWNIK"));
            pracownik.setStanowisko(rs1.getString("STANOWISKO"));
            pracownik.setImie(rs1.getString("IMIE"));
            pracownik.setNazwisko(rs1.getString("NAZWISKO"));
            result.add(pracownik);

        }
        } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;
    }


    @Override
    public ResultSet resultSetMainPracownik() throws SQLException {
        List<PracownikDTO> result = null;

        ResultSet rs1 = null;
        try (Connection connection = getDBConnection();
             Statement stmt = connection.createStatement()
        ) {
            rs1 = query("select ID_PRACOWNIK, STANOWISKO, IMIE, NAZWISKO from Pracownicy order by ID_PRACOWNIK");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs1;
    }


    @Override
        public List<PracownikDTO> readAllPracownik ()throws SQLException {
            List<PracownikDTO> resultAll = null;
            try (Connection connection = getDBConnection();
                 Statement stmt = connection.createStatement()
            ) {

                resultAll = new ArrayList<>();
                ResultSet rs = stmt.executeQuery("select * from Pracownicy order by ID_PRACOWNIK");
                while (rs.next()) {
                    PracownikDTO pracownik = new PracownikDTO(rs.getInt("ID_PRACOWNIK"));
                    pracownik.setStanowisko(rs.getString("STANOWISKO"));
                    pracownik.setImie(rs.getString("IMIE"));
                    pracownik.setNazwisko(rs.getString("NAZWISKO"));
                    pracownik.setDataUrodzenia(rs.getDate("DATA_URODZENIA"));
                    pracownik.setPESEL(rs.getString("PESEL"));
                    pracownik.setNrKonta(rs.getString("NR_KONTA"));
                    pracownik.setKawiarniaId(rs.getInt("ID_KAWIARNIA"));
                    pracownik.setLokalId(rs.getInt("ID_LOKAL"));

                    resultAll.add(pracownik);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            return resultAll;


        }


    @Override
    public PracownikDTO readSelectedPracownik (int num) throws SQLException {
        PracownikDTO resultPracownik = null;
        try (Connection connection = getDBConnection();
             Statement stmt = connection.createStatement()
        ) {


            ResultSet rs = stmt.executeQuery("select * from Pracownicy where ID_PRACOWNIK ="+num );
            while (rs.next()) {
                PracownikDTO pracownik = new PracownikDTO(num);
                pracownik.setStanowisko(rs.getString("STANOWISKO"));
                pracownik.setImie(rs.getString("IMIE"));
                pracownik.setNazwisko(rs.getString("NAZWISKO"));
                pracownik.setDataUrodzenia(rs.getDate("DATA_URODZENIA"));
                pracownik.setPESEL(rs.getString("PESEL"));
                pracownik.setNrKonta(rs.getString("NR_KONTA"));
                pracownik.setKawiarniaId(rs.getInt("ID_KAWIARNIA"));
                pracownik.setLokalId(rs.getInt("ID_LOKAL"));

                resultPracownik=pracownik;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultPracownik;


    }


    @Override
    public void modifySelectedPracownik (int num,Map<String, String> modifyPracownikMap ) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder();

        String queryString ="update Pracownicy set " ;
        queryBuilder.append(queryString);
        Iterator it = modifyPracownikMap.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {

            System.out.println("i = " + i);
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getValue().equals("")){
                System.out.println(pair.getKey() + " = empty" );
            }
            else {
                i++;
                Object key = pair.getKey();
                Object value = pair.getValue();

                    if (key.toString().equals("stanowisko")) {
                        if ((i!=1)) {
                        queryBuilder.append(",");
                        }
                        queryBuilder.append("STANOWISKO='" + value +"'");

                    } else if (key.toString().equals("imie")) {
                        if ((i!=1)) {
                            queryBuilder.append(",");
                        }
                            queryBuilder.append("IMIE='" + value + "'");
                    } else if (key.toString().equals("nazwisko")) {
                        if ((i!=1)) {
                            queryBuilder.append(",");
                        }
                            queryBuilder.append("NAZWISKO='" + value +"'");

                    } else if (key.toString().equals("data")) {
                        if ((i!=1)) {
                            queryBuilder.append(",");
                        }
                        queryBuilder.append("DATA_URODZENIA=TO_DATE('" + value + "','YYYY-MM-DD') ");

                    } else if (key.toString().equals("pesel")) {
                        if ((i!=1)) {
                            queryBuilder.append(",");
                        }
                            queryBuilder.append("PESEL='" + value+"'");

                    } else if (key.toString().equals("nrKonta")) {
                        if ((i!=1)) {
                            queryBuilder.append(",");
                        }
                        queryBuilder.append("NR_KONTA='" + value+"'");
                    }

                    System.out.println(pair.getKey() + " = " + pair.getValue());
                  //  it.remove(); // avoids a ConcurrentModificationException
                }


        }

        queryBuilder.append(" where ID_PRACOWNIK="+num);
        String finalQuery = queryBuilder.toString();
        System.out.println(finalQuery);

        try (Connection connection = getDBConnection();
             Statement stmt = connection.createStatement()
        ) {

             stmt.executeQuery(finalQuery );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void addPracownik (Map<String, String> addPracownikMap ) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder();

        String queryString ="INSERT INTO PRACOWNICY (STANOWISKO, IMIE, NAZWISKO, DATA_URODZENIA, PESEL, NR_KONTA,ID_KAWIARNIA,ID_LOKAL) VALUES(" ;
        queryBuilder.append(queryString);
        System.out.println("Otrzymany zbior: " +addPracownikMap.toString());
        Iterator it = addPracownikMap.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {

            System.out.println("i = " + i);
            Map.Entry pair = (Map.Entry)it.next();
//            if(pair.getValue().equals("")){
//                System.out.println(pair.getKey() + " = empty" );
//                System.out.println("error!!!");
//            }
//            else {
                i++;
                Object key = pair.getKey();
                Object value = pair.getValue();

                if (key.toString().equals("stanowisko")) {
                    System.out.println("wchodze do stanowisko ");
                    if ((i!=1)) {
                        queryBuilder.append(",");
                    }
                    queryBuilder.append("'" + value +"'");

                } else if (key.toString().equals("imie")) {
                    System.out.println("wchodze do imie ");

                    if ((i!=1)) {
                        queryBuilder.append(",");
                    }
                    queryBuilder.append("'" + value + "'");
                } else if (key.toString().equals("nazwisko")) {
                    System.out.println("wchodze do nazwisko ");

                    if ((i!=1)) {
                        queryBuilder.append(",");
                    }
                    queryBuilder.append("'" + value +"'");

                } else if (key.toString().equals("data")) {
                    System.out.println("wchodze do data ");

                    if ((i!=1)) {
                        queryBuilder.append(",");
                    }
                    queryBuilder.append("TO_DATE('" + value + "','YYYY-MM-DD') ");

                } else if (key.toString().equals("pesel")) {
                    System.out.println("wchodze do pesel ");

                    if ((i!=1)) {
                        queryBuilder.append(",");
                    }
                    queryBuilder.append("'" + value+"'");

                } else if (key.toString().equals("nrKonta")) {
                    System.out.println("wchodze do nr konta ");

                    if ((i!=1)) {
                        queryBuilder.append(",");
                    }
                    queryBuilder.append("'" + value+"'");
                }
                else if (key.toString().equals("idKawiarnia")) {
                    System.out.println("wchodze do id_kawiarnia ");

                    if ((i != 1)) {
                        queryBuilder.append(",");
                    }
                    queryBuilder.append( value );
                }
                else if (key.toString().equals("idLokal")) {
                    System.out.println("wchodze do id_lokal ");

                    if ((i != 1)) {
                        queryBuilder.append(",");
                    }
                    queryBuilder.append( value );
                }

                System.out.println(pair.getKey() + " = " + pair.getValue());
                  it.remove(); // avoids a ConcurrentModificationException
           // }


        }

        queryBuilder.append(")");
        String finalQuery = queryBuilder.toString();
        System.out.println(finalQuery);

        try (Connection connection = getDBConnection();
             Statement stmt = connection.createStatement()
        ) {

            stmt.executeQuery(finalQuery );

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
