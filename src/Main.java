import Lexer.Lexer;
import Parser.Parser;
import Tokens.Token;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer("code.txt", Charset.defaultCharset());
        lexer.run();
        ArrayList<Token> tokens = lexer.getTokens();
        Parser parser = new Parser(tokens);
        parser.run();
    }

}
