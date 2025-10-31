// Programa de prueba para el lexer
#include <stdio.h>

int main() {
    int a = 1.05;
    int b = 2344.10;
    char c = 'x';
    int suma = a + b;

    if (suma >= 30) {
        printf("Resultado mayor o igual a 30\n");
    } else {
        printf("Resultado menor a 30\n");
    }

    // Bucle while simple
    while (a < b) {
        a = a + 1;
    }

    /* Comentario de
       múltiples líneas */
    do {
        a = a - 1;
    } while (a > 5);

    return 0;
}

/* CODIGO EN C.
int main() {
   int a;
   int b;
   a = b + 1;
   return 0;
}
*/