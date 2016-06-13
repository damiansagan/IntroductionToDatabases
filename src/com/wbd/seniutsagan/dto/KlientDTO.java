package com.wbd.seniutsagan.dto;

public class KlientDTO {

    private int id;
    private String imie;
    private String nazwisko;
    private String email;
    private String telefonuNumer;
    private String password;

    public KlientDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonuNumer() {
        return telefonuNumer;
    }

    public void setTelefonuNumer(String telefonuNumer) {
        this.telefonuNumer = telefonuNumer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
