package Tokens;

import Tokens.Enums.TokenType;

public abstract class Token {
    private TokenType type;

    public Token(TokenType type) {
        this.type = type;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public boolean equals(Object token) {
        return ((Token) token).getType() == this.type;
    }
}
