package Expressions.BinaryExpressions;

import Expressions.Enums.ExpressionType;
import Expressions.Expression;
import Symbols.GeneralSymbol;
import Symbols.NumberGeneralSymbol;

public class NumberAddBinaryExpression extends BinaryExpression {

    public NumberAddBinaryExpression(Expression leftExpression, Expression rightExpression) {
        super(ExpressionType.NUMBER_ADD_BINARY, leftExpression, rightExpression);
    }

    @Override
    public GeneralSymbol evaluate() {
        return new NumberGeneralSymbol(((NumberGeneralSymbol) leftExpression.evaluate()).getValue() + ((NumberGeneralSymbol) rightExpression.evaluate()).getValue());
    }
}
