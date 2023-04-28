package util;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class Util {

    public String processUrl(String url_input, String ctx) {
        ctx += "/";
        int ctx_ind = url_input.indexOf(ctx);
        String url = url_input.substring(ctx_ind + ctx.length());

        return url;
    }

    public List<Class<?>> getAllClass(String path, String tomPath) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        File directory = new File(path);
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                List<Class<?>> innerClasses = this.getAllClass(file.getAbsolutePath(), tomPath);
                classes.addAll(innerClasses);
            } else if (file.getName().endsWith(".class")) {
                String absolute_path_class = file.getPath().replace("\\", "/");
                int tom_int_path = absolute_path_class.indexOf(tomPath);

                String className = absolute_path_class.substring(tom_int_path + tomPath.length())
                        .replace(".class", "")
                        .replace("/", ".");
                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            }
        }
        return classes;
    }
}
