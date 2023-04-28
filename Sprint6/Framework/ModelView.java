package etu1817.framework;

import java.util.HashMap;

public class ModelView {
    private String view;
    private HashMap<String, Object> data;

    public ModelView() {
        data = new HashMap<>();
    }

    public void addItem(String key, Object value) {
        this.data.put(key, value);
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
