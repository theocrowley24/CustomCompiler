package Expressions.BinaryExpressions;

import Expressions.Enums.ExpressionType;
import Expressions.Expression;
import Symbols.GeneralSymbol;
import Symbols.StringGeneralSymbol;

public class StringBinaryExpression extends BinaryExpression {

    public StringBinaryExpression(Expression leftExpression, Expression rightExpression) {
        super(ExpressionType.STRING_BINARY, leftExpression, rightExpression);
    }

    @Override
    public GeneralSymbol evaluate() {
        return new StringGeneralSymbol(((StringGeneralSymbol) leftExpression.evaluate()).getValue() + ((StringGeneralSymbol) rightExpression.evaluate()).getValue());
    }
}
