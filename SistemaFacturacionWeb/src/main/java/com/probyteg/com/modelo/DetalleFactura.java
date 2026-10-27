package com.probyteg.com.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class DetalleFactura implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idDetalle;
    private int idFactura;
    private int cantidad;
    private int idProducto;
    private String descripcionProducto;
    private BigDecimal precioProducto;

    public DetalleFactura() {
    }

    public BigDecimal getSubtotal() {
        if (precioProducto == null) {
            return BigDecimal.ZERO;
        }
        return precioProducto.multiply(BigDecimal.valueOf(cantidad));
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }
}
