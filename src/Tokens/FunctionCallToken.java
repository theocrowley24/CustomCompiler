package Tokens;

import Tokens.Enums.TokenType;

import java.util.ArrayList;

public class FunctionCallToken extends Token {
    private ArrayList<Token> arguments;
    private String name;

    public FunctionCallToken(ArrayList<Token> arguments, String name) {
        super(TokenType.FUNCTIONCALL);
        this.arguments = arguments;
        this.name = name;
    }
}
