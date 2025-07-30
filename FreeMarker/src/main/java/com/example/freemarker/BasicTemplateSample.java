package com.example.freemarker;

import java.lang.String;
import java.io.StringWriter;
import java.lang.Number;

import java.util.*;

import freemarker.template.*;

public final class BasicTemplateSample {

  public static String runTemplate(Configuration cfg){
    Map<String, Object> root = new HashMap<>();
    List<Object> animals = new ArrayList<>();
    Map<String, Object> mouse = new HashMap<>();
    Map<String, Object> elephant = new HashMap<>();
    Map<String, Object> python = new HashMap<>();

    mouse.put("kind","mouse");
    mouse.put("size", "small");
    mouse.put("price", 50);

    elephant.put("kind","elephant");
    elephant.put("size", "large");
    elephant.put("price", 50000);

    python.put("kind","python");
    python.put("size", "medium");
    python.put("price", 500);

    animals.add(mouse);
    animals.add(elephant);
    animals.add(python);

    // Put string "user" into the root
    root.put("user", "Big Joe");
    root.put("message","our beloved leader!!");

    root.put("animals", animals);


    StringWriter writer = new StringWriter();
    try {
      Template template = cfg.getTemplate("template.ftl");
      template.process(root, writer);
      return writer.toString();
    } catch (Exception x1) {
      System.out.println(x1.getMessage());
    }

    return null;
} 

}