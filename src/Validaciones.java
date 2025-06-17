import java.util.ArrayList;
import java.util.List;

public class Validaciones {

    //Necesitamos un método que invierta los números, conseguido de la siguiente manera:
    //Pasamos el número del que queremos conseguir su invertido por parámetro

    private static int invertirNumero(int num) {

        //Creamos una variable int que va a ir almacenándose el número de forma acumulativa
        int numNuevo = 0;

        //El número va a ir reduciéndose en un 10% a cada vuelta del bucle, que continuará mientras sea mayor de 0.
        while (num > 0) {

            //Obtenemos el módulo de 10 de nuestro número (es decir, el último dígito)
            int ultimoDigito = num % 10;

            //Nuestro nuevo número deberá multiplicarse por diez por cada vuelta iteración
            // y sumarse el último dígito obtenido.
            //De esta forma, cada número obtenido anteriormente irá pasando a la izquierda,
            //o más bien cada nuevo dígito
            //se irá colocando a la derecha,
            numNuevo = (numNuevo * 10) + ultimoDigito;

            //Finalmente, dividimos el número original entre 10. Al ser un tipo entero, perderemos el último número
            //y podremos acceder al "nuevo últimoDigito" en la siguiente iteración
            num = num / 10;

        }
        return numNuevo;
    }

    //Solo nos interesan los números primos y sus posiciones,
    //Por lo que vamos a crear este método para obtener una lista de ellos
    //Su posición será igual a su posición en la lista +1.
    private static boolean esPrimo(int num) {

        //Iniciamos la variable booleana que nos va a devolver el método en true.
        boolean primo = true;

        //Para saber si un número es primo dividimos el número por todos los números anteriores a él desde el 2
        //(todos los números son divisibles por 1).
        //En caso de que ninguna división por ningún número de resto 0 hasta la raíz cuadrada del número
        //Este es el límite para encontrar un divisor en números impares, de pares simplemente para en el 2).
        for (int i = 2; i <= Math.sqrt(num); i++) {

            if (num % i == 0) {
                primo = false;

                //Si el bucle encuentra un divisor distinto de 1 nos salimos porque no tiene sentido seguir buscando
                //y el valor booleano pasa a ser false.
                break;
            }
        }
        //El método nos devolverá simplemente un true o false dependiendo de si es primo o no.
        return primo;
    }

    //Este método nos devolverá una lista de Integer que solo pueden ser números primos.
    //Nos devolverá hasta una posición que le pasemos por parámetro
    private static List<Integer> rellenarListaConPrimos(int num) {

        List<Integer> numerosPrimos = new ArrayList<>();

        for (int i = 2; i < num; i++) {

            //Con el método creado arriba esPrimo(), validamos si cada numero desde 2
            //hasta el número pedido por el usuario es o no primo.
            //Si lo es, se añade a la lista.
            if (Validaciones.esPrimo(i)) {

                numerosPrimos.add(i);
            }
        }
        return numerosPrimos;
    }

    //Para hacer este programa he creado un objeto llamado NumeroPrimo que tiene una posición y el número propiamente dicho
    //Este método nos devolverá la lista de numeros primos como objetos Numero primo
    public static List<NumeroPrimo> listaNumeros(int num) {

        List<NumeroPrimo> lista = new ArrayList<>();

        int contador = 0;

        //Creo una lista de numeros primos.
        //Llama al método privado rellenarListaConPrimos(),
        //que a su vez utiliza el método privado esPrimo()
        List<Integer> listaOriginal = rellenarListaConPrimos(num);

        for (int i = 0; i < listaOriginal.size(); i++) {

            //Las listas se inician en el índice 0, pero la posición real sería "primera" o "primera posición",
            //es decir, 1, por lo que la posición del número siempre deberá ser i + 1.
            NumeroPrimo np = new NumeroPrimo((i+1), listaOriginal.get(i));

            //Se añade el objeto que se acaba de crear a la lista.
            lista.add(np);

            contador++;
        }

        //Imprimo resultados para visualizar
        if (lista.isEmpty()) {
            System.out.println("En " + num + " no hay ningún número primo.");
            System.out.println("\n===========================\n");
        } else {
            System.out.println("Lista de numeros primos en " + num + " numeros: " + contador + " números.\n");
            visualizarLista(lista);
            System.out.println("\n===========================\n");

        }
        return lista;
    }

    //Utilizo este método para ir visualizando cuántos número tiene cada lista que voy creando y cribando.
    public static void visualizarLista(List<NumeroPrimo> lista) {

        for (NumeroPrimo numeroPrimo : lista) {
            System.out.println(numeroPrimo);
        }
    }

