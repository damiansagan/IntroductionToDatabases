package com.wbd.seniutsagan;


class PozycjaMenuDTO {

    private Integer id;
    private String rodzajOferty;
    private String nazwa;
    private String cena;
    private String potrawy;

    PozycjaMenuDTO(Integer id) {
        this.id = id;
    }

    PozycjaMenuDTO(Integer id, String rodzajOferty, String nazwa, String cena, String potrawy) {
        this.id = id;
        this.rodzajOferty = rodzajOferty;
        this.nazwa = nazwa;
        this.cena = cena;
        this.potrawy = potrawy;
    }

    @Override
    public String toString() {
        return "PozycjaMenuDTO{" +
                "id=" + id +
                ", rodzajOferty='" + rodzajOferty + '\'' +
                ", nazwa='" + nazwa + '\'' +
                ", cena='" + cena + '\'' +
                ", potrawy='" + potrawy + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getRodzajOferty() {
        return rodzajOferty;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getCena() {
        return cena;
    }

    public String getPotrawy() { return potrawy; }

    public void setRodzajOferty(String rodzajOferty) {
        this.rodzajOferty = rodzajOferty;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public void setPotrawy(String potrawy) { this.potrawy = potrawy; }
}
