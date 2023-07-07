package etu1817.framework.function;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Vector;

public class Fonction {
    public Fonction() {
    }

    public int count_classes(String path) {
        int count = 0;
        try {
            File namePackage = new File(path);
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
        Class[] all = new Class[count_classes(path)];
        try {
            File namePackage = new File(path);
            File[] lesFiles = namePackage.listFiles();
            for (int i = 0; i < lesFiles.length; i++) {
                all[i] = Class.forName("etu1817.framework." + path + "."
                        + lesFiles[i].getName().substring(60, lesFiles[i].getName().length() - 6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return all;
    }

    public Vector<Method> getMymethod(Class classes) {
        Vector<Method> myMethods = new Vector<>();
        Method[] all = classes.getDeclaredMethods();
        for (int i = 0; i < all.length; i++) {
            myMethods.add(all[i]);
        }
        return myMethods;
    }
}
