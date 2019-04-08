package Expressions.BinaryExpressions;

import Expressions.Enums.ExpressionType;
import Expressions.Expression;
import Symbols.GeneralSymbol;
import Symbols.NumberGeneralSymbol;

public class NumberMultiplyBinaryExpression extends BinaryExpression {

    public NumberMultiplyBinaryExpression(Expression leftExpression, Expression rightExpression) {
        super(ExpressionType.NUMBER_MULTIPLY_BINARY, leftExpression, rightExpression);
    }

    @Override
    public GeneralSymbol evaluate() {
        return new NumberGeneralSymbol(((NumberGeneralSymbol) leftExpression.evaluate()).getValue() * ((NumberGeneralSymbol) rightExpression.evaluate()).getValue());
    }
}
