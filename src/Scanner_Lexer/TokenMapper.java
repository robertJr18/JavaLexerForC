package Scanner_Lexer;

import java.util.HashMap;
import java.util.Map;

public class TokenMapper {

    // Mapa de lexemas → nombres de token
    private static final Map<String, String> tokenMap = new HashMap<>();

    static {
        // Palabras clave con su "nombre de token"
        tokenMap.put("int", "INT");
        tokenMap.put("main", "MAIN");
        tokenMap.put("void", "VOID");
        tokenMap.put("if", "IF");
        tokenMap.put("else", "ELSE");
        tokenMap.put("while", "WHILE");
        tokenMap.put("return", "RETURN");
        tokenMap.put("break", "BREAK");
        tokenMap.put("do", "DO");

        // Mapeo especial según la tarea
        tokenMap.put("printf", "WRITE");
        tokenMap.put("scanf", "READ");
    }

    /**
     * Devuelve el nombre del token correspondiente al lexema.
     * Si no está en el mapa, devuelve el lexema en mayúsculas.
     */
    public static String getTokenName(String lexema) {
        return tokenMap.getOrDefault(lexema, lexema.toUpperCase());
    }
}
