package com.booleanexpressioncalculator;

import java.util.NoSuchElementException;

public class Grammar {
    public static Boolean Primary(Stream<Token> ts) throws Exception {
        Token t;
        try {
            t = ts.Get();
        } catch (NoSuchElementException e) {
            throw new Exception(Resources.PRIMARY_NOT_FOUND);
        }
        
        switch (t.Kind()) {
            case NOT:
                try {
                    return !Primary(ts);
                } catch (Exception e) {
                    throw e;
                }

            case OPENPARENTHESES:
                Boolean value;
                try {
                    value = Expression(ts);
                } catch (Exception e) {
                    throw e;
                }
        
                Token nexToken;
                try {
                    nexToken = ts.Get();
                } catch (Exception e) {
                    throw new Exception(Resources.CLOSING_PARENTHESES_NOT_FOUND, e);
                }

                if(nexToken.Kind() != TokenKind.CLOSEPARENTHESES){
                    throw new Exception(Resources.CLOSING_PARENTHESES_MISSING);
                }

                return value;

            case LABEL:
                return t.Value();

            default:
                // Do nothing, the error will be thrown in the return after the
		        // switch statement
                break;
        }

        throw new Exception(Resources.PRIMARY_EXPECTED);
    }

    public static Boolean Term(Stream<Token> ts) throws Exception {
        Boolean left;
        Token t;

        try {
            left = Primary(ts);
        } catch (Exception e) {
            throw e;
        }

        while (! ts.EOS()) {
            try {
                t = ts.Get();                
            } catch (Exception e) {
                throw e;
            }

            switch (t.Kind()) {
                case XOR:
                    Boolean right;
                    try {
                        right = Term(ts);
                    } catch (Exception e) {
                        throw e;
                    }

                    left = left != right;
                    break;
            
                default:
                    ts.Push(t);
                    return left;
            }
        }

        return left;
    }

    public static Boolean Expression(Stream<Token> ts) throws Exception {
        Boolean left;
        Boolean right;
        Token t;

        try {
            left = Term(ts);
        } catch (Exception e) {
            throw e;
        }

        while (! ts.EOS()) {
            try {
                t = ts.Get();                
            } catch (Exception e) {
                throw e;
            }

            switch (t.Kind()) {
                case AND:
                    try {
                        right = Term(ts);
                    } catch (Exception e) {
                        throw e;
                    }

                    left = left && right;
                    break;
            
                case OR:
                    try {
                        right = Term(ts);
                    } catch (Exception e) {
                        throw e;
                    }

                    left = left || right;
                    break;

                default:
                    ts.Push(t);
                    return left;
            }
        }

        return left;

    }
}
