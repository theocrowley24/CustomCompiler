import Lexer.Lexer;
import Parser.Parser;
import Symbols.GeneralSymbol;
import Symbols.GlobalSymbolTable;
import Symbols.NumberGeneralSymbol;
import Tokens.Token;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        GlobalSymbolTable.globalSymbolTable = new HashMap<>();
        Lexer lexer = new Lexer("code.txt", Charset.defaultCharset());
        lexer.run();
        ArrayList<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        parser.run();
    }

}
