package etu1817.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import etu1817.framework.Mapping;
import util.Util;
import etu1817.framework.MethodAnnotation;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;
    protected Util util = new Util();

    @Override
    public void init() throws ServletException {
        try {

            this.util = new Util();
            this.mappingUrls = new HashMap<>();

            String tomPath = "/WEB-INF/classes/";
            String path = getServletContext().getRealPath(tomPath);
            List<Class<?>> allClass = util.getAllClass(path, tomPath);

            Mapping mapping;
            Method[] allMethods;
            for (Class<?> c : allClass) {
                allMethods = c.getMethods();

                for (Method m : allMethods) {
                    if (m.isAnnotationPresent(MethodAnnotation.class)) {
                        mapping = new Mapping();
                        mapping.setClassName(c.getName());
                        mapping.setMethod(m.getName());
                        mappingUrls.put(m.getAnnotation(MethodAnnotation.class).url(), mapping);

                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        String parameter_url = util.processUrl(url, request.getContextPath());
        init();
        PrintWriter out = response.getWriter();
        out.println("URL : " + url);
        out.println("MAPPING : " + this.mappingUrls.toString());
        HashMap<String, Mapping> hashMap = this.mappingUrls;
        Mapping temp = hashMap.get(parameter_url);

        out.println("Url: " + parameter_url);
        out.println("\t Class : " + temp.getClassName());
        out.println("\t Method : " + temp.getMethod());
        try {
            Class<?> cls = Class.forName(temp.getClassName());
            Method m = cls.getMethod(temp.getMethod());
            Object o = cls.getDeclaredConstructor().newInstance();
            temp = (Mapping) m.invoke(o);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(temp.getClassName());
        dispatcher.forward(request, response);
    }
}
