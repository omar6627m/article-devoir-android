package com.example.myapplication;

public class Product {
    private int id;
    private String libelle;
    private int pu;

    public Product(int id, String libelle, int pu) {
        this.id = id;
        this.libelle = libelle;
        this.pu = pu;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getPu() {
        return pu;
    }

    public void setPu(int pu) {
        this.pu = pu;
    }

    @Override
    public String toString() {
        return id + ": " + libelle + " - " + pu;
    }
}
