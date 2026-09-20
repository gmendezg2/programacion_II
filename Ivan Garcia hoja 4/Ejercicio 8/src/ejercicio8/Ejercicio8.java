package ejercicio8;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio8 {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        ArrayList<Integer> numeros = new ArrayList<>();

        System.out.print(
                "Cuantos numeros desea ingresar: "
        );

        int cantidad = leer.nextInt();

        for (int i = 0; i < cantidad; i++) {

            System.out.print(
                    "Ingrese el numero "
                    + (i + 1) + ": "
            );

            numeros.add(leer.nextInt());
        }

        System.out.println("\nNumeros pares:");

        for (Integer numero : numeros) {

            if (numero % 2 == 0) {

                System.out.println(numero);
            }
        }
    }
}
