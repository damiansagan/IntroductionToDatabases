package com.wbd.seniutsagan;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by monika03 on 04.06.16.
 */
public class PracownikDTO {

    private Integer id;
    private String stanowisko;
    private String imie;
    private String nazwisko;
    private Date dataUrodzenia;
    private String PESEL;
    private String nrKonta;
    private Integer kawiarniaId;
    private Integer lokalId;

    PracownikDTO(Integer id) {
        this.id = id;
    }

//    public dateFormat(Date date){
//
//        ft.format(date);
//        System.out.println("Current Date: " + ft.format(date));
//    }



    public PracownikDTO(Integer id, String stanowisko, String imie, String nazwisko,
                        Date dataUrodzenia, String PESEL, String nrKonta, Integer kawiarniaId, Integer lokalId)
    {
        this.id = id;
        this.stanowisko = stanowisko;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.PESEL = PESEL;
        this.nrKonta = nrKonta;
        this.kawiarniaId = kawiarniaId;
        this.lokalId = lokalId;
    }


    @Override
    public String toString() {
        return "PracownikDTO{" +
                "id=" + id + '\'' +
                ", stanowisko=" + stanowisko + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataUrodzenia=" + dataUrodzenia+ '\'' +
                ", pesel=" + PESEL+ '\'' +
                ", nrKonta=" + nrKonta + '\'' +
                ", kawiarniaId=" + kawiarniaId+ '\'' +
                ", lokalId=" + lokalId + '\'' +
                '}';
    }


    public String toString1() {
        return "PracownikDTO{" +
                "id=" + id + '\'' +
                ", stanowisko=" + stanowisko + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public Integer getKawiarniaId() {
        return kawiarniaId;
    }

    public Integer getLokalId() {
        return lokalId;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getNrKonta() {
        return nrKonta;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {

        this.dataUrodzenia = dataUrodzenia;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setKawiarniaId(Integer kawiarniaId) {
        this.kawiarniaId = kawiarniaId;
    }

    public void setLokalId(Integer lokalId) {
        this.lokalId = lokalId;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setNrKonta(String nrKonta) {
        this.nrKonta = nrKonta;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }


}
