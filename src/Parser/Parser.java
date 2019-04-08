package Parser;

import Expressions.*;
import Expressions.BinaryExpressions.*;
import Statements.OutStatement;
import Statements.Statement;
import Tokens.CloseBracketToken;
import Tokens.Enums.TokenType;
import Tokens.NumberToken;
import Tokens.StringToken;
import Tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private ArrayList<Token> tokens;
    private ArrayList<Statement> statements;


    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    private boolean isBracketed(List<Token> tokens, int i) {
        int x = 0;

        while (i >= 0) {
            if (tokens.get(i).getType() == TokenType.CLOSE_BRACKET) {
                x--;
            } else if (tokens.get(i).getType() == TokenType.OPEN_BRACKET) {
                x++;
            }
            i--;
        }

        return x > 0;
    }

    private Expression parseExpression(List<Token> expressionTokens) {
        for (int i = 0; i < expressionTokens.size(); i++) {
            Token token = expressionTokens.get(i);
            if (token.getType() == TokenType.ADD && !isBracketed(expressionTokens, i)) {
                if (expressionTokens.get(i - 1).getType() == TokenType.STRING) {
                    return new StringBinaryExpression(parseExpression(expressionTokens.subList(0, i)), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size())));
                } else {
                    return new NumberAddBinaryExpression(parseExpression(expressionTokens.subList(0, i)), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size())));
                }

            } else if (token.getType() == TokenType.SUBTRACT && !isBracketed(expressionTokens, i)) {
                return new NumberSubtractBinaryExpression(parseExpression(expressionTokens.subList(0, i)), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size())));
            }
        }

        for (int i = 0; i < expressionTokens.size(); i++) {
            Token token = expressionTokens.get(i);
            if (token.getType() == TokenType.MULTIPLY && !isBracketed(expressionTokens, i)) {
                return new NumberMultiplyBinaryExpression(parseExpression(expressionTokens.subList(0, i)), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size())));
            } else if (token.getType() == TokenType.DIVIDE && !isBracketed(expressionTokens, i)) {
                return new NumberDivideBinaryExpression(parseExpression(expressionTokens.subList(0, i)), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size())));
            }
        }

        if (expressionTokens.get(0).getType() == TokenType.STRING) {
            return new StringExpression(((StringToken) expressionTokens.get(0)).getValue());
        } else if (expressionTokens.get(0).getType() == TokenType.NUMBER) {
            return new NumberExpression(((NumberToken) expressionTokens.get(0)).getValue());
        }

        for (int i = 0; i < expressionTokens.size(); i++) {
            Token token = expressionTokens.get(i);
            if (token.getType() == TokenType.OPEN_BRACKET) {

                int x = 0;
                for (int j = i + 1; j < expressionTokens.size(); j++) {
                    if (expressionTokens.get(j).getType() == TokenType.OPEN_BRACKET) {
                        x++;
                    } else if (expressionTokens.get(j).getType() == TokenType.CLOSE_BRACKET && x > 0) {
                        x--;
                    } else if (expressionTokens.get(j).getType() == TokenType.CLOSE_BRACKET && x == 0) {
                        return parseExpression(expressionTokens.subList(i + 1, j));
                    }
                }

                //return parseExpression(expressionTokens.subList(i + 1, expressionTokens.indexOf(new CloseBracketToken())));
            }
        }

        return null;
        /*
        Expression currentExpression = null;

        for (int i = 0; i < expressionTokens.size(); i++) {

            Token token = expressionTokens.get(i);

            if (token.getType() == TokenType.STRING) {
                currentExpression = new StringExpression(((StringToken) token).getValue());
            } else if (token.getType() == TokenType.NUMBER) {
                currentExpression = new NumberExpression(((NumberToken) token).getValue());
            } else if (token.getType() == TokenType.ADD) {
                if (currentExpression.getType() == ExpressionType.STRING) {
                    currentExpression = new StringBinaryExpression(currentExpression, parseExpression(expressionTokens.subList(i + 1, expressionTokens.size())));
                    return currentExpression;
                } else if (currentExpression.getType() == ExpressionType.NUMBER) {
                    currentExpression = new NumberAddBinaryExpression(currentExpression, parseExpression(expressionTokens.subList(i + 1, expressionTokens.size())));
                    return currentExpression;
                }

            } else if (token.getType() == TokenType.MULTIPLY) {
                return new NumberMultiplyBinaryExpression(currentExpression, parseExpression(expressionTokens.subList(i + 1, expressionTokens.size())));
            }
        }

        return currentExpression;
        */
    }


    public Statement parseStatement(List<Token> statementTokens) {

        if (statementTokens.get(0).getType() == TokenType.PRINT) {
            return new OutStatement(parseExpression(statementTokens.subList(1, statementTokens.size())).evaluate());
        }

        return null;
    }

    public void run() {
        parseStatement(tokens).execute();
    }
}
