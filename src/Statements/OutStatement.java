package Statements;

import Expressions.Expression;
import Symbols.BooleanGeneralSymbol;
import Symbols.Enums.GeneralSymbolType;
import Symbols.GeneralSymbol;
import Symbols.NumberGeneralSymbol;
import Symbols.StringGeneralSymbol;

public class OutStatement implements Statement {

    private Expression expression;

    public OutStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {

        GeneralSymbol value = expression.evaluate();

        if (value.getType() == GeneralSymbolType.BOOLEAN) {
            BooleanGeneralSymbol generalSymbol = (BooleanGeneralSymbol) value;
            System.out.println(generalSymbol.getValue());

        } else if (value.getType() == GeneralSymbolType.NUMBER) {
            NumberGeneralSymbol generalSymbol = (NumberGeneralSymbol) value;
            System.out.println(generalSymbol.getValue());

        } else if (value.getType() == GeneralSymbolType.STRING) {
            StringGeneralSymbol generalSymbol = (StringGeneralSymbol) value;
            System.out.println(generalSymbol.getValue());

        }
    }
}
