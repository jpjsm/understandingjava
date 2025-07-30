package com.booleanexpressioncalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Universe {
    private HashMap<String,String> u6e = new HashMap<>();

    public Boolean TryAdd(List<StringPair> pairs, Boolean abortOnError) 
        throws Exception
    {
        List<StringPair> candidates = new ArrayList<>();


        for (StringPair stringPair : pairs) {
            if (
                !(Utils.IsProperLabel(stringPair.Left()) && Utils.IsProperLabel(stringPair.Right())) && 
                abortOnError) 
            {
                throw new Exception(Resources.INVALID_UNIVERSE_PAIR);
            }
            else
            {
                candidates.add(stringPair);
            }
        }

        for (StringPair candidate : candidates) {
            this.Add(candidate.Left(), candidate.Right());
        }
        
        return true;
    }
    
    public Boolean Add(String label, String id){
        if (!(Utils.IsProperLabel(label) && Utils.IsProperLabel(id))) {
            return false;
        }

        String l = label.toUpperCase();
        String i = id.toUpperCase();
        u6e.put(l, l);
        u6e.put(i, l);

        return true;
    }

    public String GetLabel(String label){
        return u6e.getOrDefault(label.toUpperCase(), label);
    }

    public Boolean Contains(String label){
        return u6e.containsKey(label.toUpperCase());
    }
}
