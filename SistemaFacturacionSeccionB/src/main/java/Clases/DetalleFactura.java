
package Clases;


public class DetalleFactura {
    int idDetalle;
    String descripcionProducto;
    int cantidad;
    double precioUnitario;
    
    public DetalleFactura(int idDetalle, String descripcionProducto, int cantidad, double precioUnitario) {
        this.idDetalle = idDetalle;
        this.descripcionProducto = descripcionProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public double calcularSubtotal() {
        return cantidad * precioUnitario;
    }
    
    public void mostrarDetalle() {
        System.out.println("Producto: " + descripcionProducto + 
                           " | Cantidad: " + cantidad + 
                           " | Precio: Q" + precioUnitario + 
                           " | Subtotal: Q" + calcularSubtotal());
    }
    
}
