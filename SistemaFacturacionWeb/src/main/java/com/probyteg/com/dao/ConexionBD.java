package com.probyteg.com.dao;

import jakarta.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static Connection obtenerConexion() throws SQLException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String url = ctx.getExternalContext().getInitParameter("db.url");
        String user = ctx.getExternalContext().getInitParameter("db.user");
        String pass = ctx.getExternalContext().getInitParameter("db.password");
        return DriverManager.getConnection(url, user, pass);
    }
}
