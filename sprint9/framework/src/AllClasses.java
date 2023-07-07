package etu1817.framework.files;

import java.io.File;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;
import etu1817.framework.Mapping;
import etu1817.framework.show.*;

public class AllClasses {
    public AllClasses() {
    }

    public int Test_fichier(String pathss) {
        int count = 0;
        try {

            File namePackage = new File(pathss);
            // System.out.println("pathsss = " + namePackage);
            File[] lesFiles = namePackage.listFiles();
            for (int i = 0; i < lesFiles.length; i++) {
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public Class[] allClass(String path) {
        String paths = path.replace("\\", "/");
        System.out.println("the paths = " + paths);
        Class[] all = new Class[Test_fichier(paths)];
        try {
            File namePackage = new File(paths);
            File[] lesFiles = namePackage.listFiles();
            System.out.println("compte fichier = " + lesFiles.length);
            for (int i = 0; i < lesFiles.length; i++) {
                System.out.println(paths.substring(67, paths.length()) + "."
                        + lesFiles[i].getName().substring(0, lesFiles[i].getName().length() - 6));
                all[i] = Class.forName("etu1817.framework." + paths.substring(67, paths.length()) + "."
                        + lesFiles[i].getName().substring(0, lesFiles[i].getName().length() - 6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    public HashMap<String, Mapping> GetHashMap(String name_path, String Mypath) {
        Vector myVector = new Show().getAbout_method(name_path.split("/")[1], Mypath);
        Class classname = (Class) myVector.elementAt(0);
        Method method = (Method) myVector.elementAt(1);
        Mapping map = new Mapping(classname.getSimpleName(), method.getName());
        HashMap<String, Mapping> hash = new HashMap<>();
        hash.put(name_path.split("/")[1], map);
        // System.out.println("contenu =" + hash.get("value"));
        // Mapping test = hash.get("value") ;
        // System.out.println(test.getMethod());
        return hash;
    }
}
