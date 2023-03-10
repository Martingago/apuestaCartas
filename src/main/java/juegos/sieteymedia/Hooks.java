package juegos.sieteymedia;

import java.util.Scanner;

public class Hooks {

        /**
         * Output del texto inicio partida
         * @param nombre
         * @param credito 
         */
        public void outputTextoInicioPartida(String nombre, int credito) {
        System.out.printf("Bienvenido, " + nombre + " Vamos a jugar!\n"
                + "Pero antes, las reglas:\n"
                + "- Yo haré de banca\n"
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
                + "- En caso de quedarte sin crédito, el juego finalizará\n"
                + "Tu crédito actual es de: "+ credito  + " créditos\n"
                + "Empecemos!!! \n"
        );
    }
        
        public void menuPrincipalAplicacion(){
        Scanner sc = new Scanner(System.in);
            System.out.println("------------------------------------------------------");
            System.out.println("- VER MI CREDITO: ----------------------- 1 -");
            System.out.println("- JUGAR CONTRA LA MAQUINA ------- 2 -");
            System.out.println("- REINICIAR PARTIDA -------------------- 3- ");
            System.out.println("- SALIR DE LA APLICACIÓN ------------ 4 -");
            System.out.println("------------------------------------------------------");
            
            
        }
        
    
}
