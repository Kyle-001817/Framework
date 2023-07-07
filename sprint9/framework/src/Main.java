package etu1817.framework.main;

import etu1817.framework.function.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Vector;

import etu1817.framework.Mapping;
import etu1817.framework.show.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Class [] all = new Fonction().allClass("test");
        // for (int i = 0; i < all.length; i++) {
        // System.out.println(all[i].getName() + " my method =>" );
        // Vector<Method>getmyMethods = new Fonction().getMymethod(all[i]);
        // for (int j = 0; j < getmyMethods.size(); j++) {
        // System.out.println(getmyMethods.elementAt(j).getName());
        // }
        // }
        String name_path = "/kyle";
        if (new Show().is_exist(name_path.split("/")[1],
                "D:/Tomcat/webapps/Sprint3/WEB-INF/classes/etu1817/framework/test") == true) {
            Vector myVector = new Show().getAbout_method(name_path.split("/")[1],
                    "D:/Tomcat/webapps/Sprint3/WEB-INF/classes/etu1817/framework/test");
            Class classname = (Class) myVector.elementAt(0);
            Method method = (Method) myVector.elementAt(1);
            Mapping map = new Mapping(classname.getSimpleName(), method.getName());
            HashMap<String, Mapping> hash = new HashMap<>();
            hash.put(name_path.split("/")[1], map);
            System.out.println("contenu =" + hash.get(name_path.split("/")[1]));
            Mapping test = hash.get(name_path.split("/")[1]);
            System.out.println(test.getMethod());
        }
        // System.out.println(new Show().is_exist(name_path.split("/")[1]
        // ,"D:/Tomcat/webapps/Sprint3/WEB-INF/classes/test" ));
    }
}
