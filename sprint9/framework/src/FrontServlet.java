package etu1817.framework.servlet;

import java.sql.*;
import java.io.*;
import java.lang.reflect.Method;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import etu1817.framework.*;
import etu1817.framework.files.*;
import etu1817.framework.function.*;
import etu1817.framework.show.*;
import etu1817.framework.test.Models_view;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> Mapping_url;

    protected void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException,Exception { 
        // res.setContentType("text/html;charset=UTF-8") 
        // Part aa = req.get("File");
        // System.out.println("ito oooo = " + req.getParameter("File"));
        PrintWriter out = res.getWriter();
        String path = req.getServletPath();
        ServletContext context = getServletContext() ; 
        String c = context.getRealPath("/WEB-INF/classes/etu1817/framework/test");
        out.println(c);
        try {
            Class [] allClass = new AllClasses().allClass(c) ;
            for (int i = 0; i < allClass.length; i++) {
                Object object = allClass[i].newInstance() ; 
                out.println(allClass[i].getSimpleName());
                Object mymethod =  new Utilitaire().Save(allClass[i] , req);
                Method [] all = mymethod.getClass().getDeclaredMethods() ;
                for (int j = 0; j < all.length; j++) {
                    if(all[j].getName().substring(0, 3).compareTo("get") == 0 ){
                        out.println(all[j].invoke(mymethod));
                    }
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            processRequest(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}