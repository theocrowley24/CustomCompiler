package Lexer;

import Enums.Type;
import Tokens.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Lexer {
    private ArrayList<Token> tokens;
    private String path; //Path of source code
    private Charset encoding; //Character set used to encode source code, defaults to UTF-8

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public Lexer(String path, Charset encoding) {
        this.path = path;
        this.encoding = encoding;
        tokens = new ArrayList<>();
    }

    private boolean isBreaker(char c) {
        return c == '\"' || c == ';' || c == '+' || Character.isWhitespace(c) || Character.isDigit(c) || c == '(' || c == ')';
    }

    private String readFile() {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, encoding);
    }

    private Token getToken(String word) {
        if (word.equals(";")) {
            return new TerminationToken();
        } else if (word.matches("\\S+\\(.*\\)")) { //Function call
            String arguemnts = word.substring(word.indexOf("(")+1, word.lastIndexOf(")"));
            String name = word.substring(0, word.indexOf("("));
            String[] argumentsArray = arguemnts.split(",");
            ArrayList<Token> argumentsArrayList = new ArrayList<>();

            for (int i = 0; i < argumentsArray.length; i++) {
                argumentsArrayList.add(getToken(argumentsArray[i]));
            }

            return new FunctionCallToken(argumentsArrayList, name);
        } else if (word.matches("^\\\".*\\\"$")) { //String
            String value = word.substring(word.indexOf("\"")+1, word.lastIndexOf("\""));
            return new StringToken(value);
        } else if (word.equals("OUT")) {
            return new OutToken();
        } else if (word.equals("+")) {
            return new AddToken();
        } else if (word.matches("\\d+")) {
            return new NumberToken(Double.parseDouble(word));
        } else if (word.equals("*")) {
            return new MultiplyToken();
        } else if (word.equals("-")) {
            return new SubtractToken();
        } else if (word.equals("/")) {
            return new DivideToken();
        } else if (word.equals("(")) {
            return new OpenBracketToken();
        } else if (word.equals(")")) {
            return new CloseBracketToken();
        } else if (word.equals("Number")) {
            return new TypeToken(Type.NUMBER);
        } else if (word.equals("Boolean")) {
            return new TypeToken(Type.BOOLEAN);
        } else if (word.equals("String")) {
            return new TypeToken(Type.STRING);
        } else if (word.matches("\\w+")) {
            return new VariableToken(word);
        } else if (word.equals("=")) {
            return new EqualsToken();
        }
        return null;
    }

    public void run() {
        String file = readFile();
        file = file.replaceAll("(\\r|\\n)", "");
        file += ";";

        StringBuilder word = new StringBuilder();
        for (int i = 0; i < file.length(); i++) {
            if (isBreaker(file.charAt(i))) {
                Token token = getToken(word.toString());

                if (token != null) {
                    tokens.add(token);
                    word = new StringBuilder();
                }

                if (Character.isWhitespace(file.charAt(i))) {
                    i++;
                }

                if (file.charAt(i) ==';') {
                    tokens.add(new TerminationToken());
                    i++;
                }
            }

            word.append(file.charAt(i));

        }
    }
}
