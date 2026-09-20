package ejercicio7;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio7 {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        ArrayList<Double> calificaciones = new ArrayList<>();

        System.out.print(
                "Cuantas calificaciones desea ingresar: "
        );

        int cantidad = leer.nextInt();

        double suma = 0;

        for (int i = 0; i < cantidad; i++) {

            System.out.print(
                    "Ingrese la calificacion "
                    + (i + 1) + ": "
            );

            double nota = leer.nextDouble();

            calificaciones.add(nota);

            suma = suma + nota;
        }

        double promedio =
                suma / calificaciones.size();

        System.out.println(
                "\nCalificaciones ingresadas:"
        );

        for (Double nota : calificaciones) {

            System.out.println(nota);
        }

        System.out.println(
                "\nPromedio: " + promedio
        );
    }
}
