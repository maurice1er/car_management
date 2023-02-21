/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author user
 */
public class Car {
    private int id;
    private String matricule;
    private String marque;
    private String model;
    private int transmission;
    private  int annee;

    public Car(String matricule, String marque, String model, int transmission, int annee) {
        this.matricule = matricule;
        this.marque = marque;
        this.model = model;
        this.transmission = transmission;
        this.annee = annee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getTransmission() {
        return transmission;
    }

    public void setTransmission(int transmission) {
        this.transmission = transmission;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", model=" + model + ", transmission=" + transmission + ", annee=" + annee + '}';
    }
    
    
}
