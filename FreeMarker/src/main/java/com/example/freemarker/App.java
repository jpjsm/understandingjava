package com.example.freemarker;

import java.io.File;

import freemarker.template.*;

public class App {
    public static void main(String[] args){
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        try {
            cfg.setDirectoryForTemplateLoading(new File("./src/main/resources/templates"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // From here we will set the settings recommended for new projects. These
        // aren't the defaults for backward compatibilty.

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");

        String results = BasicTemplateSample.runTemplate(cfg);
        if (results != null) {
            System.out.println(results);
        }
    }
}
