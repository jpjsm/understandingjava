package com.booleanexpressioncalculator;

import java.lang.Character;
import java.util.List;
import java.util.ArrayList;

public class Token {
    private TokenKind kind;
    private String operator;
    private Boolean hasValue;
    private Boolean value;
    private Boolean inUniverse;

    private static class GetTokenResult{
        private Token token;
        private int indexposition;
        private String errormsg = "";

        public GetTokenResult(
            Token t,
            int i,
            String errmsg
        ){
            this.token = t;
            this.indexposition = i;
            this.errormsg = errmsg;
        }

        public GetTokenResult(
            Token t,
            int i
        ){
            this(t, i, "");
        }

        public Token Token(){
            return this.token;
        }

        public int IndexPosition(){
            return this.indexposition;
        }

        public String ErrorMessage(){
            return this.errormsg;
        }

        public Boolean Error(){
            return this.errormsg != "";
        }
    }

    private static GetTokenResult Get_Token(
        String expression,
        int index,
        Context ctx,
        Universe uv
    ){
        // skip whitespace
        for (;
            index < expression.length() && 
                Character.isWhitespace(expression.charAt(index)); 
            index++){
            /* just increment index until the first non whitespace char */
        };

        if (index >= expression.length()) {
            Token t = new Token(TokenKind.INVALID, "",false, false, false);
            return new GetTokenResult(t, index, Resources.UNEXPECTED_END_OF_TEMPLATE);
        }

        switch(expression.charAt(index)){
            case '&':
                return new GetTokenResult(
                    new Token(TokenKind.AND, 
                              "&", 
                              false, 
                              false, 
                              false), 
                    index);

            case '|':
            case ',':
                return new GetTokenResult(
                    new Token(TokenKind.OR, 
                                "|", 
                                false, 
                                false, 
                                false), 
                    index);

            case '!':
                return new GetTokenResult(
                    new Token(TokenKind.NOT, 
                                "!", 
                                false, 
                                false, 
                                false), 
                    index);

            case '^':
                return new GetTokenResult(
                    new Token(TokenKind.XOR, 
                                "!", 
                                false, 
                                false, 
                                false), 
                    index);

            case '(':
                return new GetTokenResult(
                    new Token(TokenKind.OPENPARENTHESES, 
                                "(", 
                                false, 
                                false, 
                                false), 
                    index);

            case ')':
                return new GetTokenResult(
                    new Token(TokenKind.CLOSEPARENTHESES, 
                                ")", 
                                false, 
                                false, 
                                false), 
                    index);
    
            default:
                // probably it's a label
                // let's process labels outside the switch
        }

        if (! Utils.IsValidFirstCharForLabel(expression.charAt(index))) {
            return new GetTokenResult(null, index, Resources.INVALID_CHAR_ON_TEMPLATE);
        }

        String label = "";
        for (;
            index < expression.length() && 
                Utils.IsValidCharForLabel(expression.charAt(index)); 
            index++){
            label += expression.charAt(index);
        };

        String ulabel = label.toUpperCase();
        Boolean inContext = ctx.Contains(ulabel);
        Boolean inUniverse = uv.Contains(ulabel);
        Token newtoken = new Token(
            TokenKind.LABEL, 
            ulabel, 
            true, 
            inContext, 
            inUniverse
        );

        return new GetTokenResult(newtoken, index-1);
    }

    public Token(
        TokenKind kind,
        String operator,
        Boolean hasValue,
        Boolean value,
        Boolean inUniverse
    ){
        this.kind = kind;
        this.operator = operator;
        this.hasValue = hasValue;
        this.value = value;
        this.inUniverse = inUniverse;
    }

    public TokenKind Kind(){
        return this.kind;
    }

    public String Operator(){
        return this.operator;
    }

    public Boolean HasValue(){
        return this.hasValue;
    }

    public Boolean Value(){
        return this.value;
    }

    public void SetValue(Boolean v){
        if (this.kind == TokenKind.LABEL) {
            this.value = v;
            this.hasValue = true;
        }
    }

    public Boolean InUniverse(){
        return this.inUniverse;
    }

    public static Token[] Tokenize(String expression, Context ctx, Universe uv)
        throws Exception, IllegalArgumentException {
        // Argument validation
        if (expression == null || expression.isEmpty()){
            throw new IllegalArgumentException(Resources.EXPRESSION_NOT_NULL);
        }

        // If ctx is null, provide a default empty Context
        if(ctx == null){
            ctx = new Context();
        }

        // If uv is null, provide a default empty universe
        if(uv == null){
            uv = new Universe();
        }

        List<Token> tokens = new ArrayList<>();
        int expression_len = expression.length();

        for(
            int expression_index = 0; 
            expression_index < expression_len; 
            ){
                GetTokenResult token_result = Get_Token(expression, expression_index, ctx, uv);
                if (token_result.Error()) {
                    throw new Exception(token_result.ErrorMessage());
                }

                tokens.add(token_result.Token());
                expression_index = token_result.IndexPosition() + 1;
        }

        return tokens.toArray(new Token[0]);
    }
}
