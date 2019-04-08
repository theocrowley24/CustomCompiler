package Expressions;

import Expressions.Enums.ExpressionType;
import Symbols.GeneralSymbol;
import Symbols.NumberGeneralSymbol;

public class NumberExpression extends Expression {

    private double value;

    public NumberExpression(double value) {
        super(ExpressionType.NUMBER);
        this.value = value;
    }

    @Override
    public GeneralSymbol evaluate() {
        return new NumberGeneralSymbol(value);
    }
}