    //Este método devuelve solo los numeros primos cuyo número invertido también es primo (por ejemplo 13 y 31)
    public static List<NumeroPrimo> listaPrimosInvertidos(List<NumeroPrimo> lista) {

        List<NumeroPrimo> listaInvertidos = new ArrayList<>();

        int contador = 0;

        //El for se debe iniciar en 13 porque todos los números anteriores no tienen un invertido como tal
        //Del 2 al 9 su invertido es el mismo, el 10 no es primo y el 11 pasa lo mismo, su invertido es sí mismo.
        //El 12 tampoco es primo, por lo que empiezo el bucle en 13 (posición 6, 5+1) para optimizar el inicio de la búsqueda.
        for (int i = 5; i < lista.size(); i++) {

            //Creo una variable que va a ser el invertido del número que estamos revisando.
            //Se utiliza el método privado invertirNumero() de esta misma clase.
            int primoInvertido = invertirNumero(lista.get(i).getNumero());

            //Valoramos si el invertido es primo
            if (esPrimo(primoInvertido)) {

                //Si lo es, pasa a la lista
                listaInvertidos.add(lista.get(i));
                contador++;

            }
        }

        //Imprimo resultados para visualizar

        if (listaInvertidos.isEmpty()) {
            System.out.println("No hay ningún primo cuyo invertido también sea primo.");
            System.out.println("\n===========================\n");
        } else {
            System.out.println("Numeros primos cuyo invertido también es un número primo: "
                    + contador + " números de " + lista.size() + " numeros.\n");
            visualizarLista(listaInvertidos);

            System.out.println("\n===========================\n");
        }
        return listaInvertidos;
    }

    //Este método nos devolverá los numeros de las lista anteriores cuya posición invertida sea la misa
    //Por ejemplo (no es real): posición 25 -> 139, posición 52 -> 931.
    //Es decir, estos numeros ya no solo tienen un invertido que es primo,
    //sino que sus posiciones invertidas pueden intercambiarse con las del otro número.
    public static List<NumeroPrimo> listaPosicionesInvertidas(List<NumeroPrimo> lista) {

        List<NumeroPrimo> posicionesInvertidas = new ArrayList<>();

        int contador = 0;
        //Con un primer for recorro cada número de la lista
        for (int i = 0; i < lista.size(); i++) {

            //Con un segundo for, comparo cada número con los demás.
            for (int j = 0; j < lista.size(); j++) {

                //Si el número que tenemos en este momento (i) es igual al número invertido con el que lo comparamos (j)
                if ((lista.get(i).getNumero() == invertirNumero(lista.get(j).getNumero())) &&

                        //comparamos la posición del número (i) con la posición invertida del número comparado (j)
                        (lista.get(i).getPosicion() == invertirNumero(lista.get(j).getPosicion()))) {

                    //Si ambas cosas ocurren, ese número se añadirá a una nueva lista mucho más corta.
                    posicionesInvertidas.add(new NumeroPrimo(lista.get(i).getPosicion(), lista.get(i).getNumero()));

                    contador++;
                }
            }
        }

        //Imprimo resultados para visualizar.

        if (posicionesInvertidas.isEmpty()) {
            System.out.println("No hay posiciones invertidas iguales.");
            System.out.println("\n===========================\n");
        } else {
            System.out.println("Números invertidos iguales cuyas posiciones " +
                    "invertidas también son iguales: " + contador + " números de " + lista.size() + ".\n");
            visualizarLista(posicionesInvertidas);
            System.out.println("\n===========================\n");
        }
        return posicionesInvertidas;
    }

    //Finalmente, para que un número primo sea un número primo de Sheldon tiene que tener una última característica:
    //el producto de sus dígitos debe dar como resultado su posición en la lista de números primos.
    //Este último método nos dirá si algún número cumple esa característica.
    public static List<NumeroPrimo> numeroPrimoDeSheldon(List<NumeroPrimo> lista) {

        List<NumeroPrimo> listaFinal = new ArrayList<>();

        int contador = 0;

        //Recorremos la lista de números que nos queda, la de números primos
        //cuyo invertido es primo y sus posiciones invertidas son las mismas
        for (int i = 0; i < lista.size(); i++) {

            //Creo una variable que va a ser igual a la posición y otra va a ser el número
            int posicion = lista.get(i).getPosicion();
            int numero = lista.get(i).getNumero();

            //Paso los números a String y los meto en un array
            String digitosNumero = String.valueOf(numero);
            String[] arrayDigitos = new String[digitosNumero.length()];

            //Creo un acumulador iniciado en 1, ya que al multiplicar el primer número
            //dará como resultado ese mismo número y a partir de ahí se seguirá normalmente con las multiplicaciones
            int multiplicacionNumerosPosicion = 1;

            //Un segundo form irá multiplicando cada número con el siguiente del array
            for (int j = 0; j < digitosNumero.length(); j++) {

                arrayDigitos[j] = digitosNumero.substring(j, j + 1);

                //Para poder multiplicar, hay que pasar los datos, que estaban en String, a Integer
                multiplicacionNumerosPosicion *= Integer.parseInt(arrayDigitos[j]);

            }

            //Finalmente, comparamos el resultado de las multiplicaciones de dígitos con la posición del número.
            //Si coinciden, este es un número primo de Sheldon.
            if (posicion == multiplicacionNumerosPosicion) {

                listaFinal.add(new NumeroPrimo(lista.get(i).getPosicion(), lista.get(i).getNumero()));

                contador++;
            }
        }

        if (listaFinal.isEmpty()) {
            System.out.println("Ningún número cumple los requisitos");
            System.out.println("\n===========================\n");
        } else {
            System.out.println("Números invertidos iguales cuyas posiciones " +
                    "invertidas también son iguales: " + contador + " números de " + lista.size() + ".\n");
            visualizarLista(listaFinal);
            System.out.println("\n===========================\n");
        }
        return listaFinal;
    }

    //Un método final que llama a todos los anteriores
    public static void metodoSheldon(int numero) {

        numeroPrimoDeSheldon(listaPosicionesInvertidas(listaPrimosInvertidos(listaNumeros(numero))));

    }
}