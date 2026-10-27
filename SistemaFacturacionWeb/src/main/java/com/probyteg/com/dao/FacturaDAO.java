package com.probyteg.com.dao;

import com.probyteg.com.modelo.DetalleFactura;
import com.probyteg.com.modelo.Factura;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public int siguienteIdFactura(Connection cn) throws SQLException {
        String sql = "SELECT COALESCE(MAX(idfactura), 0) + 1 FROM tbl_facturacion";
        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 1;
        }
    }

    public void insertarFactura(Factura factura) throws SQLException {
        try (Connection cn = ConexionBD.obtenerConexion()) {
            cn.setAutoCommit(false);
            try {
                int idFactura = siguienteIdFactura(cn);
                factura.setIdFactura(idFactura);
                if (factura.getFechaHoraFactura() == null) {
                    factura.setFechaHoraFactura(new Timestamp(System.currentTimeMillis()));
                }
                if (factura.getEstadoFactura() == null || factura.getEstadoFactura().trim().isEmpty()) {
                    factura.setEstadoFactura("A");
                }

                String sqlFactura = "INSERT INTO tbl_facturacion "
                        + "(idfactura, seriefectura, numfactura, idcliente, fechahorafactura, estadofactura) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = cn.prepareStatement(sqlFactura)) {
                    ps.setInt(1, idFactura);
                    ps.setString(2, factura.getSerieFactura());
                    ps.setInt(3, factura.getNumFactura());
                    ps.setInt(4, factura.getIdCliente());
                    ps.setTimestamp(5, factura.getFechaHoraFactura());
                    ps.setString(6, factura.getEstadoFactura());
                    ps.executeUpdate();
                }

                String sqlDetalle = "INSERT INTO tbl_detalle_factura "
                        + "(iddetalle, idfactura, cantidad, idproducto, descripcionproducto, precioproducto) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                int idDetalle = siguienteIdDetalle(cn);
                for (DetalleFactura detalle : factura.getDetalles()) {
                    detalle.setIdDetalle(idDetalle);
                    detalle.setIdFactura(idFactura);
                    try (PreparedStatement ps = cn.prepareStatement(sqlDetalle)) {
                        ps.setInt(1, idDetalle);
                        ps.setInt(2, idFactura);
                        ps.setInt(3, detalle.getCantidad());
                        ps.setInt(4, detalle.getIdProducto());
                        ps.setString(5, detalle.getDescripcionProducto());
                        ps.setBigDecimal(6, detalle.getPrecioProducto());
                        ps.executeUpdate();
                    }
                    idDetalle++;
                }

                cn.commit();
            } catch (SQLException e) {
                cn.rollback();
                throw e;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }

    private int siguienteIdDetalle(Connection cn) throws SQLException {
        String sql = "SELECT COALESCE(MAX(iddetalle), 0) + 1 FROM tbl_detalle_factura";
        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 1;
        }
    }

    public List<Factura> listarFacturas(String serie, Integer numFactura, Integer idCliente, String estado)
            throws SQLException {
        StringBuilder sql = new StringBuilder(
                "SELECT f.idfactura, f.seriefectura, f.numfactura, f.idcliente, f.fechahorafactura, f.estadofactura, "
                + "(SELECT COALESCE(SUM(d.cantidad * d.precioproducto), 0) FROM tbl_detalle_factura d "
                + " WHERE d.idfactura = f.idfactura) AS total "
                + "FROM tbl_facturacion f WHERE 1 = 1");
        List<Object> parametros = new ArrayList<>();

        if (serie != null && !serie.trim().isEmpty()) {
            sql.append(" AND UPPER(seriefectura) LIKE ?");
            parametros.add("%" + serie.trim().toUpperCase() + "%");
        }
        if (numFactura != null) {
            sql.append(" AND numfactura = ?");
            parametros.add(numFactura);
        }
        if (idCliente != null) {
            sql.append(" AND idcliente = ?");
            parametros.add(idCliente);
        }
        if (estado != null && !estado.trim().isEmpty()) {
            sql.append(" AND estadofactura = ?");
            parametros.add(estado.trim());
        }
        sql.append(" ORDER BY idfactura DESC");

        List<Factura> facturas = new ArrayList<>();
        try (Connection cn = ConexionBD.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Factura f = new Factura();
                    f.setIdFactura(rs.getInt("idfactura"));
                    f.setSerieFactura(rs.getString("seriefectura"));
                    f.setNumFactura(rs.getInt("numfactura"));
                    f.setIdCliente(rs.getInt("idcliente"));
                    f.setFechaHoraFactura(rs.getTimestamp("fechahorafactura"));
                    f.setEstadoFactura(rs.getString("estadofactura"));
                    f.setTotal(rs.getBigDecimal("total"));
                    facturas.add(f);
                }
            }
        }
        return facturas;
    }

    public List<DetalleFactura> listarDetalles(int idFactura) throws SQLException {
        String sql = "SELECT iddetalle, idfactura, cantidad, idproducto, descripcionproducto, precioproducto "
                + "FROM tbl_detalle_factura WHERE idfactura = ? ORDER BY iddetalle";
        List<DetalleFactura> detalles = new ArrayList<>();
        try (Connection cn = ConexionBD.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idFactura);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleFactura d = new DetalleFactura();
                    d.setIdDetalle(rs.getInt("iddetalle"));
                    d.setIdFactura(rs.getInt("idfactura"));
                    d.setCantidad(rs.getInt("cantidad"));
                    d.setIdProducto(rs.getInt("idproducto"));
                    d.setDescripcionProducto(rs.getString("descripcionproducto"));
                    d.setPrecioProducto(rs.getBigDecimal("precioproducto"));
                    detalles.add(d);
                }
            }
        }
        return detalles;
    }
}
