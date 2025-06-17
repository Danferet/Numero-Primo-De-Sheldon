public class NumeroPrimo {

    //Dos atributos, la posición en la lista de números primos y el número en sí.
    private int posicion;
    private int numero;

    public NumeroPrimo(int posicion, int numero) {

        this.posicion = posicion;
        this.numero = numero;
    }

    //No necesitaremos los métodos setter, pero sí los getter para poder acceder a los atributos dentro de las listas.
    public int getPosicion() {
        return posicion;
    }

    public int getNumero() {
        return numero;
    }

    //Un toString()para visualizar el objeto
    @Override
    public String toString() {
        return "Posicion: " + posicion + " -> " + numero;
    }
}
