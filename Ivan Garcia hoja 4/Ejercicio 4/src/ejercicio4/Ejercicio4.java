package ejercicio4;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio4 {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        ArrayList<String> elementos = new ArrayList<>();

        int opcion;

        do {

            System.out.println("\n--- MENU ---");
            System.out.println("1. Agregar elemento");
            System.out.println("2. Mostrar elementos");
            System.out.println("3. Eliminar elemento");
            System.out.println("4. Salir");

            System.out.print("Seleccione una opcion: ");
            opcion = leer.nextInt();

            leer.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Ingrese un elemento: ");
                    String elemento = leer.nextLine();

                    elementos.add(elemento);

                    System.out.println("Elemento agregado.");

                    break;

                case 2:

                    System.out.println("\nElementos:");

                    for (int i = 0; i < elementos.size(); i++) {

                        System.out.println(
                                i + " - " + elementos.get(i)
                        );
                    }

                    break;

                case 3:

                    System.out.print(
                            "Ingrese el indice que desea eliminar: "
                    );

                    int indice = leer.nextInt();

                    if (indice >= 0 && indice < elementos.size()) {

                        elementos.remove(indice);

                        System.out.println("Elemento eliminado.");

                    } else {

                        System.out.println("Indice no valido.");
                    }

                    break;

                case 4:

                    System.out.println("Programa finalizado.");

                    break;

                default:

                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 4);
    }
}
