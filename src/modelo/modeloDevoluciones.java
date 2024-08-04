/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Funciones.Utilerias;
import Vistas.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author ederd
 */
public class modeloDevoluciones {
     Login login=new Login();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    private int idP,idU,IdL;
    private String nombre,apellidoP,apellidoM,titulo,fechaP,fechaD;

    public modeloDevoluciones() {
    }
    

    public modeloDevoluciones(int idP, int idU, int IdL, String nombre, String apellidoP, String apellidoM, String titulo, String fechaP, String fechaD) {
        this.idP = idP;
        this.idU = idU;
        this.IdL = IdL;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.titulo = titulo;
        this.fechaP = fechaP;
        this.fechaD = fechaD;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdL() {
        return IdL;
    }

    public void setIdL(int IdL) {
        this.IdL = IdL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaP() {
        return fechaP;
    }

    public void setFechaP(String fechaP) {
        this.fechaP = fechaP;
    }

    public String getFechaD() {
        return fechaD;
    }

    public void setFechaD(String fechaD) {
        this.fechaD = fechaD;
    }
    
     public List<modeloDevoluciones> listaPrestamos() {
                 con = login.baseDatos.getConnection();
    List<modeloDevoluciones> datosPrestamos = new ArrayList<>();
    String sql = "SELECT " +
                 "p.ID_Prestamo AS ID_Prestamo, " +
                 "u.ID_Usuario AS ID_Usuario, " +
                 "u.Nombre AS Nombre, " +
                 "u.Apellido_Paterno AS Apellido_Paterno, " +
                 "u.Apellido_Materno AS Apellido_Materno, " +
                 "l.ID_Libro AS ID_Libro, " +
                 "l.Título AS Título_Libro, " +
                 "p.Fecha_Prestamo AS Fecha_Prestamo, " +
                 "p.Fecha_Devolución_Estimada AS Fecha_Devolución " +
                 "FROM Prestamo p " +
                 "INNER JOIN Usuario u ON p.ID_Usuario = u.ID_Usuario " +
                 "INNER JOIN Libro l ON p.ID_Libro = l.ID_Libro";

    try {

        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            modeloDevoluciones p = new modeloDevoluciones();
            p.setIdP(rs.getInt(1));
            p.setIdU(rs.getInt(2));
            p.setNombre(rs.getString(3));
            p.setApellidoP(rs.getString(4));
            p.setApellidoM(rs.getString(5));
            p.setIdL(rs.getInt(6));
            p.setTitulo(rs.getString(7));
            p.setFechaP(rs.getString(8));
            p.setFechaD(rs.getString(9));
            datosPrestamos.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } 
    return datosPrestamos;
}
     
     public boolean eliminarPrestamo(int idPrestamo) {
    String sql = "DELETE FROM Prestamo WHERE ID_Prestamo = ?";
    boolean eliminado = false;
    Connection con = null;
    PreparedStatement ps = null;
    try {
        con = login.baseDatos.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idPrestamo);
        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            eliminado = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return eliminado;
}
     
        public void actualizarNumeroCopias(int idLibro, int nuevoNumeroCopias) throws SQLException {
        String sql = "UPDATE Libro SET Número_Copias = ? WHERE ID_Libro = ?";
        try  {
            PreparedStatement ps = login.baseDatos.getConnection().prepareStatement(sql);
            ps.setInt(1, nuevoNumeroCopias);
            ps.setInt(2, idLibro);
            ps.executeUpdate();
        }catch (Exception e) {
        e.printStackTrace();
    } 
    }
    
        public int obtenerNumeroCopias(int idLibro) {
    String sql = "SELECT Número_Copias FROM Libro WHERE ID_Libro = ?";
    int numeroCopias = 0;

    try  {
         con = login.baseDatos.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idLibro);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                numeroCopias = rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return numeroCopias;
}


       
}
