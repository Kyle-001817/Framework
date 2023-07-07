package etu1817.framework.test;

import java.sql.*;
import java.io.*;
import java.lang.reflect.Method;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;

public class Models_view {
    String view;
    HashMap<String, Object> donnee;

    public HashMap<String, Object> getDonnee() {
        return donnee;
    }

    public void setDonnee(HashMap<String, Object> donnee) {
        this.donnee = donnee;
    }

    public Models_view(String view) {
        this.view = view;
    }

    public Models_view() {
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void add_item(String key, Object values, HashMap<String, Object> objet) {
        objet.put(key, values);
    }
}