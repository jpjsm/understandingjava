package com.booleanexpressioncalculator;

public class StringBooleanPair {
    private final String left;
    private final Boolean right;

    public StringBooleanPair(String left, Boolean right){
        this.left = left;
        this.right = right;
    }

    public String Left(){ return this.left; }
    public Boolean Right(){ return this.right; }

    @Override
    public String toString(){
        return this.left + ", " + this.right.toString();
    }
}
