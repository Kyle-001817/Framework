package etu1817.framework.test;

import etu1817.framework.annotation.*;

@Model("Testas")
public class Test {

    @ForFields(valeur = "champ")
    String monChamp;
    String Nom;
    int Age;

    public int getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = Integer.valueOf(age);
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public Test() {
    }

    public String getMonChamp() {
        return monChamp;
    }

    public void setMonChamp(String monChamp) {
        this.monChamp = monChamp;
    }

}
