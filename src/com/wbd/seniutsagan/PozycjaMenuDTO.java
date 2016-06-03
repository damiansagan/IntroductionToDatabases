package com.wbd.seniutsagan;


import java.util.List;

class PozycjaMenuDTO {

    private Integer id;
    private String rodzajOferty;
    private String nazwa;
    private String cena;
    private Integer kawiarniaId;

    private List<PotrawaDTO> potrawy;

    PozycjaMenuDTO(Integer id) {
        this.id = id;
    }

    public PozycjaMenuDTO(Integer id, String rodzajOferty, String nazwa, String cena, Integer kawiarniaId) {
        this.id = id;
        this.rodzajOferty = rodzajOferty;
        this.nazwa = nazwa;
        this.cena = cena;
        this.kawiarniaId = kawiarniaId;
    }

    @Override
    public String toString() {
        return "PozycjaMenuDTO{" +
                "id=" + id +
                ", rodzajOferty='" + rodzajOferty + '\'' +
                ", nazwa='" + nazwa + '\'' +
                ", cena='" + cena + '\'' +
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

    public Integer getKawiarniaId() {
        return kawiarniaId;
    }

    public List<PotrawaDTO> getPotrawy() {
        return potrawy;
    }

    public void setRodzajOferty(String rodzajOferty) {
        this.rodzajOferty = rodzajOferty;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public void setKawiarniaId(Integer kawiarniaId) {
        this.kawiarniaId = kawiarniaId;
    }

    public void setPotrawy(List<PotrawaDTO> potrawy) {
        this.potrawy = potrawy;
    }
}
