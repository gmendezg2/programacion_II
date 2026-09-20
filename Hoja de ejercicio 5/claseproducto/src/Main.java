import java.util.ArrayList;

class Producto {
    int id;
    String nombre;
    double precio;
    int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public void mostrar() {
        System.out.println(id + " - " + nombre +
                " - Q" + precio + " - Stock: " + stock);
    }
}

public class Main {

    public static void aumentarPrecio(ArrayList<Producto> productos, double porcentaje) {

        for (Producto p : productos) {

            if (p.stock < 10) {
                p.precio = p.precio + (p.precio * porcentaje / 100);
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Producto> productos = new ArrayList<>();

        productos.add(new Producto(1, "Teclado", 150, 5));
        productos.add(new Producto(2, "Mouse", 100, 15));
        productos.add(new Producto(3, "Monitor", 1200, 7));

        aumentarPrecio(productos, 10);

        for (Producto p : productos) {
            p.mostrar();
        }
    }
}