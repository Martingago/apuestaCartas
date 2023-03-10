package main.sieteymedio;

import juegos.sieteymedia.GameController;

public class Sieteymedio {

    public static void main(String[] args) {
        //se inicia un game controller y se establecen los datos del usuario
        GameController controlador = new GameController();
        controlador.inicioPartida();
        //Solicita al jugador que realice su apuesta
        int credito = controlador.getJugador().getCredito();
        
        while(credito >10){
        controlador.comenzarApuesta();
        
        /**
         * se ejecuta el turno del jugador, devuelve boolean: true: el jugador
         * no se ha pasado de los 7.5 puntos => se procede con el turno de la
         * máquina false: El jugador se ha pasado, pierde automaticamente
         */
        boolean turnoDelJugador;

        /**
         * Se ejecuta el turno de la maquina siempre y cuando el jugador no se
         * haya pasado de 7,5 puntos La funcion devolverá un boolean: true: la
         * máquina ha ganado (supero o igualó al usuario sin pasarse de 7,5)
         * false: la máquina pierde (supero o igualó al usuario pero se pasó de
         * 7,5)
         */
        boolean turnoDeMaquina = false; //Por defecto false

        turnoDelJugador = controlador.turnoJugador();

        if (turnoDelJugador) {
            System.out.println("Turno de la máquina: ");
            turnoDeMaquina = controlador.turnoMaquina();
            if (turnoDeMaquina) {
                System.out.println("Has perdido!");
                controlador.mostrarPuntuacion();
            } else {
                System.out.println("Has ganado!");
                controlador.mostrarPuntuacion();
                controlador.DarPuntosVictoria();
            }
        } else {
            //El jugador pierde automaticamente
            System.out.println("Has perdido!");
            controlador.mostrarPuntuacion();
        }
        credito = controlador.getJugador().getCredito();
        controlador.ContinuarSiguienteRondaJuego();
    }
    }
}
