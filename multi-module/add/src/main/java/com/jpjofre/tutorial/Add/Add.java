package com.jpjofre.tutorial.Add;

import com.jpjofre.tutorial.Arithmetic.*;

public class Add {
    public static void main (String[] args) {
        if (args.length == 0){
            System.out.println("Insuficient arguments !! Need one or more integers.");
            System.exit(-1);
        }

        int result = 0;
        for (String s: args) {
            int a = Integer.parseInt(s);
            result = Arithmetic.Add(result, a);
        }

        System.out.format("%s = %d", String.join(" + ", args), result);
        System.exit(0);
    }
}