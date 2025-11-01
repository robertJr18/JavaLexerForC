import Scanner_Lexer.Lexer;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //archivo por defecto
        File archivo = new File("src/entrada.c");

        System.out.println("=== ANALIZADOR LÉXICO ===");

        if (!archivo.exists()) {
            System.out.println("El archivo no existe: " + archivo.getAbsolutePath());
            return;
        }

        try {
            loadingAnimation("Leyendo archivo", 25);
            dotsAnimation("Analizando tokens", 7);

            System.out.println("\n--- TOKENS DETECTADOS ---\n");
            Lexer.scan(archivo);
            System.out.println("\n--- FIN DEL ANÁLISIS --- ");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


























    private static void loadingAnimation(String mensaje, int tiempo) throws InterruptedException {
        String[] frames = {"|", "/", "-", "\\"};
        System.out.print(mensaje);
        for (int i = 0; i < tiempo; i++) {
            System.out.print("\r" + mensaje + " " + frames[i % frames.length]);
            Thread.sleep(150);
        }
        System.out.print("\r" + mensaje + " ✓\n");
    }

    private static void dotsAnimation(String mensaje, int repeticiones) throws InterruptedException {
        System.out.print(mensaje);
        for (int i = 0; i < repeticiones; i++) {
            System.out.print(".");
            Thread.sleep(250);
        }
        System.out.println(" ✓");
    }
}
