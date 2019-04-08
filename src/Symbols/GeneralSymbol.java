package Symbols;

import Symbols.Enums.GeneralSymbolType;

public class GeneralSymbol {
    private GeneralSymbolType type;

    public GeneralSymbol(GeneralSymbolType type) {
        this.type = type;
    }

    public GeneralSymbolType getType() {
        return type;
    }
}
