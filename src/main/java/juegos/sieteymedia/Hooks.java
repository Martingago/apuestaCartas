package juegos.sieteymedia;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Hooks {

    Scanner sc = new Scanner(System.in);

    /**
     * Solicita nombre del usuario
     * @return nombre del usuario
     */
    public String solicitarNombre() {
        System.out.println("Cómo te llamas?");
        String nombre = sc.nextLine();
        return nombre;
    }

    /**
     * Muestra por salida de texto las normas de la partida
     */
    public void mostrarReglasJuego() {
        System.out.printf(
                "- Yo haré de banca\n"
                + "- Antes de pedir una carta, debes hacer una apuesta.\n"
                + "- La apuesta no puede ser inferior a 10\n"
                + "- Puedes sacar todas las cartas que quieras. Recuerda, las figuras (10, 11 y\n"
                + "12) valen medio punto y, el resto, su valor\n"
                + "- Si la suma de los valores de las cartas sacadas es superior a 7 y medio, se\n"
                + "pierde\n"
                + "- Puedes plantarte en cualquier momento\n"
                + "- Yo, al ser la banca, estoy obligado a sacar cartas hasta superar tu jugada o\n"
                + "pasarme\n"
                + "- Ganas si obtienes una jugada de valor superior a la mía\n"
                + "- En caso de empate, gano yo\n"
                + "- En caso de que uno de los dos saque 7 y media, se pagará el doble\n"
                + "- En caso de quedarte sin crédito, el juego finalizará\n");
    }

    /**
     * Texto de inicio de la aplicacion
     *
     * @param nombre
     * @param credito
     */
    public void outputTextoInicioPartida(String nombre, int credito) {
        System.out.printf("Bienvenido, " + nombre + " Vamos a jugar!\n"
                + "Pero antes, las reglas:\n"
        );
        mostrarReglasJuego();
        System.out.printf("Tu crédito actual es de: " + credito + " créditos\n"
                + "Empecemos!!! \n");
    }

    /**
     * Menu principal de la aplicacion
     *
     * @param jugador
     */
    public void menuPrincipalAplicacion(Jugador jugador) {
        System.out.printf("%50s%n", String.join("", Collections.nCopies(53, "-")));
        System.out.printf("| %-45s | %,d |%n", "VER MI CRÉDITO", 1);
        System.out.printf("| %-45s | %,d |%n", "AÑADIR CREDITO", 2);
        System.out.printf("| %-45s | %,d |%n", "NORMAS DEL JUEGO", 3);
        System.out.printf("| %-45s | %,d |%n", "REINICIAR PARTIDA", 4);
        System.out.printf("| %-45s | %,d |%n", "SALIR DE LA APLICACIÓN", 5);
        System.out.printf("%50s%n", String.join("", Collections.nCopies(53, "-")));
        int opcionJugador = verificarInputNumerico();
        switch (opcionJugador) {
            case (1) -> {
                System.out.println(jugador.getNombre() + " el saldo de tu cuenta es: " + jugador.getCredito());
            }
            case (2) -> {
                System.out.println("Introduce credito a la cuenta: (maximo 1000)");
                try {
                    int añadirBalance = sc.nextInt();
                    while (añadirBalance <= 1000) {
                        añadirBalance = sc.nextInt();
                    }
                } catch (Exception e) {
                    System.out.println("");
                }
            }
            case (3) -> {
                System.out.println("Las reglas del juego son las siguientes: ");
                mostrarReglasJuego();
            }
            case (5) -> {
                System.out.println("Saliendo de la partida...");
                verificarInputNumerico();
            }

        }

    }

    /**
     * Funcion que valida que el input del usuario sea un numero
     *
     * @return numero
     */
    public int verificarInputNumerico() {
        int numero = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                numero = sc.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error al introducir los datos. Debes introducir un número entero");
                sc.next(); //limpiamos la entrada de datos
            }
        }
        return numero;
    }
}
