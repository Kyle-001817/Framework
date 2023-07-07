package etu1817.framework.test;

import etu1817.framework.annotation.*;

@Model
public class Emp {
    @ForFields
    String Test;

    public String getTest() {
        return Test;
    }

    public void setTest(String test) {
        this.Test = test;
    }

    String Aa;

    public String getAa() {
        return Aa;
    }

    public void setAa(String aa) {
        this.Aa = aa;
    }

    @Methods("kyle")
    public void add_some() {
        System.out.println("salut bogosy");
    }

    @Methods("view")
    public Models_view m_view_emp() {
        Models_view m_v = new Models_view("test.jsp");
        return m_v;
    }

    @Methods("get_id_Emp__id")
    public void find_by_id(@Parametre("id") String id, @Parametre("nom") String nom) {
        System.out.println("valeur de nom  = " + nom);
        System.out.println("valeur d'id  = " + Integer.valueOf(id));
    }
}