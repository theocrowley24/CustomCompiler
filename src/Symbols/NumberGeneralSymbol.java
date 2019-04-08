package Symbols;

import Symbols.Enums.GeneralSymbolType;

public class NumberGeneralSymbol extends GeneralSymbol{

    private double value;

    public NumberGeneralSymbol(Double value) {
        super(GeneralSymbolType.NUMBER);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
