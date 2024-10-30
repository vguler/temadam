package com.example.myapplication.Clase;

import android.widget.Spinner;

import java.io.Serializable;
import java.util.Date;

public class Activitate implements Serializable {
    private String titlu;
    private String descriere;
    private String durata;
    private String prioritate;

    public Activitate(String titlu, String descriere, String durata, String prioritate) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.durata = durata;
        this.prioritate = prioritate;
    }

    @Override
    public String toString() {
        // Check if the description is provided (not null and not empty)
        String result = "Task name: " + titlu;

        if (descriere != null && !descriere.trim().isEmpty()) {
            result += "\nDescription: " + descriere;
        }

        result += "\n" + durata +
                "\nPrioritate: " + prioritate;

        return result;
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

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getPrioritate() {
        return prioritate;
    }

    public void setPrioritate(String prioritate) {
        this.prioritate = prioritate;
    }
}
