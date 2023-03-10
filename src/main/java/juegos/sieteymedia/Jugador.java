package juegos.sieteymedia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import juegos.recursos.Carta;

public class Jugador {

    Scanner sc = new Scanner(System.in);
    private String nombre; //nombre del usuario
    private int credito = 1000; //credito que tiene el usuario en su cuenta
    private ArrayList<Carta> mano; //ArrayList que contiene las cartas en mano del jugador
    private int apuesta; //numero que contiene el valor de la apuesta total actual
    private double puntaje; //puntos que acumula procedente de las cartas

    /**
     * constructor nombre
     *
     * @param nombre nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new ArrayList<>();
    }

    /**
     * Constructor nombre y saldo
     *
     * @param nombre nombre del jugador
     * @param credito saldo que tiene el jugador en su cuenta
     */
    public Jugador(String nombre, int credito) {
        this.nombre = nombre;
        this.credito = credito;
        this.mano = new ArrayList<>();
    }

    /**
     * @param value numero int que puede ser positivo o negativo
     * @return credito actualizado del usuario
     */
    public int actualizaCredito(int value) {
        this.credito = credito + value;
        return credito;
    }

    /**
     * @param carta Añade una carta a la lista de cartas que tiene el jugador
     * @return arrayList con todas las cartas en mano del jugador
     */
    public ArrayList<Carta> añadirMano(Carta carta) {
        mano.add(carta);
        return mano;
    }
    
    /**
     * Elimina las cartas de la mano
     */
    public void BorrarMano(){
        this.mano = new ArrayList<>();
    }

    /**
     * Cuenta el valor de la mano actual
     * @return el valor de puntos de las cartas que se encuentran en la mano
     */
    public double contarPuntosMano() {
        double puntaje = 0;
        for (Carta c : mano) {
            double p = c.getValor();
            puntaje += p;
        }
        return puntaje;
    }

    public List<Carta> getCartas() {
        return this.mano;
    }

    //Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }
}
