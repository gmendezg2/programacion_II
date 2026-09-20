package ejercicio9;

import java.util.ArrayList;
import java.util.Scanner;

class Empleado {

    String nombre;
    double salario;

    public Empleado(String nombre, double salario) {

        this.nombre = nombre;
        this.salario = salario;
    }
}

public class Ejercicio9 {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        ArrayList<Empleado> empleados = new ArrayList<>();

        System.out.print(
                "Cuantos empleados desea ingresar: "
        );

        int cantidad = leer.nextInt();

        leer.nextLine();

        double sumaSalarios = 0;

        for (int i = 0; i < cantidad; i++) {

            System.out.println(
                    "\nEmpleado " + (i + 1)
            );

            System.out.print("Ingrese nombre: ");

            String nombre = leer.nextLine();

            System.out.print("Ingrese salario: ");

            double salario = leer.nextDouble();

            leer.nextLine();

            Empleado empleado =
                    new Empleado(
                            nombre,
                            salario
                    );

            empleados.add(empleado);

            sumaSalarios =
                    sumaSalarios + salario;
        }

        System.out.println(
                "\n LISTA DE EMPLEADOS "
        );

        for (Empleado empleado : empleados) {

            System.out.println(
                    "Nombre: "
                    + empleado.nombre
                    + " - Salario: Q"
                    + empleado.salario
            );
        }

        double promedio =
                sumaSalarios / empleados.size();

        System.out.println(
                "\nPromedio de salarios: Q"
                + promedio
        );
    }
}
