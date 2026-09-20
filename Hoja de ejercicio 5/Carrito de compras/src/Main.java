import java.util.ArrayList;

class ItemCarrito {

    String producto;
    int cantidad;
    double precioUnitario;

    public ItemCarrito(String producto, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }
}

class CarritoCompras {

    ArrayList<ItemCarrito> items = new ArrayList<>();

    public void agregarItem(String producto, int cantidad, double precio) {

        for (ItemCarrito item : items) {

            if (item.producto.equalsIgnoreCase(producto)) {

                item.cantidad += cantidad;
                return;
            }
        }

        items.add(new ItemCarrito(producto, cantidad, precio));
    }

    public void eliminarItem(String producto) {

        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).producto.equalsIgnoreCase(producto)) {

                items.remove(i);
                return;
            }
        }
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public double calcularTotal() {

        double total = 0;

        for (ItemCarrito item : items) {
            total += item.calcularSubtotal();
        }

        return total;
    }

    public void mostrarCarrito() {

        for (ItemCarrito item : items) {

            System.out.println(
                    item.producto +
                    " - Cantidad: " + item.cantidad +
                    " - Precio: Q" + item.precioUnitario +
                    " - Subtotal: Q" + item.calcularSubtotal()
            );
        }
    }
}

public class Main {

    public static void main(String[] args) {

        CarritoCompras carrito = new CarritoCompras();

        carrito.agregarItem("Mouse", 2, 100);
        carrito.agregarItem("Teclado", 1, 200);

        // El Mouse ya existe, por lo tanto aumenta su cantidad
        carrito.agregarItem("Mouse", 3, 100);

        carrito.mostrarCarrito();

        System.out.println("Total: Q" + carrito.calcularTotal());
    }
}