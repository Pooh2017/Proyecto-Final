/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Vistas.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.modeloLogin;
import Funciones.Utilerias;
import Vistas.Usuarios2;
/**
 *
 * @author ederd
 */
public class controladorLogin implements ActionListener{
    private modeloLogin modelo;
    private Login vista;
    Utilerias funciones=new Utilerias();

    public controladorLogin(modeloLogin modelo, Login vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnIniciarSesion.addActionListener(this);
        this.vista.VerPasword.addActionListener(this);
        this.vista.txtCorreo.addActionListener(this);
        this.vista.btnRegistrar.addActionListener(this);
    }
     @Override
    public void actionPerformed(ActionEvent e){
        modelo.verOjo(vista.VerPasword, vista.Pasword);
        if(e.getSource()==vista.btnIniciarSesion){
             verificarUsuario();
        }
    }
    
    public void verificarUsuario(){
        modelo.setCorreo(vista.txtCorreo.getText());
        modelo.setContraseña( new String (vista.Pasword.getPassword()));
        if(modelo.verificarCorreoExistente()){
            if(modelo.verificarContrasenaExistente()){
                vista.dispose();
                JOptionPane.showMessageDialog(null, "Bienvenido "+modelo.getUsuario()+" de tipo "+modelo.getTipoUsuario());
                Usuarios2 vista=new Usuarios2();
                ControladorUsuario controlador =new ControladorUsuario(vista);
                vista.setVisible(true);
                vista.setLocationRelativeTo(vista);
            }else{
                funciones.mostrarMensaje( vista.lblValidacion, modelo.getMensajeValidacion(),3000);
            }
            
        }else{
            funciones.mostrarMensaje( vista.lblValidacion, modelo.getMensajeValidacion(),3000);
        }    
    }  

     public void iniciar() {
        vista.setVisible(true);
    }
}
