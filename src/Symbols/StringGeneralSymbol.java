package Symbols;

import Symbols.Enums.GeneralSymbolType;

public class StringGeneralSymbol extends GeneralSymbol {

    private String value;

    public StringGeneralSymbol(String value) {
        super(GeneralSymbolType.STRING);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
