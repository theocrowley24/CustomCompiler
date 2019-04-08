package Expressions;

import Expressions.Enums.ExpressionType;
import Symbols.GeneralSymbol;

public abstract class Expression {

    protected ExpressionType type;

    public Expression(ExpressionType type) {
        this.type = type;
    }

    public abstract ExpressionType getType();

    public abstract GeneralSymbol evaluate();
}
