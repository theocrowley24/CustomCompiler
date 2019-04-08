package Tokens;

import Tokens.Enums.TokenType;
import Tokens.Token;

public class VariableToken extends  Token {

    private String name;

    public VariableToken(String name) {
        super(TokenType.VARIABLE);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
