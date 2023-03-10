package juegos.recursos;

public class Carta {

    private String palo;
    private int numero;

    public  Carta(String palo, int numero) {
        this.palo = palo;
        this.numero = numero;
    }

    //función para obtener el valor de una carta
    public double getValor() {
        if (numero >= 1 && numero <= 7) {
            return numero;
        } else if (numero >= 10 && numero <= 12) {
            return 0.5;
        } else {
            return 0;
        }
    }

    //Getters and setters
    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carta{");
        sb.append("palo=").append(palo);
        sb.append(", numero=").append(numero);
        sb.append('}');
        return sb.toString();
    }

}
