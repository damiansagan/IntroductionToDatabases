package com.wbd.seniutsagan.dto;

import java.util.HashMap;
import java.util.Map;

public class ZamowienieDTO {

    private Integer id;
    private String status = "Przyjete";
    private Map<PozycjaMenuDTO,Integer> pozycjeMenu = new HashMap<>();
    private Integer idKlienta;
    private Integer idRezerwacji;
    private Integer idPracownika;

    public ZamowienieDTO(KlientDTO klientDTO) {
        this.idKlienta = klientDTO.getId();
    }

    public void addPozycjaMenu(PozycjaMenuDTO pozycjaMenu) {
        Integer current = pozycjeMenu.putIfAbsent(pozycjaMenu,1);
        if(current!=null)
            pozycjeMenu.replace(pozycjaMenu,++current);
    }

    public void removePozycjaMenu(PozycjaMenuDTO pozycjaMenu) {
        Integer current = pozycjeMenu.get(pozycjaMenu);
        if(current-1>0)
            pozycjeMenu.replace(pozycjaMenu,--current);
        else
            pozycjeMenu.remove(pozycjaMenu);
    }

    @Override
    public String toString() {
        return "ZamowienieDTO{" +
                "pozycjeMenu=" + pozycjeMenu +
                '}';
    }

    public void setIdPracownika(Integer idPracownika) {
        this.idPracownika = idPracownika;
    }

    public void setIdRezerwacji(Integer idRezerwacji) {
        this.idRezerwacji = idRezerwacji;
    }

    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Map<PozycjaMenuDTO, Integer> getPozycjeMenu() {
        return pozycjeMenu;
    }

    public Integer getIdKlienta() {
        return idKlienta;
    }

    public Integer getIdRezerwacji() {
        return idRezerwacji;
    }

    public Integer getIdPracownika() {
        return idPracownika;
    }
}
