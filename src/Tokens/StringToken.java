package Tokens;

import Tokens.Enums.TokenType;

public class StringToken extends Token {
    private String value;

    public StringToken(String value) {
        super(TokenType.STRING);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
