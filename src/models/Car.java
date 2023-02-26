/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import exam2.GenerateMatricule;

/**
 *
 * @author user
 */
public class Car {
    private int id;
    private String matricule;
    private String marque;
    private String model;
    private String transmission;
    private int annee;
    private int ownerId;

    public Car(String matricule, String marque, String model, String transmission, int annee, int ownerId) {
        this.matricule = matricule;
        this.marque = marque;
        this.model = model;
        this.transmission = transmission;
        this.annee = annee;
        this.ownerId = ownerId;
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

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", model=" + model + ", transmission=" + transmission + ", annee=" + annee + ", ownerId=" + ownerId + '}';
    }
    
    
}
