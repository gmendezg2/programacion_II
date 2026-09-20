package ejercicio5;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio5 {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        ArrayList<String> nombres = new ArrayList<>();

        nombres.add("Carlos");
        nombres.add("Maria");
        nombres.add("Pedro");
        nombres.add("Ana");
        nombres.add("Luis");

        System.out.println("Lista de nombres:");

        for (String nombre : nombres) {
            System.out.println(nombre);
        }

        System.out.print("\nIngrese el nombre que desea buscar: ");

        String buscar = leer.nextLine();

        if (nombres.contains(buscar)) {

            System.out.println(
                    "El nombre existe en la lista."
            );

        } else {

            System.out.println(
                    "El nombre no existe en la lista."
            );
        }
    }
}

