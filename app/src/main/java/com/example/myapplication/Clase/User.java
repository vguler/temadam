package com.example.myapplication.Clase;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String nume;
    private String prenume;
    private Date dataNastere;

    public User(String nume, String prenume, Date dataNastere) {
        this.nume = nume;
        this.prenume = prenume;
        this.dataNastere = dataNastere;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Date getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(Date dataNastere) {
        this.dataNastere = dataNastere;
    }

    @Override
    public String toString() {
        return "User{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", dataNastere=" + dataNastere +
                '}';
    }
}
