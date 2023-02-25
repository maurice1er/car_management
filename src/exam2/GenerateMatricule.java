/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exam2;

/**
 *
 * @author user
 */
public class GenerateMatricule {
 
    private static int compteur = 1;
    private String matricule;
    

    public GenerateMatricule() {
        String prefixe = "AAAA-";
        String suffixe = "-AA";
        int numero = (compteur % 9999) + 1;
        String formatNumero = String.format("%04d", numero);
        char lettre1 = (char) ((compteur / (26 * 26)) % 26 + 'A');
        char lettre2 = (char) ((compteur / 26) % 26 + 'A');
        char lettre3 = (char) (compteur % 26 + 'A');
        this.matricule = prefixe + formatNumero + "-" + lettre1 + lettre2 + lettre3;
        compteur++;
    }

    public String getMatricule() {
        return this.matricule;
    }

    /*private void incrementer() {
        if (this.numero < 9999) {
            this.numero++;
        } else {
            this.numero = 1;
            if (this.lettre2 < 'Z') {
                this.lettre2++;
            } else {
                this.lettre2 = 'A';
                if (this.lettre1 < 'Z') {
                    this.lettre1++;
                } else {
                    this.lettre1 = 'A';
                }
            }
        }
    }*/
    
}
