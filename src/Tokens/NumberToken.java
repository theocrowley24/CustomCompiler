package Tokens;

import Tokens.Enums.TokenType;

public class NumberToken extends Token{
    private double value;

    public NumberToken(double value) {
        super(TokenType.NUMBER);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
