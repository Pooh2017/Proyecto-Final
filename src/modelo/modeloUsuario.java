/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Vistas.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ederd
 */
public class modeloUsuario {
    Login login=new Login();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    DefaultTableModel tablad=new DefaultTableModel();
    private int id;
    private String nombre,apellidoP,apellidoM,fechaNacimiento,telefono,domicilio;
    private String nombreUsuario,correo,contraseña,tipoUsuario;

    public modeloUsuario() {
    }

    public modeloUsuario(String nombre, String apellidoP, String apellidoM, String fechaNacimiento, String telefono, String domicilio, String nombreUsuario, String correo, String contraseña, String tipoUsuario) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
      public boolean existeUsuario(){
       con=login.baseDatos.getConnection();
       ps = null;
       rs = null;
       try{
       String sql = "SELECT * FROM usuario WHERE Nombre_Usuario = ?";
       ps = con.prepareStatement(sql);
       ps.setString(1, getNombreUsuario());
       rs=ps.executeQuery();
       if(rs.next()){
           return true;
       }else{
           return false;
       }
       }catch (Exception e) {
            e.printStackTrace();
            login.baseDatos.disconnect();
            return false;
        }
    }
    
    public boolean existeCorreo(){
       con=login.baseDatos.getConnection();
       ps = null;
       rs = null;
       try{
       String sql = "SELECT * FROM usuario WHERE Correo = ?";
       ps = con.prepareStatement(sql);
       ps.setString(1, getCorreo());
       rs=ps.executeQuery();
       if(rs.next()){
           return true;
       }else{
           return false;
       }
       }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List listaUsuario(){
        List<modeloUsuario>datosUsuarios=new ArrayList();
        String sql="SELECT * FROM usuario";
        try{
             con=login.baseDatos.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                modeloUsuario p=new modeloUsuario();
                p.setId(rs.getInt(1));
                p.setNombreUsuario(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setApellidoP(rs.getString(4));
                p.setApellidoM(rs.getString(5));
                p.setFechaNacimiento(rs.getString(6));
                p.setContraseña(rs.getString(7));
                p.setTelefono(rs.getString(8));
                p.setCorreo(rs.getString(9));
                p.setDomicilio(rs.getString(10));
                p.setTipoUsuario(rs.getString(11));
                datosUsuarios.add(p);    
            }
        }catch(Exception e){
                     e.printStackTrace();
        }
        return datosUsuarios;
    }
    
    
    public boolean registrarUsuario() {
    ps = null;
    boolean success = false;
    try {
        con.setAutoCommit(false);
        String sql = "INSERT INTO Usuario (Nombre_Usuario, Nombre, Apellido_Paterno, Apellido_Materno, Fecha_Nacimiento, Contraseña, Teléfono, Correo, Domicilio, Tipo_Usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ps = con.prepareStatement(sql);
        ps.setString(1, getNombreUsuario());
        ps.setString(2, getNombre());
        ps.setString(3, getApellidoP());
        ps.setString(4, getApellidoM());
        ps.setString(5, getFechaNacimiento());
        ps.setString(6, getContraseña());
        ps.setString(7, getTelefono());
        ps.setString(8, getCorreo());
        ps.setString(9, getDomicilio());
        ps.setString(10, getTipoUsuario());
        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
                success = true;
                con.commit(); // Confirmar la transacción
            } else {
                con.rollback(); // Revertir la transacción en caso de error
            }
    } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback(); // Revertir la transacción en caso de excepción
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                con.setAutoCommit(true); // Volver al modo de auto-commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
}
    
public boolean actualizarUsuario(int id) {
    PreparedStatement ps = null;
    boolean success = false;

    try {
        con.setAutoCommit(false);  // Desactivar el auto-commit para manejar la transacción manualmente

        String sql = "UPDATE Usuario SET Nombre_Usuario = ?, Nombre = ?, Apellido_Paterno = ?, Apellido_Materno = ?, Fecha_Nacimiento = ?, Contraseña = ?, Teléfono = ?, Correo = ?, Domicilio = ?, Tipo_Usuario = ? WHERE ID_Usuario = ?";
        ps = con.prepareStatement(sql);

        // Establecer los valores en el PreparedStatement
        ps.setString(1, getNombreUsuario());
        ps.setString(2, getNombre());
        ps.setString(3, getApellidoP());
        ps.setString(4, getApellidoM());
        ps.setString(5, getFechaNacimiento());
        ps.setString(6, getContraseña());
        ps.setString(7, getTelefono());
        ps.setString(8, getCorreo());
        ps.setString(9, getDomicilio());
        ps.setString(10, getTipoUsuario());
        ps.setInt(11, id);  // ID del usuario a actualizar

        // Ejecutar la actualización
        int rowsUpdated = ps.executeUpdate();

        if (rowsUpdated > 0) {
            success = true;
            con.commit();  // Confirmar la transacción si la actualización fue exitosa
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

public boolean eliminarUsuario(int id) {
     ps = null;
    boolean success = false;

    try {
        con.setAutoCommit(false);  // Desactivar el auto-commit para manejar la transacción manualmente

        String sql = "DELETE FROM Usuario WHERE ID_Usuario = ?";
        ps = con.prepareStatement(sql);

        // Establecer el ID del usuario a eliminar
        ps.setInt(1, id);

        // Ejecutar la eliminación
        int rowsDeleted = ps.executeUpdate();

        if (rowsDeleted > 0) {
            success = true;
            con.commit();  // Confirmar la transacción si la eliminación fue exitosa
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

public List<modeloUsuario> buscarUsuarios(String busqueda) {
        List<modeloUsuario> datosUsuarios = new ArrayList<>();
         ps = null;
        rs = null;
        try {
            con=login.baseDatos.getConnection();
            String sql = "SELECT * FROM Usuario WHERE LOWER(Nombre_Usuario) LIKE LOWER(?) OR LOWER(Nombre) LIKE LOWER(?) OR LOWER(Apellido_Paterno) LIKE LOWER(?) OR LOWER(Apellido_Materno) LIKE LOWER(?) OR LOWER(Fecha_Nacimiento) LIKE LOWER(?) OR LOWER(Teléfono) LIKE LOWER(?) OR LOWER(Correo) LIKE LOWER(?) OR LOWER(Domicilio) LIKE LOWER(?) OR LOWER(Tipo_Usuario) LIKE LOWER(?)";
            ps = con.prepareStatement(sql);
            String query = "%" + busqueda + "%";
            for (int i = 1; i <= 9; i++) {
                ps.setString(i, query);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                modeloUsuario usuario = new modeloUsuario();
                usuario.setId(rs.getInt("ID_Usuario"));
                usuario.setNombreUsuario(rs.getString("Nombre_Usuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellidoP(rs.getString("Apellido_Paterno"));
                usuario.setApellidoM(rs.getString("Apellido_Materno"));
                usuario.setFechaNacimiento(rs.getString("Fecha_Nacimiento"));
                usuario.setTelefono(rs.getString("Teléfono"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setDomicilio(rs.getString("Domicilio"));
                usuario.setTipoUsuario(rs.getString("Tipo_Usuario"));
                datosUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return datosUsuarios;
    }
}
