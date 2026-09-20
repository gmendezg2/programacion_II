package ejercicio3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        ArrayList<String> nombres = new ArrayList<>();

        System.out.print("Cuantos nombres desea ingresar: ");
        int cantidad = leer.nextInt();

        leer.nextLine();

        for (int i = 0; i < cantidad; i++) {

            System.out.print("Ingrese el nombre " + (i + 1) + ": ");

            nombres.add(leer.nextLine());
        }

        Collections.sort(nombres);

        System.out.println("\nNombres ordenados alfabeticamente:");

        for (String nombre : nombres) {
            System.out.println(nombre);
        }
    }
}

