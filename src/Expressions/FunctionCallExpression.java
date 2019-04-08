package Expressions;

import Expressions.Enums.ExpressionType;
import Symbols.GeneralSymbol;

public class FunctionCallExpression extends Expression {

    public FunctionCallExpression(ExpressionType type) {
        super(ExpressionType.FUNCTION_CALL);
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
