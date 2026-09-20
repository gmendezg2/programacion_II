import java.util.ArrayList;

public class Ejercicio6 {

    public static double calcularPromedio(ArrayList<Double> ventas) {
        double suma = 0;

        for (double venta : ventas) {
            suma += venta;
        }

        return suma / ventas.size();
    }

    public static double calcularMaximo(ArrayList<Double> ventas) {
        double maximo = ventas.get(0);

        for (double venta : ventas) {
            if (venta > maximo) {
                maximo = venta;
            }
        }

        return maximo;
    }

    public static double calcularMinimo(ArrayList<Double> ventas) {
        double minimo = ventas.get(0);

        for (double venta : ventas) {
            if (venta < minimo) {
                minimo = venta;
            }
        }

        return minimo;
    }

    public static int diasSobrePromedio(ArrayList<Double> ventas) {
        double promedio = calcularPromedio(ventas);
        int cantidad = 0;

        for (double venta : ventas) {
            if (venta > promedio) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public static void main(String[] args) {

        ArrayList<Double> ventas = new ArrayList<>();

        ventas.add(1500.0);
        ventas.add(2200.0);
        ventas.add(1800.0);
        ventas.add(3000.0);
        ventas.add(1200.0);
        ventas.add(2500.0);
        ventas.add(1700.0);
        ventas.add(2800.0);

        System.out.println("Promedio mensual: Q" + calcularPromedio(ventas));
        System.out.println("Venta maxima: Q" + calcularMaximo(ventas));
        System.out.println("Venta minima: Q" + calcularMinimo(ventas));
        System.out.println("Dias con ventas superiores al promedio: "
                + diasSobrePromedio(ventas));
    }
}