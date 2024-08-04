/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Vistas.Login;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;

/**
 *
 * @author ederd
 */
public class modeloLogin {
    Login login=new Login();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    private String correo,contraseña;
    private String usuario,tipoUsuario;
    private String mensajeValidacion;

    public modeloLogin() {
    }

    
    public modeloLogin(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }
    public boolean verificarContrasenaExistente(){
        con=login.baseDatos.getConnection();
        ps = null;
        rs = null;
        try {
            String sql = "SELECT * FROM usuario WHERE Correo = ? AND Contraseña = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, getCorreo());
            ps.setString(2, getContraseña());
            rs = ps.executeQuery();
            if(rs.next()){
                setUsuario(rs.getString("Nombre_Usuario"));
                setTipoUsuario(rs.getString("Tipo_Usuario"));
                return true;
            }else{
                setMensajeValidacion("Contraseña no valida");
                return false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean verificarCorreoExistente(){
        con=login.baseDatos.getConnection();
        ps = null;
        rs = null;
        try {
            String sql = "SELECT * FROM usuario WHERE Correo = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, getCorreo());
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                setMensajeValidacion("Correo no registrado");
                return false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
      public void verOjo(JToggleButton toglebtnOjo,JPasswordField passwordField){
        if(toglebtnOjo.isSelected()){
            passwordField.setEchoChar((char)0);
        }else{
            passwordField.setEchoChar('*');
        }
     }
      public void showWindows(JPanel vista,JPanel panelVista){
        vista.setSize(726, 457);
        vista.setLocation(0,0);
        panelVista.removeAll();
        panelVista.add(vista,BorderLayout.CENTER);
        panelVista.revalidate();
        panelVista.repaint();
    }
    
}
