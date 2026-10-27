package com.probyteg.com.javabeans;

import com.probyteg.com.dao.FacturaDAO;
import com.probyteg.com.modelo.DetalleFactura;
import com.probyteg.com.modelo.Factura;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FacturaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(FacturaBean.class.getName());

    private final FacturaDAO facturaDAO = new FacturaDAO();

    // Datos de la factura a crear
    private String serieFactura;
    private int numFactura;
    private int idCliente;

    // Detalle en edición (agregar línea)
    private int idProducto;
    private String descripcionProducto;
    private int cantidad;
    private BigDecimal precioProducto;
    private List<DetalleFactura> detalles = new ArrayList<>();

    // Filtros de consulta
    private String filtroSerie;
    private Integer filtroNumFactura;
    private Integer filtroIdCliente;
    private String filtroEstado;
    private List<Factura> facturas = new ArrayList<>();
    private List<DetalleFactura> detallesEnConsulta = new ArrayList<>();

    public FacturaBean() {
    }

    @PostConstruct
    public void init() {
        cargarFacturas();
    }

    // ---- Crear factura ----

    public void agregarDetalle() {
        if (idProducto <= 0 || descripcionProducto == null || descripcionProducto.trim().isEmpty()
                || cantidad <= 0 || precioProducto == null || precioProducto.compareTo(BigDecimal.ZERO) <= 0) {
            mensajeError("Complete todos los campos del detalle: producto, descripción, cantidad y precio válidos.");
            return;
        }
        DetalleFactura d = new DetalleFactura();
        d.setIdProducto(idProducto);
        d.setDescripcionProducto(descripcionProducto.trim());
        d.setCantidad(cantidad);
        d.setPrecioProducto(precioProducto);
        detalles.add(d);
        limpiarDetalle();
        mensajeInfo("Detalle agregado. Subtotal de la línea: Q" + d.getSubtotal());
    }

    public void eliminarDetalle(DetalleFactura detalle) {
        detalles.remove(detalle);
    }

    public String guardarFactura() {
        if (serieFactura == null || serieFactura.trim().isEmpty()) {
            mensajeError("La serie de la factura es obligatoria.");
            return null;
        }
        if (numFactura <= 0) {
            mensajeError("El número de factura debe ser mayor a cero.");
            return null;
        }
        if (idCliente <= 0) {
            mensajeError("El identificador del cliente debe ser mayor a cero.");
            return null;
        }
        if (detalles.isEmpty()) {
            mensajeError("La factura debe tener al menos un detalle.");
            return null;
        }

        Factura factura = new Factura();
        factura.setSerieFactura(serieFactura.trim());
        factura.setNumFactura(numFactura);
        factura.setIdCliente(idCliente);
        factura.setDetalles(new ArrayList<>(detalles));

        try {
            facturaDAO.insertarFactura(factura);
            mensajeInfo("Factura " + factura.getSerieFactura() + "-" + factura.getNumFactura()
                    + " registrada correctamente. Total: Q" + factura.getTotal());
            limpiarFactura();
            cargarFacturas();
            return null;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar la factura", e);
            mensajeError("No se pudo guardar la factura: " + e.getMessage());
            return null;
        }
    }

    private void limpiarFactura() {
        serieFactura = null;
        numFactura = 0;
        idCliente = 0;
        detalles = new ArrayList<>();
        limpiarDetalle();
    }

    private void limpiarDetalle() {
        idProducto = 0;
        descripcionProducto = null;
        cantidad = 0;
        precioProducto = null;
    }

    // ---- Consultar facturas ----

    public void buscar() {
        try {
            facturas = facturaDAO.listarFacturas(filtroSerie, filtroNumFactura, filtroIdCliente, filtroEstado);
            if (facturas.isEmpty()) {
                mensajeInfo("No se encontraron facturas con los filtros indicados.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al consultar facturas", e);
            facturas = new ArrayList<>();
            mensajeError("No se pudo consultar las facturas: " + e.getMessage());
        }
    }

    public void cargarFacturas() {
        try {
            facturas = facturaDAO.listarFacturas(null, null, null, null);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al cargar las facturas", e);
            facturas = new ArrayList<>();
            mensajeError("No se pudo cargar el listado de facturas: " + e.getMessage());
        }
    }

    public void cargarDetalles(Factura factura) {
        try {
            detallesEnConsulta = facturaDAO.listarDetalles(factura.getIdFactura());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al cargar los detalles de la factura", e);
            detallesEnConsulta = new ArrayList<>();
            mensajeError("No se pudieron cargar los detalles: " + e.getMessage());
        }
    }

    public List<DetalleFactura> getDetallesEnConsulta() {
        return detallesEnConsulta;
    }

    public void setDetallesEnConsulta(List<DetalleFactura> detallesEnConsulta) {
        this.detallesEnConsulta = detallesEnConsulta;
    }

    public void limpiarFiltros() {
        filtroSerie = null;
        filtroNumFactura = null;
        filtroIdCliente = null;
        filtroEstado = null;
        cargarFacturas();
    }

    // ---- Mensajes ----

    private void mensajeError(String detalle) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detalle));
    }

    private void mensajeInfo(String detalle) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", detalle));
    }

    // ---- Getters y Setters ----

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getTotalDetalles() {
        BigDecimal total = BigDecimal.ZERO;
        for (DetalleFactura d : detalles) {
            total = total.add(d.getSubtotal());
        }
        return total;
    }

    public String getFiltroSerie() {
        return filtroSerie;
    }

    public void setFiltroSerie(String filtroSerie) {
        this.filtroSerie = filtroSerie;
    }

    public Integer getFiltroNumFactura() {
        return filtroNumFactura;
    }

    public void setFiltroNumFactura(Integer filtroNumFactura) {
        this.filtroNumFactura = filtroNumFactura;
    }

    public Integer getFiltroIdCliente() {
        return filtroIdCliente;
    }

    public void setFiltroIdCliente(Integer filtroIdCliente) {
        this.filtroIdCliente = filtroIdCliente;
    }

    public String getFiltroEstado() {
        return filtroEstado;
    }

    public void setFiltroEstado(String filtroEstado) {
        this.filtroEstado = filtroEstado;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
}
