package juegos.recursos;

import java.util.ArrayList;
import java.util.Random;

public class Baraja {

    private final String[] palos = {"Oro", "Basto", "Espada", "Copa"};
    private final int[] numeros = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};
    private int posEnBaraja; //Posicion que tiene actualmente la cartas
    //Arrays de la cartas
    private ArrayList<Carta> cartas = new ArrayList<>(); //ArrayList con TODAS las cartas ordenadas de la cartas
    private ArrayList<Carta> arrCartasRetiradasMonton = new ArrayList<>(); //Arr con las cartas que se han retirado
    private ArrayList<Carta> arrCartasRestantesMonton; //Arr con las cartas que quedan en el monton
    private ArrayList<Carta> manoDeCartas;
    
    
    /**
     * @return baraja ordenada Recorre el bucle que contiene el array de
     * 'numeros' anidado en otro bucle que contiene el array de 'palos' por cada
     * iteraccion genera una carta con un palo y número único que será cargada
     * en el ArrayList 'cartas' de forma ordenada
     */
    public ArrayList<Carta> generarBaraja() {
        for (String palo : palos) {
            for (int numero : numeros) {
                cartas.add(new Carta(palo, numero));
            }
        }
        posEnBaraja = cartas.size() - 1; //posicionamos la posición de la cartas en el último elemento (39)
        return cartas;
    }

    /**
     * funcion de desordenacion de cartas
     *
     * @return devuelve una baraja desordenada El algoritmo de desordenación
     * llevado a cabo es el Fisher-yates. Consiste en posicionar un indice en el
     * último elemento del Array (cartas-size() -1) y obtener un valor aleatorio
     * entre 0 y esa cantidad. El siguiente paso es alternar la posición final
     * con la del valor obtenido y posicionarnos una posición atrás respecto a
     * nuestro indice *El proceso se repite hasta llegar a indice 0. En ese
     * momento toda la baraja estará desordenada
     */
    public ArrayList<Carta> barajaCartas() {
        Random r = new Random();
        arrCartasRestantesMonton = new ArrayList<>();
        for (int indice = cartas.size() - 1; indice > 0; indice--) { //nos posicionamos en el ultimo elemento y vamos retrocediendo hasta llegar a 0
            int IndiceAleatorio = r.nextInt(indice + 1); //genera un numero aleatorio entre 0 y el indice en la que nos encontramos
            Carta tmp = cartas.get(IndiceAleatorio); // hacemos un objeto temporal carta con los datos de una carta de una posicion aleatoria
            cartas.set(IndiceAleatorio, cartas.get(indice)); //intercambiamos el objeto del indice con el del indiceAleatorio
            cartas.set(indice, tmp);
        }
        arrCartasRestantesMonton.addAll(cartas); //cargamos en otro array todos los datos de la baraja
        return cartas;
    }

    /**
     * @param num introduce el numero de cartas que queremos retirar del mazo;
     * @return devuelve un arrayList con las cartas que se han retirado del mazo
     */
    public ArrayList<Carta> daCartas(int num) {
        manoDeCartas = new ArrayList<>();
        for (int numCartasSolicitadas = num; numCartasSolicitadas > 0; numCartasSolicitadas--) {
            if (posEnBaraja >= 0 && posEnBaraja >= numCartasSolicitadas - 1) {
                Carta cartaSeleccionada = cartas.get(posEnBaraja);
                arrCartasRetiradasMonton.add(cartaSeleccionada);
                manoDeCartas.add(cartaSeleccionada);
                posEnBaraja--;
            } else {
                System.out.println("No quedan suficientes cartas para esa solicitud");
                
                return manoDeCartas;
            }
        }
        //eliminar las cartas retiradas del arraylist de cartasRestantesMonton:
        int tam = arrCartasRestantesMonton.size();
        arrCartasRestantesMonton.subList(tam - num, tam).clear();
        return manoDeCartas;
    }

    /**
     * Imprime las cartas de un arrayList
     * @param cartas arrayList que en su interior tiene objetos de tipo cartas
     */
    public void imprimirCartas(ArrayList<Carta> cartas) {
        for (Carta carta : cartas) {
            System.out.println(carta.getPalo() + " " + carta.getNumero() + " con valor de: " + carta.getValor());
        }
    }

    /**
     * @return arrayList cartas (baraja desordenada)
     */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /**
     * funcion para ver cartas retiradas
     * @return arrayList de las cartas que ya han sido dadas a los jugadores
     */
    public ArrayList<Carta> cartasDadas() {
        return arrCartasRetiradasMonton;
    }

    /**
     * funcion para ver las cartas del monton
     *
     * @return arrayList de las cartas que quedan aun en el montón
     */
    public ArrayList<Carta> cartasMazo() {
        return arrCartasRestantesMonton;
    }
    
    
    
    /**
     * baraja nuevamente las cartas de la baraja y resetea las cartas del monton a 0
     */
    public void reiniciarBaraja(){
        barajaCartas();
        arrCartasRetiradasMonton = new ArrayList<>();
    };

    public ArrayList<Carta> getManoDeCartas() {
        return manoDeCartas;
    }

    public void setManoDeCartas(ArrayList<Carta> manoDeCartas) {
        this.manoDeCartas = manoDeCartas;
    }

}
