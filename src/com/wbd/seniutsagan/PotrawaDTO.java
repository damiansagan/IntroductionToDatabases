package com.wbd.seniutsagan;

class PotrawaDTO {

    private Integer id;
    private String nazwa;
    private String rodzaj;
    private Integer pozycjaMenuId;

    @Override
    public String toString() {
        return "PotrawaDTO{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", rodzaj='" + rodzaj + '\'' +
                ", pozycjaMenuId=" + pozycjaMenuId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public Integer getPozycjaMenuId() {
        return pozycjaMenuId;
    }

    public void setPozycjaMenuId(Integer pozycjaMenuId) {
        this.pozycjaMenuId = pozycjaMenuId;
    }
}
