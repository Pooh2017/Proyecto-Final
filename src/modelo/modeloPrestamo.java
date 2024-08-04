/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Vistas.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ederd
 */
public class modeloPrestamo {
     Login login=new Login();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    private int idPrestamo,idUsuario,idLibro;
    private String fechaPrestamo,fechaDevolucionEstimada;

    public modeloPrestamo() {
    }

    
    public modeloPrestamo(int idPrestamo, int idUsuario, int idLibro, String fechaPrestamo, String fechaDevolucionEstimada) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEstimada = fechaDevolucionEstimada;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucionEstimada() {
        return fechaDevolucionEstimada;
    }

    public void setFechaDevolucionEstimada(String fechaDevolucionEstimada) {
        this.fechaDevolucionEstimada = fechaDevolucionEstimada;
    }
    
    public boolean registrarPrestamo() {

     ps = null;
    boolean success = false;

    try {
        con=login.baseDatos.getConnection();
        con.setAutoCommit(false);  // Desactivar el auto-commit para manejar la transacción manualmente

        String sql = "INSERT INTO Prestamo (ID_Usuario, ID_Libro, Fecha_Prestamo, Fecha_Devolución_Estimada) VALUES (?, ?, ?, ?)";
        ps = con.prepareStatement(sql);


        // Establecer los valores en el PreparedStatement utilizando métodos get
        ps.setInt(1, getIdUsuario());
        ps.setInt(2, getIdLibro());
        ps.setString(3, getFechaPrestamo());
        ps.setString(4, getFechaDevolucionEstimada());

        // Ejecutar la inserción
        int rowsInserted = ps.executeUpdate();

        if (rowsInserted > 0) {
            success = true;
            con.commit();  // Confirmar la transacción si la inserción fue exitosa
        } else {
            con.rollback();  // Revertir la transacción en caso de fallo
        }
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            con.rollback();  // Revertir la transacción en caso de excepción
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } finally {
        if (ps != null) {
            try {
                ps.close();  // Asegurarse de cerrar el PreparedStatement
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            con.setAutoCommit(true);  // Volver al modo de auto-commit
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return success;
}
public int obtenerNumeroCopiasDisponibles(int idLibro) throws SQLException {
        String sql = "SELECT Número_Copias FROM Libro WHERE ID_Libro = ?";
        try (PreparedStatement ps = login.baseDatos.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idLibro);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Número_Copias");
                } else {
                    return 0;
                }
            }
        }
    }

    public void actualizarNumeroCopias(int idLibro, int nuevoNumeroCopias) throws SQLException {
        String sql = "UPDATE Libro SET Número_Copias = ? WHERE ID_Libro = ?";
        try (PreparedStatement ps = login.baseDatos.getConnection().prepareStatement(sql)) {
            ps.setInt(1, nuevoNumeroCopias);
            ps.setInt(2, idLibro);
            ps.executeUpdate();
        }catch (Exception e) {
        e.printStackTrace();
    } 
    }
}
