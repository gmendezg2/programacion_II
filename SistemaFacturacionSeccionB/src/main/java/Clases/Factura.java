
package Clases;

import java.util.ArrayList;


public class Factura {
    int IdFactura;
    Cliente Clientes;
    String FechaFactura;
    boolean Anulada = false;
    ArrayList<DetalleFactura> DetalleFactura;

    public Factura(int IdFactura, Cliente Clientes, String FechaFactura) {
        this.IdFactura = IdFactura;
        this.Clientes = Clientes;
        this.FechaFactura = FechaFactura;
        this.DetalleFactura = new ArrayList<>();
    }
    //Crear detalle factura
    public void CrearFacturaDetalle(DetalleFactura detalle){
        DetalleFactura.add(detalle);
     
    }
    //Método anular factura
    public void AnulaFactura(){
    this.Anulada = true;
        System.out.println("La Factura: "+IdFactura +" fue anulada con éxito");
    }
    
   
    public void consultarFactura() {
        System.out.println("Factura #" + IdFactura + " | Fecha: " + FechaFactura);
        System.out.println("Cliente: " + Clientes.getNombreCliente() + " " + Clientes.getApellidoCliente());
        System.out.println("Estado: " + (Anulada ? "ANULADA" : "ACTIVA"));
        System.out.println("Detalles:");
        double total = 0;
        for (DetalleFactura d : DetalleFactura) {
            d.mostrarDetalle();
            total += d.calcularSubtotal();
        }
        System.out.println("TOTAL: Q" + total);
    }
    
}
