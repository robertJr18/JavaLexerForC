package Scanner_Lexer;

import java.util.HashMap;
import java.util.Map;

public class TokenMapper {
    private static final Map<String, String> tokenMap = new HashMap<>();

    static {
        tokenMap.put("int", "INT");
        tokenMap.put("main", "MAIN");
        tokenMap.put("void", "VOID");
        tokenMap.put("if", "IF");
        tokenMap.put("else", "ELSE");
        tokenMap.put("while", "WHILE");
        tokenMap.put("return", "RETURN");
        tokenMap.put("break", "BREAK");
        tokenMap.put("do", "DO");

        tokenMap.put("printf", "WRITE");
        tokenMap.put("scanf", "READ");
    }

    public static String getTokenName(String lexema) {
        return tokenMap.getOrDefault(lexema, lexema.toUpperCase());
    }
}
