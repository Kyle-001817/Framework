package etu1817.framework.test;

public class Dept {
    String Nom;
    String Prenom;
    String Dates;
    int Age;

    public int getAge() {
        return Age;
    }

    public void setAge(String age) {
        this.Age = Integer.valueOf(age);
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getDates() {
        return Dates;
    }

    public void setDates(String dates) {
        Dates = dates;
    }

    public Dept() {
    }
}