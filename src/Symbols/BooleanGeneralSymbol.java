package Symbols;

import Symbols.Enums.GeneralSymbolType;

public class BooleanGeneralSymbol extends GeneralSymbol {

    private boolean value;

    public BooleanGeneralSymbol(boolean value) {
        super(GeneralSymbolType.BOOLEAN);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
