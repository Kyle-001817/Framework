package etu1817.framework.servlet;

import etu1817.framework.Mapping;
import etu1817.framework.MethodAnnotation;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilitaire.Utilitaire;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;
    protected Utilitaire util;

    @Override
    public void init() throws ServletException {
        this.util = new Utilitaire();
        this.MappingUrls = new HashMap<>();
        List<Class<?>> allClass = getClassesInPackage("etu1817.framework");
        Mapping mapping;
        Method[] allMethods;
        for(Class<?> c : allClass) {
            allMethods = c.getMethods();

            for(Method m : allMethods) {
                if(m.isAnnotationPresent(MethodAnnotation.class)) {
                    mapping = new Mapping();
                    mapping.setClassName(c.getSimpleName());
                    mapping.setMethod(m.getName());

                    MappingUrls.put(m.getAnnotation(MethodAnnotation.class).url(), mapping);

                }
            }
        }
    }

    protected static List<Class<?>> getClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            for (java.net.URL resource : java.util.Collections.list(classLoader.getResources(path))) {
                for (String file : new java.io.File(resource.toURI()).list()) {
                    if (file.endsWith(".class")) {
                        String className = packageName + '.' + file.substring(0, file.length() - 6);
                        Class<?> clazz = Class.forName(className);
                        classes.add(clazz);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURL().toString();

        PrintWriter out = response.getWriter();
        out.print("url: " + util.processUrl(url, request.getContextPath()));
        out.print("<h1>"+this.MappingUrls.get("methode").getClassName()+"</h1>");
    }
}
