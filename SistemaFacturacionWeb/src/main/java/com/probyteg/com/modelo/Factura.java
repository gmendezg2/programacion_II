package com.probyteg.com.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idFactura;
    private String serieFactura;
    private int numFactura;
    private int idCliente;
    private Timestamp fechaHoraFactura;
    private String estadoFactura;
    private List<DetalleFactura> detalles;
    private BigDecimal total;

    public Factura() {
        this.detalles = new ArrayList<>();
    }

    public BigDecimal getTotal() {
        if (total != null) {
            return total;
        }
        BigDecimal suma = BigDecimal.ZERO;
        for (DetalleFactura d : detalles) {
            suma = suma.add(d.getSubtotal());
        }
        return suma;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean estaAnulada() {
        return estadoFactura != null && "N".equalsIgnoreCase(estadoFactura.trim());
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getSerieFactura() {
        return serieFactura;
    }

    public void setSerieFactura(String serieFactura) {
        this.serieFactura = serieFactura;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Timestamp getFechaHoraFactura() {
        return fechaHoraFactura;
    }

    public void setFechaHoraFactura(Timestamp fechaHoraFactura) {
        this.fechaHoraFactura = fechaHoraFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
