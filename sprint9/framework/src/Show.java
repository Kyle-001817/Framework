package etu1817.framework.show;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;
import etu1817.framework.annotation.*;
// import etu1817.framework.test.*;
import etu1817.framework.files.*;

public class Show {
    public Show() {
    }

    public Vector<Class> getClassAnnoter(String path) throws Exception {
        Vector<Class> obj = new Vector<Class>();
        Class[] all = new AllClasses().allClass(path);
        for (int i = 0; i < all.length; i++) {
            if (all[i].getAnnotation(Model.class) != null) {
                obj.add(all[i]);
            }
        }
        return obj;
    }

    public Boolean test_methods(String methods, String mypath) {
        Class[] all = new AllClasses().allClass(mypath);
        for (int index = 0; index < all.length; index++) {
            Method[] mymethod = all[index].getDeclaredMethods();
            for (int i = 0; i < mymethod.length; i++) {
                if (mymethod[i].getName().compareTo(methods) == 0) {
                    if (mymethod[i].getAnnotation(Methods.class) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean is_exist(String methods, String mypath) throws Exception {
        System.out.println(mypath);
        Class[] all = new AllClasses().allClass(mypath);
        for (int index = 0; index < all.length; index++) {
            Method[] mymethod = all[index].getDeclaredMethods();
            for (int i = 0; i < mymethod.length; i++) {
                Annotation[] al = mymethod[i].getAnnotations();
                for (int j = 0; j < al.length; j++) {
                    if (mymethod[i].getAnnotation(Methods.class).value().compareTo(methods) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Vector getAbout_method(String myMethod, String path) {
        Vector all_vec = new Vector();
        Class[] all = new AllClasses().allClass(path);
        for (int index = 0; index < all.length; index++) {
            Method[] mymethod = all[index].getDeclaredMethods();
            for (int i = 0; i < mymethod.length; i++) {
                Annotation[] al = mymethod[i].getAnnotations();
                for (int j = 0; j < al.length; j++) {
                    if (mymethod[i].getAnnotation(Methods.class).value().compareTo(myMethod) == 0) {
                        all_vec.add(all[index]);
                        all_vec.add(mymethod[i]);
                    }
                }

            }
        }
        return all_vec;
    }

    public String nameAnnoterOfClass(Class name) throws Exception {
        Annotation[] annotation = name.getAnnotations();
        String names = new String();
        for (int j = 0; j < annotation.length; j++) {
            Method[] mdp = annotation[j].annotationType().getDeclaredMethods();
            for (int k = 0; k < mdp.length; k++) {
                names += "" + mdp[k].invoke(annotation[j]);
                System.out.println("annotation de " + name + " = " + mdp[k].invoke(annotation[j]));
            }
        }
        return names;
    }

    public Vector<String> getAttribuAnnotations(Class name) {
        System.out.println("les attribut annoter de " + name + "sont => ");
        Vector<String> anno = new Vector<String>();
        Field[] t = name.getDeclaredFields();
        for (int j = 0; j < t.length; j++) {
            Annotation[] as = t[j].getAnnotations();
            for (int k = 0; k < as.length; k++) {
                if (as[k].annotationType().getSimpleName().compareTo("ForFields") == 0) {
                    System.out.println("les valeur du champ " + t[j].getName() + " = "
                            + t[j].getAnnotation(ForFields.class).valeur());
                    anno.add(t[j].getAnnotation(ForFields.class).valeur());
                }
            }
        }
        return anno;
    }
}
