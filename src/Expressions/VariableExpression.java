package Expressions;

import Expressions.Enums.ExpressionType;
import Symbols.GeneralSymbol;

public class VariableExpression extends Expression {

    public VariableExpression(ExpressionType type) {
        super(type);
    }

    @Override
    public ExpressionType getType() {
        return null;
    }

    @Override
    public GeneralSymbol evaluate() {
        return null;
    }
}
