/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Vistas.RegistarUsuario;
import modelo.modeloRegistrarUsuario;

/**
 *
 * @author ederd
 */
public class controladorRegistrarUsuario {
    private modeloRegistrarUsuario modelo=new modeloRegistrarUsuario();
    private RegistarUsuario vista =new RegistarUsuario();

    public controladorRegistrarUsuario(modeloRegistrarUsuario modelo,RegistarUsuario vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
         public void iniciar() {
        vista.setVisible(true);
    }
}
