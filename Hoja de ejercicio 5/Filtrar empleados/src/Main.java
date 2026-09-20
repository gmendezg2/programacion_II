import java.util.ArrayList;

class Empleado {
    String nombre;
    String departamento;
    double salario;

    public Empleado(String nombre, String departamento, double salario) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    public void mostrar() {
        System.out.println(nombre + " - " + departamento + " - Q" + salario);
    }
}

public class Main {

    public static ArrayList<Empleado> filtrarEmpleados(
            ArrayList<Empleado> empleados, String departamento) {

        ArrayList<Empleado> resultado = new ArrayList<>();

        for (Empleado e : empleados) {

            if (e.departamento.equalsIgnoreCase(departamento)
                    && e.salario > 5000) {

                resultado.add(e);
            }
        }

        return resultado;
    }

    public static void main(String[] args) {

        ArrayList<Empleado> empleados = new ArrayList<>();

        empleados.add(new Empleado("Carlos", "Sistemas", 6000));
        empleados.add(new Empleado("Ana", "Sistemas", 4500));
        empleados.add(new Empleado("Luis", "Contabilidad", 7000));
        empleados.add(new Empleado("Mario", "Sistemas", 8000));

        ArrayList<Empleado> resultado =
                filtrarEmpleados(empleados, "Sistemas");

        for (Empleado e : resultado) {
            e.mostrar();
        }
    }
}