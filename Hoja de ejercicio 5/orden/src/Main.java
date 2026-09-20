import java.util.ArrayList;

public class Main {

    public static ArrayList<Integer> fusionar(
            ArrayList<Integer> lista1,
            ArrayList<Integer> lista2) {

        ArrayList<Integer> resultado = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < lista1.size() && j < lista2.size()) {

            if (lista1.get(i) <= lista2.get(j)) {

                resultado.add(lista1.get(i));
                i++;

            } else {

                resultado.add(lista2.get(j));
                j++;
            }
        }

        while (i < lista1.size()) {

            resultado.add(lista1.get(i));
            i++;
        }

        while (j < lista2.size()) {

            resultado.add(lista2.get(j));
            j++;
        }

        return resultado;
    }

    public static void main(String[] args) {

        ArrayList<Integer> lista1 = new ArrayList<>();
        ArrayList<Integer> lista2 = new ArrayList<>();

        lista1.add(1);
        lista1.add(3);
        lista1.add(5);
        lista1.add(8);

        lista2.add(2);
        lista2.add(4);
        lista2.add(6);
        lista2.add(9);

        ArrayList<Integer> resultado = fusionar(lista1, lista2);

        System.out.println(resultado);
    }
}