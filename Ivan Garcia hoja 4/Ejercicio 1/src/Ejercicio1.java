 import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ArrayList<String> elementos = new ArrayList<>();

        String dato;

        System.out.println("Ingrese elementos. Escriba 'fin' para terminar:");

        while (true) {
            System.out.print("Elemento: ");
            dato = leer.nextLine();

            if (dato.equalsIgnoreCase("fin")) {
                break;
            }

            elementos.add(dato);
        }

        System.out.println("\nElementos ingresados:");

        for (String elemento : elementos) {
            System.out.println(elemento);
        }
    }
}
