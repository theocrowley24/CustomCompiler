package Expressions;

import Expressions.Enums.ExpressionType;
import Symbols.GeneralSymbol;

import java.util.HashMap;

public abstract class Expression {

    protected ExpressionType type;

    public Expression(ExpressionType type) {
        this.type = type;
    }

    public ExpressionType getType() {
        return type;
    }

    public abstract GeneralSymbol evaluate();
}
