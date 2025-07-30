package com.booleanexpressioncalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Context {
    private Map<String, String> ctx = new HashMap<>();
    private Set<String> extendedCtx = new HashSet<>();

    public Context(List<String> labels, 
                   Universe u,
                   Boolean continueOnLabelError){
        if (labels == null) {
            throw new IllegalArgumentException("'labels' argument cannot be null");
        }

        for (String label : labels) {
            if (Utils.IsProperLabel(label)) {
                String ulabel = label.toUpperCase();
                String labelPreferredName = u.GetLabel(ulabel);

                ctx.put(label, labelPreferredName);
                extendedCtx.add(ulabel);   
                extendedCtx.add(labelPreferredName);
            } else {
                if (!continueOnLabelError) {
                    throw new IllegalArgumentException("Invalid 'label' at " + label);
                }
            }
        }
    }

    public Context(List<String> labels, Universe u){
        this(labels, u, false);
    }

    public Context(){}

    /*
    public Boolean Add(String label){
        if (!Utils.IsProperLabel(label)) {
            return false;
        }

        ctx.add(label.toUpperCase());

        return true;
    }
    */

    public Boolean Contains(String label){
        String ulabel = label.toUpperCase();

        Boolean result = extendedCtx.contains(ulabel);
        return result;
    }

    public String ToUniverseValues(Universe u){
        List<String> items = new ArrayList<>();

        for (String value : ctx.values()) {
            items.add(value);
        }

        return "[" + String.join(", ", items) + "]";
    }

    @Override
    public String toString(){
        List<String> items = new ArrayList<>();

        for (String value : ctx.keySet()) {
            items.add(value);
        }

        return "[" + String.join(", ", items) + "]";
    }
}
