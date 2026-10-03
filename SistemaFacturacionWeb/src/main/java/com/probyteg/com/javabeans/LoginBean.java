package com.probyteg.com.javabeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String CONSULTA =
            "SELECT id_usuario, nombre_usuario, estado_usuario "
          + "FROM tbl_usuarios "
          + "WHERE nombre_usuario = ? AND pass_usuario = ?";

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() {
        String usuario = username == null ? "" : username.trim();
        String clave = password == null ? "" : password;

        if (usuario.isEmpty() || clave.isEmpty()) {
            mensajeError("Credenciales inválidas");
            return;
        }

        try {
            if (autenticar(usuario, clave)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario));
            } else {
                mensajeError("Credenciales inválidas o usuario inactivo");
            }
        } catch (SQLException e) {
            mensajeError("No se pudo conectar con la base de datos");
        }
    }

 private boolean autenticar(String usuario, String clave) throws SQLException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String url = ctx.getExternalContext().getInitParameter("db.url");
        String user = ctx.getExternalContext().getInitParameter("db.user");
        String pass = ctx.getExternalContext().getInitParameter("db.password");

        try (Connection cn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = cn.prepareStatement(CONSULTA)) {

            ps.setString(1, usuario);
            ps.setString(2, clave);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && estaActivo(rs.getString("estado_usuario"));
            }
        }
    }

    private boolean estaActivo(String estado) {
        if (estado == null) {
            return false;
        }
        String e = estado.trim();
        return "1".equals(e) || "t".equalsIgnoreCase(e)
                || "true".equalsIgnoreCase(e) || "s".equalsIgnoreCase(e);
    }

    private void mensajeError(String detalle) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", detalle));
    }
}
