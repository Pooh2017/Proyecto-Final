/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Vistas.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ederd
 */
public class modeloRegistrarUsuario {
    Login login=new Login();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    private String nombre,apellidoP,apellidoM,fechaNacimiento,telefono,domicilio;
    private String nombreUsuario,correo,contraseña,tipoUsuario;

    public modeloRegistrarUsuario() {
    }

    public modeloRegistrarUsuario(String nombre, String apellidoP, String apellidoM, String fechaNacimiento, String telefono, String domicilio, String nombreUsuario, String correo, String contraseña) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
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
    
    public boolean registrarUsuario(){
         con=login.baseDatos.getConnection();
         try{
             String sql="INSERT INTO Usuario (Nombre_Usuario, Nombre, Apellido_Paterno, Apellido_Materno, Fecha_Nacimiento, Contraseña, Teléfono, Correo, Domicilio, Tipo_Usuario) VALUES (?, ?, ?, ?, ?, ?,? , ?, ?, ?)";
              ps=con.prepareStatement(sql);
              ps.setString(1, getNombreUsuario());
              ps.setString(2, getNombre());
              ps.setString(3,getApellidoP());
              ps.setString(4, getApellidoM());
              ps.setString(5, getFechaNacimiento());
              ps.setString(6,getContraseña());
              ps.setString(7, getTelefono());
              ps.setString(8, getCorreo());
              ps.setString(9,getDomicilio());
              ps.setString(10, getTipoUsuario());
              int rowsInserted = ps.executeUpdate();
              if(rowsInserted>0){
                  return true;
              }else{
                  return false;
              }
         }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
