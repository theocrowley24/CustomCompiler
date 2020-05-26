package Statements;

import Expressions.Expression;
import Symbols.GeneralSymbol;
import Symbols.GlobalSymbolTable;

import java.util.HashMap;

public class VariableDeclarationStatement implements Statement {

    private HashMap<String, GeneralSymbol> localSymbolTable;
    private Expression expression;
    private String name;

    public VariableDeclarationStatement(Expression expression, String name) {
        this.expression = expression;
        this.name = name;
    }

    public VariableDeclarationStatement setSymbolTable(HashMap<String, GeneralSymbol> localSymbolTable) {
        this.localSymbolTable = localSymbolTable;
        return this;
    }

    @Override
    public void execute() {
        if (localSymbolTable == null) {
            if (GlobalSymbolTable.globalSymbolTable.get(name) == null) {
                GlobalSymbolTable.globalSymbolTable.put(name, expression.evaluate());
            } else {
                System.out.println("ERROR Variable: " + name + " already declared.");
                System.exit(0);
            }

        } else {
            if (localSymbolTable.get(name) == null) {
                localSymbolTable.put(name, expression.evaluate());
            } else {
                System.out.println("ERROR Variable: " + name + " already declared.");
                System.exit(0);
            }
        }
    }
}
