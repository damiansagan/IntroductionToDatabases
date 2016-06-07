package com.wbd.seniutsagan.dto;


public class PozycjaMenuDTO {

    private final Integer id;
    private String rodzajOferty;
    private String nazwa;
    private Double cena;
    private String potrawy;

    public PozycjaMenuDTO(Integer id) {
        this.id = id;
    }

    public PozycjaMenuDTO(Integer id, String rodzajOferty, String nazwa, Double cena, String potrawy) {
        this.id = id;
        this.rodzajOferty = rodzajOferty;
        this.nazwa = nazwa;
        this.cena = cena;
        this.potrawy = potrawy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PozycjaMenuDTO)) return false;
        PozycjaMenuDTO that = (PozycjaMenuDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
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

    public Double getCena() {
        return cena;
    }

    public String getPotrawy() { return potrawy; }

    public void setRodzajOferty(String rodzajOferty) {
        this.rodzajOferty = rodzajOferty;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setCena(Double cena) { this.cena = cena; }

    public void setPotrawy(String potrawy) { this.potrawy = potrawy; }
}
