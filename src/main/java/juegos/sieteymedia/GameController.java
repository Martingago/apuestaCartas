package juegos.sieteymedia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import juegos.recursos.*;

public class GameController {

    Scanner sc = new Scanner(System.in);
    String nombre;
    Baraja baraja = new Baraja();
    Hooks hooks = new Hooks();
    Jugador jugador;
    Jugador maquina;

    /**
     * Método para solicitar nombre usuarios
     *
     * @return nombre del usuario
     */
    public String solicitarNombre() {
        System.out.println("Cómo te llamas?");
        this.nombre = sc.nextLine();
        return nombre;
    }

    /**
     * Método que inicia automaticamente los datos principales de una partida:
     * Solicita nombre al usuario y establece su crédito: por defecto 1000
     * Genera una baraja de cartas y posteriormente la ordena de forma aleatoria
     */
    public void inicioPartida() {
        solicitarNombre();
        jugador = new Jugador(nombre);
        maquina = new Jugador("Máquina", 99999);
        hooks.outputTextoInicioPartida(jugador.getNombre(), jugador.getCredito());
        baraja.generarBaraja();
        baraja.barajaCartas();
    }

    /**
     * Permite al usuario iniciar una apuesta
     *
     */
    public void comenzarApuesta() {
        System.out.println("Ingresa la cantidad de creditos que quieres apostar: ");
        int credito = jugador.getCredito();
        int apuestaTiradaCarta; //Establece la apuesta de la tirada del jugador
        int apuestaTotal; //Apuesta total (acumulada) del jugador en todas las tiradas
        //Si el jugador tiene mas de 10 creditos se le permite apostar
        if (jugador.getCredito() >= 10) {
            do {
                //Se le pide apuesta al usuario
                apuestaTiradaCarta = sc.nextInt();
                apuestaTotal = jugador.getApuesta() + apuestaTiradaCarta;
                if (apuestaTiradaCarta < 10) {
                    System.out.println("Error, cantidad mínima 10 créditos,  vuelve a introducir un valor válido");
                } else if (apuestaTiradaCarta > credito) {
                    System.out.println("Error, no tienes suficiente crédito para realizar la acción, tu saldo es de: " + credito);
                } else {
                    jugador.setApuesta(apuestaTotal);
                }
            } while (apuestaTiradaCarta < 10 || apuestaTiradaCarta > credito);
            credito = credito - apuestaTiradaCarta;
            jugador.setCredito(credito); //establecemos el nuevo credito que tendrá el jugador
            System.out.println("Apuesta realizada con éxito, saldo restante: " + credito + " apuesta total: " + jugador.getApuesta());

        } else if (jugador.getCredito() < 10 && jugador.getApuesta() > 10) {
            System.out.println("No dispones del crédito mínimo (10), tu saldo actual es de: " + credito + ". Se continúa con tu apuesta total:  " + jugador.getApuesta());
        }
    }

    /**
     * Saca una carta del monton y la coloca en tu mano
     *
     * @param element quien va a retirar carta, si el jugador o la maquina Esta
     * funcion llama a la funcion daCartas procedente de la clase Baraja, se
     * encarga de retirar una cantidad de cartas del montón (1) y añadirla a la
     * mano de quien retire las cartas. Esta función actualiza el ArrayList de
     * arrCartasRetiradasMonton y arrCartasRestantesMonton, los cuales nos
     * sirven para comprobar si se puede seguir la jugada, o si es necesario
     * barajar de nuevo
     */
    public void sacarCartaBaraja(Jugador element) {
        ArrayList<Carta> cartasObtenidas = baraja.daCartas(1);
        for (Carta c : cartasObtenidas) {
            element.añadirMano(c);
        }
        element.setPuntaje(element.contarPuntosMano());
        System.out.printf("Cartas obtenidas: %s%n", "\u001B[34m" + element.getCartas() + "\u001B[0m");
        System.out.println("Valor de la jugada: " + "\u001B[1m" + element.contarPuntosMano() + "\u001B[0m");
    }

