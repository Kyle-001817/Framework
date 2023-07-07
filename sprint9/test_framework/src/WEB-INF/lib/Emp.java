package etu1817.framework.test;

import etu1817.framework.files.*;

import java.io.File;

import etu1817.framework.annotation.*;

@Model
public class Emp {
    @ForFields
    String Test;

    public String getTest() {
        return Test;
    }

    public void setTest(String test) {
        this.Test = test;
    }

    String Aa;

    public String getAa() {
        return Aa;
    }

    public void setAa(String aa) {
        this.Aa = aa;
    }

    @Methods("kyle")
    public void add_some() {
        System.out.println("salut bogosy");
    }

    @Methods("view")
    public Models_view m_view_emp() {
        Models_view m_v = new Models_view("test.jsp");
        return m_v;
    }

    FileUpload File;

    public FileUpload getFile() {
        return File;
    }

    public void setFile(FileUpload file) {
        this.File = file;
    }

    public String getNamePath() {
        return File.getPath();
    }
}