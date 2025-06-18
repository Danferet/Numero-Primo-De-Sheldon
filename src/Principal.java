import javax.swing.*;

//Clase principal/Main del programa. Al usuario le aparecerá una ventana para introducir un número entero positivo.
//El programa evaluará si se ha hecho un input correcto.
//Seguidamente, nos dirá cuantos números primos hay hasta el número elegido y cuantos van cumpliendo las características
//del número primo de Sheldon.
public class Principal {
    public static void main(String[] args) {

        //Añado un bucle con una salida boolean para que el usuario pueda introducir un número por una ventana
        boolean inputCorrecto = false;

        //Mientras no introduzca algo que no sea un numero entero positivo, el bucle se seguirá ejecutando
        while(!inputCorrecto) {
            try {

                String input = JOptionPane.showInputDialog("Introduce un numero entero positivo.");
                //Paso el número de String a integer; si no se puede parsear,
                //lanzará NumberFormatException y reiniciará el bucle
                int numero = Integer.parseInt(input);

                //Evalúo si el número es positivo, y si no es así, lanzará la excepción creada para este caso
                //NumeroValidoException.
                if (numero < 0) {
                    throw new NumeroValidoException("Debes introducir un número entero positivo");
                }

                //Si ninguna excepción se lanzó, se realizará el programa propiamente dicho.
                Validaciones.metodoSheldon(numero);
                //Y paso la variable de salida a true para que el bucle termine y nos dé la información.
                inputCorrecto = true;

            //Estas son las dos excepciones que se pueden lanzar en este bucle.
            } catch (NumeroValidoException | NumberFormatException nve) {
                JOptionPane.showMessageDialog(null, "Debes introducir un número entero positivo.");
            }
        }
    }
}