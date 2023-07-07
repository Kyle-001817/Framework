package etu1817.framework;

import java.sql.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import etu1817.framework.files.FileUpload;

import java.util.*;
import java.io.InputStream;

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
            System.out.println(object.getClass().getName());
            Field[] fields = classe.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                String parameter = fields[j].getName();
                if (req.getParameter(parameter) != null) {
                    String valeur = (String) req.getParameter(parameter);
                    System.out.println(valeur);
                    System.out.println(parameter);
                    if (object.getClass().getDeclaredField(parameter).getType().getSimpleName()
                            .compareTo("FileUpload") == 0) {
                        System.out.println("classsess  = " + classe);
                        System.out.println("fileupload ok");
                        System.out.println(req.getParameter(parameter));
                        // (object.getClass().getDeclaredMethod("set".concat(parameter) ,
                        // String.class)).invoke(object, valeur ) ;
                    } else {
                        (object.getClass().getDeclaredMethod("set".concat(parameter), String.class)).invoke(object,
                                valeur);
                    }
                } else {
                    if (req.getPart(parameter) != null) {
                        Part filepart = req.getPart(parameter);
                        if (object.getClass().getDeclaredField(parameter).getType().getSimpleName()
                                .compareTo("FileUpload") == 0) {
                            InputStream inputStream = filepart.getInputStream();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                byteArrayOutputStream.write(buffer, 0, bytesRead);
                            }
                            byte[] fileBytes = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            inputStream.close();
                            String fileName = filepart.getSubmittedFileName();
                            String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\sprint9\\image"
                                    + fileName; // Spécifiez le chemin souhaité sur le
                            // serveur
                            filepart.write(path);
                            System.out.println("les byte ooh = " + fileBytes + "  path ooh = " + path);
                            FileUpload files = new FileUpload(fileName, path, fileBytes);
                            (object.getClass().getDeclaredMethod("set".concat(parameter), FileUpload.class))
                                    .invoke(object, files);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
