package Scanner_Lexer;

import java.io.*;
import java.util.*;

public class Lexer {
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
            "int", "main", "void", "if", "else", "while",
            "return", "printf", "scanf", "break", "do"
    ));

    private static final Map<String, String> SYMBOLS = new HashMap<>();

    static {
        //SIMPLES
        SYMBOLS.put("{", "LBRACE");
        SYMBOLS.put("}", "RBRACE");
        SYMBOLS.put("[", "LSQUARE");
        SYMBOLS.put("]", "RSQUARE");
        SYMBOLS.put("(", "LPAR");
        SYMBOLS.put(")", "RPAR");
        SYMBOLS.put(";", "SEMI");
        SYMBOLS.put("+", "PLUS");
        SYMBOLS.put("-", "MINUS");
        SYMBOLS.put("*", "MUL_OP");
        SYMBOLS.put("&", "AND_OP");
        SYMBOLS.put("/", "DIV_OP");
        SYMBOLS.put("%", "MOD_OP");
        SYMBOLS.put("|", "OR_OP");
        SYMBOLS.put("^", "XOR_OP");
        SYMBOLS.put("~", "NOTBIT_OP");
        SYMBOLS.put("!", "NOT_OP");
        SYMBOLS.put("=", "ASSIGN");
        SYMBOLS.put("<", "LT");
        SYMBOLS.put(">", "GT");
        SYMBOLS.put(".", "DOT");
        SYMBOLS.put(",", "COMMA");
        SYMBOLS.put("#", "HASH");

        //DOBLES
        SYMBOLS.put("==", "EQ");
        SYMBOLS.put("!=", "NOTEQ");
        SYMBOLS.put("<=", "LTEQ");
        SYMBOLS.put(">=", "GTEQ");
        SYMBOLS.put("&&", "ANDAND");
        SYMBOLS.put("||", "OROR");
        SYMBOLS.put("<<", "SHL_OP");
        SYMBOLS.put(">>", "SHR_OP");
        SYMBOLS.put("->", "ARROW");
        SYMBOLS.put("++", "INC_OP");
        SYMBOLS.put("--", "DEC_OP");
    }


    public static void scan(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int ch;

        while ((ch = reader.read()) != -1) {
            char c = (char) ch;

            // Espacios en blanco
            if (Character.isWhitespace(c)) continue;


            // Identificadores o Keywords
            if (Character.isLetter(c) || c == '_') {
                StringBuilder lexema = new StringBuilder();
                lexema.append(c);

                reader.mark(1);
                int next;
                while ((next = reader.read()) != -1) {
                    char nc = (char) next;
                    if (Character.isLetterOrDigit(nc) || nc == '_') {
                        lexema.append(nc);
                        reader.mark(1);
                    } else {
                        reader.reset();
                        break;
                    }
                }

                String token = lexema.toString();
                if (KEYWORDS.contains(token)) {
                    String tokenName = TokenMapper.getTokenName(token);
                    System.out.printf("Token: %s \"%s\"%n", tokenName, token);
                } else {
                    System.out.printf("Token: ID \"%s\"%n", token);
                }
            }

            // Números enteros o flotantes
            else if (Character.isDigit(c)) {
                StringBuilder numero = new StringBuilder();
                numero.append(c);
                boolean tienePunto = false;

                reader.mark(1);
                int next;
                while ((next = reader.read()) != -1) {
                    char nc = (char) next;

                    if (Character.isDigit(nc)) {
                        numero.append(nc);
                        reader.mark(1);
                    } else if (nc == '.' && !tienePunto) {
                        // Detecta primer punto decimal
                        tienePunto = true;
                        numero.append(nc);
                        reader.mark(1);
                    } else {
                        reader.reset();
                        break;
                    }
                }

                if (tienePunto)
                    System.out.printf("Token: FLOAT_NUM \"%s\"%n", numero);
                else
                    System.out.printf("Token: INT_NUM \"%s\"%n", numero);
            }

            // Literales de cadena o caracter
            else if (c == '"' || c == '\'') {
                char comilla = c;
                StringBuilder literal = new StringBuilder();
                literal.append(comilla);

                while ((ch = reader.read()) != -1) {
                    c = (char) ch;
                    literal.append(c);
                    if (c == '\\') { // Caracter de escape
                        literal.append((char) reader.read());
                    } else if (c == comilla) {
                        break;
                    }
                }

                if (comilla == '"')
                    System.out.printf("Token: STRING_LITERAL %s%n", literal);
                else
                    System.out.printf("Token: CHAR_LITERAL %s%n", literal);
            }

            // Comentarios y símbolos
            else {
                reader.mark(1);
                int next = reader.read();
                char nc = (next != -1) ? (char) next : '\0';


                if (c == '/' && nc == '/') {  // Comentario de línea
                    while ((ch = reader.read()) != -1 && ch != '\n');
                    continue;
                } else if (c == '/' && nc == '*') { // Comentario multilinea
                    int prev = 0;
                    while ((ch = reader.read()) != -1) {
                        if (prev == '*' && ch == '/') break;
                        prev = ch;
                    }
                    continue;
                }

                // Operadores
                String doble = "" + c + nc;
                if (SYMBOLS.containsKey(doble)) {
                    System.out.printf("Token: %s \"%s\"%n", SYMBOLS.get(doble), doble);
                } else {
                    if (next != -1) reader.reset();
                    String single = "" + c;
                    if (SYMBOLS.containsKey(single)) {
                        System.out.printf("Token: %s \"%s\"%n", SYMBOLS.get(single), single);
                    } else {
                        System.out.printf("Token desconocido: '%c'%n", c);
                    }
                }
            }
        }

        reader.close();
    }
}
