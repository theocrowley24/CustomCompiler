package Expressions;

import Expressions.Enums.ExpressionType;
import Symbols.GeneralSymbol;
import Symbols.GlobalSymbolTable;

import java.util.HashMap;

public class VariableExpression extends Expression {

    private HashMap<String, GeneralSymbol> localSymbolTable;
    private String name;

    public VariableExpression(String name) {
        super(ExpressionType.VARIABLE);
        this.name = name;
    }

    public VariableExpression setLocalSymbolTable(HashMap<String, GeneralSymbol> localSymbolTable) {
        this.localSymbolTable = localSymbolTable;
        return this;
    }

    @Override
    public GeneralSymbol evaluate() {
        if (localSymbolTable == null) {
            if (GlobalSymbolTable.globalSymbolTable.get(name) != null) {
                return GlobalSymbolTable.globalSymbolTable.get(name);
            } else {
                System.out.println("ERROR Variable: " + name + " does not exist in scope.");
                System.exit(0);
                return null;
            }

        } else {
            return localSymbolTable.get(name);
        }
    }
}
