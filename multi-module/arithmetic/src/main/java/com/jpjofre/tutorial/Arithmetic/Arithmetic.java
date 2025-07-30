package com.jpjofre.tutorial.Arithmetic;

public class Arithmetic {
    private Arithmetic(){}

    static public class DivisionResult{
        public final int Quotient;
        public final int Remainder;

        public DivisionResult(int q, int r){
            Quotient = q;
            Remainder = r;
        }
    }

    public static int Add(int a, int b){
        return a + b;
    }

    public static int Difference(int a, int b){
        return a - b;
    }

    public static int Multiply(int a, int b){
        return a * b;
    }

    public static DivisionResult Divide(int a, int b){
        DivisionResult r = new DivisionResult(a/b, a%b);
        return r;
    }
}
