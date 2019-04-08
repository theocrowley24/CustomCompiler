package Expressions;

import Expressions.Enums.ExpressionType;
import Symbols.GeneralSymbol;
import Symbols.StringGeneralSymbol;

public class StringExpression extends Expression {

    private GeneralSymbol value;

    public StringExpression(String value) {
        super(ExpressionType.STRING);
        this.value = new StringGeneralSymbol(value);
    }

    @Override
    public GeneralSymbol evaluate() {
        return value;
    }
}
