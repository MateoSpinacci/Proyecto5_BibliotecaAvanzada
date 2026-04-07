package BibliotecaCode.service;

import java.util.Scanner;

public class Verificadores {

    public int verificarEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número valido");
            }
        }
    }

    public String verificarVacio(Scanner sc, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String variable = sc.nextLine();
            if (variable.isBlank()) {
                System.out.println("Ingrese un valor correcto");
            } else {
                return variable.trim();
            }
        }
    }
}
