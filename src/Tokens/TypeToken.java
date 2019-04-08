package Tokens;

import Enums.Type;
import Tokens.Enums.TokenType;

public class TypeToken extends Token {
    private Type type_;

    public TypeToken(Type type_) {
        super(TokenType.TYPE);
        this.type_ = type_;
    }

    public Type getType_() {
        return type_;
    }

}
