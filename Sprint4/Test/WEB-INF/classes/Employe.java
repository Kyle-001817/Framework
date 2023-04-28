package model;

import etu1817.framework.*;

public class Employe {
    String nom;
    String prenom;

    @MethodAnnotation(url = "find-all")
    public String getNom() {
        return this.nom;
    }

    @MethodAnnotation(url = "find-one")
    public String getPrenom() {
        return this.prenom;
    }
}