    /**
     * Retira cartas de la baraja hasta igualar/superar al usuario o pasarse de
     * 7,5
     *
     * @return boolean: true: la máquina gana automáticamente (no se ha pasado
     * de los puntos y ha obtenido la misma puntuación que el jugador) false: la
     * máquina ha perdido, se ha pasado de los puntos
     */
    public boolean turnoMaquina() {
        //Tendre que añadir un getter de los puntos del jugador 
        double puntaje = 0;
        while (puntaje < 7.5 && puntaje < jugador.getPuntaje()) {
            try {
                sacarCartaBaraja(maquina);
                puntaje = maquina.contarPuntosMano();
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.err.println("Se ha producido una excepción InterruptedException: " + e.getMessage());
                e.printStackTrace();
            }
        }
        maquina.setPuntaje(puntaje);
        //saca cartas de la baraja
        System.out.println(maquina.getCartas());
        System.out.println(puntaje);
        if (maquina.getPuntaje() > 7.5) {
            System.out.println("Me he pasado, he perdido!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Turno del jugador en el juego
     *
     * @return boolean, true si el jugador no se pasa, false si pierde (se pasa
     * de 7,5)
     */
    public boolean turnoJugador() {
        sacarCartaBaraja(jugador);
        boolean continuar = preguntarJugadorContinuaPidiendoCartas();
        //funcion que pide carta o se planta metida en un while jugador.puntaje < 7,5 si se pasa pierde automaticamente
        while (continuar) {
            sacarCartaBaraja(jugador);
            if (jugador.getPuntaje() > 7.5) {
                System.out.println("Has perdido, te pasaste");
                return false;
            }
            continuar = preguntarJugadorContinuaPidiendoCartas();
        }
        return true;
    }

    /**
     * Pregunta al jugador si quiere continuar pidiendo cartas
     * @return 
     */
    public boolean preguntarJugadorContinuaPidiendoCartas() {
        System.out.println("¿Pides una [C]arta, o te [P]lantas?");
        char car = sc.next().toUpperCase().charAt(0);

        //Pedimos caracter al usuario hasta que introduzca una C o una P
        while (car != 'C' && car != 'P') {
            System.out.println(car);
            System.out.println("Error, introduce 'C' para Continuar, o 'P' para Salir ");
            car = sc.next().toUpperCase().charAt(0);
        }
        switch (car) {
            case 'C' -> {
                System.out.println("Continuando partida");
                comenzarApuesta();
                //se le pide nuevo credito minimo 10, en caso de no tener credito no se le pide
                return true;
            }
            case 'P' -> {
                System.out.println("Saliendo.... turno de la máquina");
                return false;
            }
            default -> {
                System.out.println("Saliendo...");
                return false;
            }
        }
    }

    public void mostrarPuntuacion() {
        System.out.println("Tu puntuación total de cartas es de: " + jugador.getPuntaje());
        System.out.println("La puntuación de la banca es de: " + maquina.getPuntaje());
    }

    /**
     * En caso de victoria simple, el jugador ingresará como beneficio la
     * cantidad apostada => apuesta 100 recibe 200 (100 de la apuesta + 100 de
     * la banca); En caso de victoria obteniendo 7,5 puntos recibirá el doble de
     * una apuesta normal (2x) => apuesta 100 recibe 300 ( 100 de la apuesta +
     * 100*2);
     */
    public void DarPuntosVictoria() {
        int cantApostada = jugador.getApuesta();
        int credito = jugador.getCredito();
        //Se le devuelve TODO lo que aposto
        credito = credito + cantApostada;

        if (jugador.getPuntaje() == 7.5) {
            //recibe doble puntos apostados
            credito = credito + (cantApostada * 2);
        } else {
            //Recibe la cantidad apostada normal
            credito = credito + cantApostada;
        }
        jugador.setCredito(credito);
        System.out.println("Tu nuevo credito es de: " + jugador.getCredito());
    }
    
    public void ContinuarSiguienteRondaJuego(){
    //Reinicia la mano de la maquina y del jugador:
    jugador.BorrarMano();
    maquina.BorrarMano();
    //Pone la apuesta del jugador en 0
   jugador.setApuesta(0);
        
    
    }

    public Baraja getBaraja() {
        return baraja;
    }

    public void setBaraja(Baraja baraja) {
        this.baraja = baraja;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getMaquina() {
        return maquina;
    }

    public void setMaquina(Jugador maquina) {
        this.maquina = maquina;
    }

    
    
}
