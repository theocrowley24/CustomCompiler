package Statements;

import Symbols.BooleanGeneralSymbol;
import Symbols.Enums.GeneralSymbolType;
import Symbols.GeneralSymbol;
import Symbols.NumberGeneralSymbol;
import Symbols.StringGeneralSymbol;

public class OutStatement implements Statement {

    private GeneralSymbol value;

    public OutStatement(GeneralSymbol value) {
        this.value = value;
    }

    @Override
    public void execute() {

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
