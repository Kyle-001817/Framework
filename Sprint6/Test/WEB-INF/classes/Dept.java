package model;

import etu1817.framework.*;

public class Dept {
    String nom;
    String numero;

    @MethodAnnotation(url = "dept-all")
    public String getNom() {
        return this.nom;
    }

    @MethodAnnotation(url = "dept-one")
    public String getNumero() {
        return this.numero;
    }

    @MethodAnnotation(url = "test")
    public ModelView Test() {
        ModelView m = new ModelView();
        m.setView("Test.jsp");
        m.addItem("data", "Hello, world!");
        return m;
    }
}