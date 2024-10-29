package com.example.myapplication.Clase;

import android.widget.Spinner;

import java.io.Serializable;
import java.util.Date;

public class Activitate implements Serializable {
    private String titlu;
    private String descriere;
    private String durata;
    private String prioritate;

    public Activitate(String titlu, String descriere,String durata, String prioritate) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.durata = durata;
        this.prioritate = prioritate;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getPrioritate() {
        return prioritate;
    }

    public void setPrioritate(String prioritate) {
        this.prioritate = prioritate;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "Activitate{" +
                "titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                ", prioritate=" + prioritate +
                '}';
    }
}
