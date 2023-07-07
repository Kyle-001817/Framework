package etu1817.framework;

import java.sql.*;
import java.io.*;

import java.lang.reflect.Field;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;
import etu1817.framework.Mapping;
import etu1817.framework.show.*;
import etu1817.framework.annotation.*;
import java.lang.annotation.Annotation;
import etu1817.framework.files.*;

public class Utilitaire {

    public Utilitaire() {
    }

    public String servletPath(HttpServletRequest req, HttpServletResponse res) {
        return req.getServletPath();
    }

    public Object Save(Class classe, HttpServletRequest req) {
        Object object = null;
        try {
            object = classe.newInstance();
            Field[] fields = classe.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                String parameter = fields[j].getName();
                if (req.getParameter(parameter) != null) {
                    String valeur = (String) req.getParameter(parameter);
                    System.out.println(valeur);
                    System.out.println(parameter);
                    (object.getClass().getDeclaredMethod("set".concat(parameter), String.class)).invoke(object, valeur);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public String[] parametere(String answer) {
        String[] new_value = answer.split("__");
        return new_value;
    }

    public boolean chek_split(String[] new_parameter, HttpServletRequest req) {
        int compteur = 0;
        for (int l = 1; l < new_parameter.length; l++) {
            if (req.getParameter(new_parameter[l]) != null) {
                compteur++;
            }
        }
        if (compteur == new_parameter.length - 1) {
            return true;
        }
        return false;
    }

    public void get_annoter(HttpServletRequest req, String path) throws Exception {
        String path_annoter = (String) req.getServletPath();
        Class[] all = new AllClasses().allClass(path);
        int count = 0;
        for (int index = 0; index < all.length; index++) {
            Method[] mymethod = all[index].getDeclaredMethods();
            Object instance = all[index].newInstance();
            for (int i = 0; i < mymethod.length; i++) {
                Annotation[] al = mymethod[i].getAnnotations();
                for (int j = 0; j < al.length; j++) {
                    Method[] content = al[j].annotationType().getDeclaredMethods();
                    for (int k = 0; k < content.length; k++) {
                        if (path_annoter.split("/")[1].compareTo((String) content[k].invoke(al[j])) == 0) {
                            System.out.println(content[k].getName());
                            System.out.println(mymethod[i].getName());
                            Parameter[] para = mymethod[i].getParameters();
                            Object[] ob = new Object[para.length];
                            for (int l = 0; l < para.length; l++) {
                                String anno_para = para[l].getAnnotation(Parametre.class).value();
                                System.out.println(anno_para);
                                if (req.getParameter(anno_para) != null) {
                                    ob[count] = (String) req.getParameter(anno_para);
                                    count++;
                                }
                            }
                            if (count == para.length) {
                                System.out.println("okokokok");
                                mymethod[i].invoke(instance, ob);
                            }

                        }
                    }
                }
            }
        }
        // return null;
    }
}