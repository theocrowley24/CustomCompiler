package Parser;

import Expressions.*;
import Expressions.BinaryExpressions.*;
import Statements.OutStatement;
import Statements.Statement;
import Statements.VariableDeclarationStatement;
import Symbols.GeneralSymbol;
import Tokens.*;
import Tokens.Enums.TokenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {

    private ArrayList<Token> tokens;
    private ArrayList<Statement> statements;


    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        statements = new ArrayList<>();
    }

    private List<List<Token>> splitTokensOnSemicolon() {
        List<List<Token>> splitTokens = new ArrayList<>();
        List<Token> subList = new ArrayList<>();

        for (Token token : tokens) {
            if (token.getType() == TokenType.TERMINATION){
                splitTokens.add(subList);
                subList = new ArrayList<>();
            } else {
                subList.add(token);
            }
        }

        return splitTokens;
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

    private Expression parseExpression(List<Token> expressionTokens, HashMap<String, GeneralSymbol> localSymbolTable) {
        for (int i = 0; i < expressionTokens.size(); i++) {
            Token token = expressionTokens.get(i);
            if (token.getType() == TokenType.ADD && !isBracketed(expressionTokens, i)) {
                if (expressionTokens.get(i - 1).getType() == TokenType.STRING) {
                    return new StringBinaryExpression(parseExpression(expressionTokens.subList(0, i), null), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size()), null));
                } else {
                    return new NumberAddBinaryExpression(parseExpression(expressionTokens.subList(0, i), null), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size()), null));
                }

            } else if (token.getType() == TokenType.SUBTRACT && !isBracketed(expressionTokens, i)) {
                return new NumberSubtractBinaryExpression(parseExpression(expressionTokens.subList(0, i), null), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size()), null));
            }
        }

        for (int i = 0; i < expressionTokens.size(); i++) {
            Token token = expressionTokens.get(i);
            if (token.getType() == TokenType.MULTIPLY && !isBracketed(expressionTokens, i)) {
                return new NumberMultiplyBinaryExpression(parseExpression(expressionTokens.subList(0, i), null), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size()), null));
            } else if (token.getType() == TokenType.DIVIDE && !isBracketed(expressionTokens, i)) {
                return new NumberDivideBinaryExpression(parseExpression(expressionTokens.subList(0, i), null), parseExpression(expressionTokens.subList(i + 1, expressionTokens.size()), null));
            }
        }

        if (expressionTokens.get(0).getType() == TokenType.STRING) {
            return new StringExpression(((StringToken) expressionTokens.get(0)).getValue());
        } else if (expressionTokens.get(0).getType() == TokenType.NUMBER) {
            return new NumberExpression(((NumberToken) expressionTokens.get(0)).getValue());
        } else if (expressionTokens.get(0).getType() == TokenType.VARIABLE) {
            return new VariableExpression(((VariableToken) expressionTokens.get(0)).getName()).setLocalSymbolTable(localSymbolTable);
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
                        return parseExpression(expressionTokens.subList(i + 1, j), null);
                    }
                }
            }
        }

        return null;
    }


    public Statement parseStatement(List<Token> statementTokens) {

        if (statementTokens.get(0).getType() == TokenType.PRINT) {
            return new OutStatement(parseExpression(statementTokens.subList(1, statementTokens.size()), null));
        } else if (statementTokens.get(0).getType() == TokenType.TYPE && statementTokens.get(1).getType() == TokenType.VARIABLE && statementTokens.get(2).getType() == TokenType.EQUALS) {
            return new VariableDeclarationStatement(parseExpression(statementTokens.subList(3, statementTokens.size()), null),  ((VariableToken) statementTokens.get(1)).getName());
        }

        return null;
    }

    public void run() {
        List<List<Token>> splitTokens = splitTokensOnSemicolon();
        for (List<Token> tokenList : splitTokens) {
            statements.add(parseStatement(tokenList));
        }

        for (Statement statement : statements) {
            try {
                statement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
