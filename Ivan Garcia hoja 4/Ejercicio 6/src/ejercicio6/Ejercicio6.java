package ejercicio6;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio6 {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        ArrayList<String> tareas = new ArrayList<>();

        int opcion;

        do {

            System.out.println("\n CONTROL DE ACTIVIDADES ");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Mostrar tareas");
            System.out.println("3. Marcar como completada");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opcion: ");

            opcion = leer.nextInt();

            leer.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Ingrese la tarea: ");

                    String tarea = leer.nextLine();

                    tareas.add("[Pendiente] " + tarea);

                    System.out.println("Tarea agregada.");

                    break;

                case 2:

                    System.out.println("\nLista de tareas:");

                    for (int i = 0; i < tareas.size(); i++) {

                        System.out.println(
                                i + " - " + tareas.get(i)
                        );
                    }

                    break;

                case 3:

                    System.out.print(
                            "Ingrese el indice de la tarea: "
                    );

                    int completar = leer.nextInt();

                    if (completar >= 0 &&
                            completar < tareas.size()) {

                        String tareaActual =
                                tareas.get(completar);

                        tareaActual =
                                tareaActual.replace(
                                        "[Pendiente]",
                                        "[Completada]"
                                );

                        tareas.set(
                                completar,
                                tareaActual
                        );

                        System.out.println(
                                "Tarea completada."
                        );

                    } else {

                        System.out.println(
                                "Indice no valido."
                        );
                    }

                    break;

                case 4:

                    System.out.print(
                            "Ingrese el indice de la tarea a eliminar: "
                    );

                    int eliminar = leer.nextInt();

                    if (eliminar >= 0 &&
                            eliminar < tareas.size()) {

                        tareas.remove(eliminar);

                        System.out.println(
                                "Tarea eliminada."
                        );

                    } else {

                        System.out.println(
                                "Indice no valido."
                        );
                    }

                    break;

                case 5:

                    System.out.println(
                            "Programa finalizado."
                    );

                    break;

                default:

                    System.out.println(
                            "Opcion no valida."
                    );
            }

        } while (opcion != 5);
    }
}
