package com.service.booleanexpressioncalculator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.System;

public class BooleanParser
{
    // Initial values
    private String expression;
    private List<Context> contexts = new ArrayList<>(List.of(new Context()));
    private Universe u6e = new Universe();

    // Generated
    private ParserStatus status = ParserStatus.UNDEFINED;
    private String errorMessages = null;
    private Token[] tokens = null;
    private String[] labelsNotInUniverse = null;
    private Boolean valueOnEmptyContext = false;
    private String parsedExpressionOnEmptyContext = "";
    private HashMap<String, String> ctxEvaluationResults = new HashMap<>();

    public BooleanParser(
        String exprssn,
        List<Context> ctxs,
        Universe unvs)
    throws Exception{
        // validate arguments
        if (exprssn == null || exprssn.isEmpty() || exprssn.isBlank()) {
            throw new IllegalArgumentException(Resources.EXPRESSION_NOT_NULL);
        }

        this.expression = exprssn;

        if(ctxs != null){
            this.contexts = ctxs;
        }

        if (unvs != null) {
            u6e = unvs;
        }

        // Evaluate expression on empty context
        try {
            Context emptyctx = new Context();
            this.status = ParserStatus.FAILEDTOKENIZING;
            this.tokens = Token.Tokenize(this.expression, emptyctx, u6e);

            this.status = ParserStatus.FAILEDPARSING;
            this.valueOnEmptyContext = EvaluateExpression(emptyctx);

            this.status = ParserStatus.SUCCESSFULPARSE;
            List<String> l = new ArrayList<>();
            for (Token t: this.tokens) {
                if (t.Kind() == TokenKind.LABEL && !t.InUniverse()) {
                    l.add(t.Operator());
                }
            }

            this.labelsNotInUniverse = new String[0];
            this.labelsNotInUniverse = l.toArray(this.labelsNotInUniverse);

            this.parsedExpressionOnEmptyContext = ParsedExpression();

        } catch (Exception e) {
            this.errorMessages = e.getMessage();
            return;
        }            

        // Evaluate expression in all contexts
        for (Context ctx : this.contexts) {
            // Re-evaluate tokens on context 'ctx'
            for(Token t: this.tokens){
                if (t.Kind() == TokenKind.LABEL) {
                    if (ctx.Contains(t.Operator())) {
                        t.SetValue(true);
                    } else{
                        t.SetValue(false);
                    }
                }
            }

            try {
                Boolean v = EvaluateExpression(ctx);
                String p = ParsedExpression();
                String x = ValuedParsedExpression();

                String r = p + " => " + x + " ==> " + v.toString().toUpperCase();
                String k = ctx.toString() + " -> " + ctx.ToUniverseValues(unvs);
                ctxEvaluationResults.put(k, r);
                
            } catch (Exception e) {
                throw e;
            }                
        }
    }

    public BooleanParser(
        String expression,
        Context ctx,
        Universe unvs)
    throws Exception
    {
        this(expression, new ArrayList<Context>(List.of(ctx)), unvs);
    }

    public BooleanParser(
        String expression,
        List<Context> ctxs)
        throws Exception
    {
        this(expression, ctxs, null);
    }

    public BooleanParser(
        String expression,
        Context ctx)
        throws Exception
    {
        this(expression, new ArrayList<Context>(List.of(ctx)), null);
    }

    public BooleanParser(
        String expression)
        throws Exception
    {
        this(expression, (Context)null);
    }

    public ParserStatus Status(){
        return this.status;
    }

    public String[] LabelsNotInUniverse(){
        String[] result = new String[labelsNotInUniverse.length];
        System.arraycopy(labelsNotInUniverse, 0, result, 0, labelsNotInUniverse.length);

        return result;
    }

    public String ErrorMessages(){
        return errorMessages;
    }

    public String ParsedExpressionDefault(){
        return this.parsedExpressionOnEmptyContext;
    }

    public Boolean ValueOnEmptyContext(){
        return this.valueOnEmptyContext;
    }

    public Token[] Tokens(){
        if (this.status != ParserStatus.SUCCESSFULPARSE) {
            return null;
        }

        Token[] result = new Token[tokens.length];
        System.arraycopy(tokens, 0, result, 0, tokens.length);

        return result;
    }

    public String[] ContextEvaluationResults(){
        if (this.status != ParserStatus.SUCCESSFULPARSE) {
            return null;
        }

        String[] results = new String[ctxEvaluationResults.size()];

        List<String> ctxs = ctxEvaluationResults.keySet()
                           .stream()
                           .sorted()
                           .collect(Collectors.toList());

        int i = 0;
        for (String ctx : ctxs) {
            results[i++] = ctx + ": " + ctxEvaluationResults.get(ctx);                           
        }

        return results;
    }

    private String ParsedExpression(){
        if (this.status != ParserStatus.SUCCESSFULPARSE) {
            return null;
        }

        List<String> operators = new ArrayList<>();

        for(Token t: this.tokens){
            operators.add(t.Operator());
        }

        return String.join(" ", operators);
    }

    private String ValuedParsedExpression(){
        if (this.status != ParserStatus.SUCCESSFULPARSE) {
            return null;
        }

        List<String> operators = new ArrayList<>();

        for(Token t: this.tokens){

            if (t.Kind() == TokenKind.LABEL) {
                operators.add(t.Value().toString().toUpperCase());
            } else {
                operators.add(t.Operator());
            }
        }

        return String.join(" ", operators);
    }

    private Boolean EvaluateExpression(Context ctx)
    throws Exception
    {
        Stream<Token> ts = new Stream<>();
        for (Token token : this.tokens) {
            Token t = new Token(
                token.Kind(), 
                token.Operator(), 
                token.HasValue(), 
                token.Value(), 
                token.InUniverse());
            ts.Append(t);
        }

        Boolean v = false;
        try {
            v = Grammar.Expression(ts);
        } catch (Exception e) {
            throw e;
        }
        
        return v;
    }
}
