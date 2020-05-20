package com.example.Lab2;

import java.io.Serializable;

public class ContentClass implements Serializable {

    String flags = null;
    String graphic = null;
    String graphic_alt = null;
    String helptext = null;
    String name = null;
    String req1 = null;
    String req2 = null;

    String description;
    String format_version;
    String options;

    public ContentClass(String r2) {
        req2 = r2;
    }

    public ContentClass(String d, String f_v, String o) {
        description = d;
        format_version = f_v;
        options = o;
    }

    public ContentClass(String f, String g, String g_a, String h, String n, String r1, String r2) {
        flags = f;
        graphic = g;
        graphic_alt = g_a;
        helptext = h;
        name = n;
        req1 = r1;
        req2 = r2;
    }

    public String getFlags() {
        if (flags == null || flags == "") return "flags: \n";
        return "flags: "+flags+"\n";
    }

    public String getGraphic() {
        if (graphic == null || graphic == "") return "graphic: \n";
        return "graphic: "+graphic+"\n";
    }

    public String getGraphic_alt() {
        if (graphic_alt == null || graphic_alt == "") return "graphic_alt: \n";
        return "graphic_alt: "+graphic_alt+"\n";
    }

    public String getHelptext() {
        if (helptext == null || helptext == "") return "";

        return helptext;
    }

    public String getName() {
        if (name == null || name == "") return "";

        return name;


    }

    public String getReq1() {
        if (req1 == null || req1 == "") return "req1: \n";
        return "req1: "+req1+"\n";
    }

    public String getReq2() {
        if (req2 == null || req2 == "") return "req2: \n";
        return "req2: "+req2+"\n";
    }

    public String getDescription() {
        return "description: "+description+"\n";
    }

    public String getFormat_version() {
        return "format_version: "+format_version+"\n";
    }

    public String getOptions() {
        return "options: "+options+"\n";
    }
}
