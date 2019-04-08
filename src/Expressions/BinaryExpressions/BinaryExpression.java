package Expressions.BinaryExpressions;

import Expressions.Enums.ExpressionType;
import Expressions.Expression;
import Symbols.GeneralSymbol;

public class BinaryExpression extends Expression {

    protected Expression leftExpression;
    protected Expression rightExpression;

    public BinaryExpression(ExpressionType type, Expression leftExpression, Expression rightExpression) {
        super(type);
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public ExpressionType getType() {
        return type;
    }

    @Override
    public GeneralSymbol evaluate() {
        return null;
    }
}
