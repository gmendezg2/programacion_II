/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemafacturacionseccionb;

import Clases.Cliente;
import Clases.DetalleFactura;
import Clases.Factura;

/**
 *
 * @author gmendez
 */
public class SistemaFacturacionSeccionB {

    public static void main(String[] args) {
        Cliente c1 = new Cliente(1, "1234567-8", "Juan", "Pérez", "Zona 1", "5555-1234", "juan@mail.com", "Activo");

        Factura f1 = new Factura(1001, c1, "18/08/2026");

        // CrearFactura (agregar detalles)
        f1.CrearFacturaDetalle(new DetalleFactura(1, "Laptop Lenovo", 1, 5000));
        f1.CrearFacturaDetalle(new DetalleFactura(2, "Mouse Logitech", 2, 150));

        // ConsultarFactura
        f1.consultarFactura();

        // AnularFactura
        f1.AnulaFactura();

        // ConsultarFactura nuevamente
        f1.consultarFactura();
    
    }
}
