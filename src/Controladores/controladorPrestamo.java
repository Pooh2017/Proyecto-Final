/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Vistas.Libros;
import Vistas.Prestamos;
import Vistas.Usuarios2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import modelo.modeloPrestamo;
import Controladores.controladorLibro;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ederd
 */
public class controladorPrestamo  implements ActionListener{
     private modeloPrestamo modelo=new modeloPrestamo();
    private Prestamos vista=new Prestamos();
    private Usuarios2 vista2=new Usuarios2(); 
    private ControladorUsuario controladorUsuario=new ControladorUsuario(vista2);
    private Libros vista3=new Libros();
    private controladorLibro conLibro=new controladorLibro(vista3);
 
    LocalDate fechaActual = LocalDate.now();
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String fechaFormateada = fechaActual.format(formatoFecha);
    
    LocalDate fechaDespuesDeCincoDias = fechaActual.plusDays(5);
   String fechaDespuesDeCincoDiasFormateada = fechaDespuesDeCincoDias.format(formatoFecha);
    
    public controladorPrestamo( Prestamos vista){
        this.vista=vista;
        controladorUsuario.valoresLista(vista.tablaUsuarios);
        conLibro.valoresLista(vista.tablaLibros);
        this.vista.btnPrestar.addActionListener(this);
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
         if(e.getSource()==vista.btnPrestar){
             int fila1=vista.tablaUsuarios.getSelectedRow();
             int fila2=vista.tablaLibros.getSelectedRow();
             if(fila1==-1){
                 JOptionPane.showMessageDialog(null,"Escoga un usuario");
             }else{
                 if(fila2==-1){
                     JOptionPane.showMessageDialog(null,"Escoga un libro");
                 }else
                 {
                     
                     int confirm = JOptionPane.showConfirmDialog(null, 
                "¿Desea aquirir el libro  "+vista.tablaLibros.getValueAt(vista.tablaLibros.getSelectedRow(), 1) +
                        " \n para la persona  "+vista.tablaUsuarios.getValueAt(vista.tablaUsuarios.getSelectedRow(), 2)+" "+vista.tablaUsuarios.getValueAt(vista.tablaUsuarios.getSelectedRow(), 3)+" "+vista.tablaUsuarios.getValueAt(vista.tablaUsuarios.getSelectedRow(), 4)+" ?", 
                "Confirmar Ediicion", 
                JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){
                    prestamoLibro();
                    //numeroCopias();
                    conLibro.limpiarTabla();
                    conLibro.valoresLista(vista.tablaLibros);
                }else {
                    // Si el usuario selecciona "No"
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                }
                 }
             }
             
             
         }
    }
    public void prestamoLibro(){
        modelo.setIdUsuario(Integer.parseInt(vista.tablaUsuarios.getValueAt(vista.tablaUsuarios.getSelectedRow(), 0).toString()));
        modelo.setIdLibro(Integer.parseInt(vista.tablaLibros.getValueAt(vista.tablaLibros.getSelectedRow(), 0).toString()));
        modelo.setFechaPrestamo(fechaFormateada);
        modelo.setFechaDevolucionEstimada(fechaDespuesDeCincoDiasFormateada);
        int numCopias=Integer.parseInt(vista.tablaLibros.getValueAt(vista.tablaLibros.getSelectedRow(), 6).toString())-1;
        if(modelo.registrarPrestamo()){
            try {
                JOptionPane.showMessageDialog(null,"El dia "+fechaFormateada+
                        "\na la persona "+vista.tablaUsuarios.getValueAt(vista.tablaUsuarios.getSelectedRow(), 2)+" "+vista.tablaUsuarios.getValueAt(vista.tablaUsuarios.getSelectedRow(), 3)+" "+vista.tablaUsuarios.getValueAt(vista.tablaUsuarios.getSelectedRow(), 4)+"\n"
                                +"se le presto el libro"+vista.tablaLibros.getValueAt(vista.tablaLibros.getSelectedRow(), 1)+"\n"+
                                        "se debe regresar la fecha "+fechaDespuesDeCincoDiasFormateada+"o antes sino habra una multa");
                modelo.actualizarNumeroCopias(modelo.getIdLibro(), numCopias);
            } catch (SQLException ex) {
                Logger.getLogger(controladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,"No se pudo realizar el prestamo","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void numeroCopias(){
         try {
             if(modelo.obtenerNumeroCopiasDisponibles(modelo.getIdLibro())>0){
                 prestamoLibro();
             }else{
                 JOptionPane.showMessageDialog(null,"NO HAY LIBROS DISPONIBLES","ERROR",JOptionPane.ERROR_MESSAGE);
             }
         } catch (SQLException ex) {
             Logger.getLogger(controladorPrestamo.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
