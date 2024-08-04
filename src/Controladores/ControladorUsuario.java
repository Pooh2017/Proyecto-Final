/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Funciones.Utilerias;
import Vistas.Usuarios2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelo.modeloUsuario;

/**
 *
 * @author ederd
 */
public class ControladorUsuario implements ActionListener{
    SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
    Utilerias utilerias=new Utilerias();
    private modeloUsuario modelo=new modeloUsuario();
    private Usuarios2 vista=new Usuarios2();
    DefaultTableModel tablad=new DefaultTableModel();

    public ControladorUsuario(Usuarios2 vista) {
        this.vista=vista;
        this.vista.btnInsertar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.txtBuscador.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
                buscarUsuarios();
        }
    });
        valoresLista(vista.tablaUsuarios);
         
        //buscarUsuario(vista.tablaUsuarios);
    }
    @Override
    public void actionPerformed(ActionEvent e){
         if(e.getSource()==vista.btnInsertar){
             alta();
             limpiarTabla();
             valoresLista(vista.tablaUsuarios);
         }
         if(e.getSource()==vista.btnEditar){
            int fila=vista.tablaUsuarios.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                int confirm = JOptionPane.showConfirmDialog(null, 
                "¿Estás seguro de que deseas editar este usuario?", 
                "Confirmar Ediicion", 
                JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null,"El usuario "+vista.txtId.getText()+" fue  editado");
                    editar();
                    limpiarTabla();
                    valoresLista(vista.tablaUsuarios);
                }else {
                    // Si el usuario selecciona "No"
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                }
            }
         }
         if(e.getSource()==vista.btnEliminar){
                         int fila=vista.tablaUsuarios.getSelectedRow();
             if(fila==-1){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                 int confirm = JOptionPane.showConfirmDialog(null, 
                "¿Estás seguro de que deseas borrar este usuario?", 
                "Confirmar Borrado", 
                JOptionPane.YES_NO_OPTION);

                // Si el usuario selecciona "Sí"
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null,"El usuario "+vista.txtId.getText()+" fue  elminado");
                    eliminar();
                    limpiarTabla();
                    valoresLista(vista.tablaUsuarios);
                }else {
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                }

             }
         }
             
    }
            public void limpiarTabla(){
            for(int i=0;i<vista.tablaUsuarios.getRowCount();i++){
                tablad.removeRow(i);
                i=i-1;
            }
        }
        public void valoresLista(JTable tabla) {
        tablad=(DefaultTableModel)tabla.getModel();
        tablad.setRowCount(0);
        List<modeloUsuario>datosTabla=modelo.listaUsuario();
        Object [] objeto=new Object[11];
        for(int i=0;i<datosTabla.size();i++){
            objeto[0]=datosTabla.get(i).getId();
            objeto[1]=datosTabla.get(i).getNombreUsuario();
            objeto[2]=datosTabla.get(i).getNombre();
            objeto[3]=datosTabla.get(i).getApellidoP();
            objeto[4]=datosTabla.get(i).getApellidoM();
            objeto[5]=datosTabla.get(i).getFechaNacimiento();
            objeto[6]=datosTabla.get(i).getContraseña();
            objeto[7]=datosTabla.get(i).getTelefono();
            objeto[8]=datosTabla.get(i).getCorreo();
            objeto[9]=datosTabla.get(i).getDomicilio();
            objeto[10]=datosTabla.get(i).getTipoUsuario();
            tablad.addRow(objeto);
        }
        vista.tablaUsuarios.setModel(tablad);  
    }
    public void alta(){
        modelo.setNombre(vista.txtNombre.getText());
        modelo.setApellidoP(vista.txtApellidoP.getText());
        modelo.setApellidoM(vista.txtApellidoM.getText());
        modelo.setFechaNacimiento(formatoFecha.format(vista.fechaNacimiento.getDate()));
        modelo.setTelefono(vista.txtTelefono.getText());
        modelo.setDomicilio(vista.txtDomicilio.getText());
        modelo.setNombreUsuario(vista.txtNombreUsuario.getText());
        modelo.setCorreo(vista.txtCorreo.getText());
        modelo.setContraseña(vista.visualPassword.generatePassword());
        modelo.setTipoUsuario(vista.comboxTipoUsuario.getSelectedItem().toString());
        if(modelo.existeUsuario()){
            utilerias.mostrarMensaje(vista.lblValidacion, "Ese usuario ya existe", 3000);
        }
        if(modelo.existeCorreo()){
            utilerias.mostrarMensaje(vista.lblValidacion, "Ese correo  ya esta dado de alta", 3000);
        }

        if(modelo.registrarUsuario()){
            JOptionPane.showMessageDialog(null,"Usuario dado de alta");
        }else{
            JOptionPane.showMessageDialog(null,"Error al dar alta");
        }
    }
    
    public void editar(){
        modelo.setNombre(vista.txtNombre.getText());
        modelo.setApellidoP(vista.txtApellidoP.getText());
        modelo.setApellidoM(vista.txtApellidoM.getText());
        modelo.setFechaNacimiento(formatoFecha.format(vista.fechaNacimiento.getDate()));
        modelo.setTelefono(vista.txtTelefono.getText());
        modelo.setDomicilio(vista.txtDomicilio.getText());
        modelo.setNombreUsuario(vista.txtNombreUsuario.getText());
        modelo.setCorreo(vista.txtCorreo.getText());
        modelo.setContraseña(vista.visualPassword.generatePassword());
        modelo.setTipoUsuario(vista.comboxTipoUsuario.getSelectedItem().toString());
        modelo.actualizarUsuario(Integer.parseInt(vista.txtId.getText()));
    }
    
    public void eliminar(){
        modelo.eliminarUsuario(Integer.parseInt(vista.txtId.getText()));
    }
    
    public void buscarUsuarios(){
        String busqueda = vista.txtBuscador.getText();
        List<modeloUsuario> usuarios = modelo.buscarUsuarios(busqueda);
        limpiarTabla();
        actualizarTabla(usuarios);
    }
        private void actualizarTabla(List<modeloUsuario> usuarios) {
        DefaultTableModel tablad = (DefaultTableModel) vista.tablaUsuarios.getModel();
        tablad.setRowCount(0); // Limpiar la tabla antes de agregar los nuevos datos

        for (modeloUsuario usuario : usuarios) {
            Object[] rowData = new Object[]{
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getNombre(),
                usuario.getApellidoP(),
                usuario.getApellidoM(),
                usuario.getFechaNacimiento(),
                usuario.getTelefono(),
                usuario.getCorreo(),
                usuario.getDomicilio(),
                usuario.getTipoUsuario()
            };
            tablad.addRow(rowData);
        }
    }
}
