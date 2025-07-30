package com.booleanexpressioncalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static Boolean IsProperLabel(String label)
    {
        Pattern pattern = Pattern.compile(Resources.PROPER_LABEL_PATTERN);
        Matcher matcher = pattern.matcher(label);
        return matcher.find();
    }

    public static Boolean IsValidCharForLabel(char c){
        return ((c >= 'A' && c <= 'Z') ||
        (c >= 'a' && c <= 'z') ||
        (c >= '0' && c <= '9') ||
        (c == '_' ) ||
        (c == '-' )
        );
    }

    public static Boolean IsValidFirstCharForLabel(char c){
        return ((c >= 'A' && c <= 'Z') ||
        (c >= 'a' && c <= 'z') ||
        (c >= '0' && c <= '9') ||
        (c == '_' ) 
        );
    }

}
