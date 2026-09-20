import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ArrayList<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            System.out.print("Ingrese el numero " + (i + 1) + ": ");
            numeros.add(leer.nextInt());
        }

        System.out.println("\nNumeros ingresados:");

        for (Integer numero : numeros) {
            System.out.println(numero);
        }
    }
}
