/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Vistas.Devoluciones;
import Vistas.Libros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.modeloDevoluciones;
import modelo.modeloLibro;
import modelo.modeloPrestamo;
import modelo.modeloUsuario;

/**
 *
 * @author ederd
 */
public class controladorDevoluciones implements ActionListener{
    private Libros vista3=new Libros();
    private modeloLibro moL=new modeloLibro();
    private controladorLibro conLibro=new controladorLibro(vista3);
   private modeloPrestamo modeloP=new modeloPrestamo();
   
    SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
    DefaultTableModel tablad=new DefaultTableModel();
    private modeloDevoluciones modelo=new modeloDevoluciones();
    private Devoluciones vista=new Devoluciones();
    
    public controladorDevoluciones(Devoluciones vista){
        this.vista=vista;
        this.vista.btnDevolver.addActionListener(this);
        valoresLista(vista.tablaDevoluciones);
    }
    @Override
    public void actionPerformed(ActionEvent e){
         if(e.getSource()==vista.btnDevolver){
                         int fila=vista.tablaDevoluciones.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                 //int numCopias=Integer.parseInt(vista.tablaDevoluciones.getValueAt(vista.tablaDevoluciones.getSelectedRow(), 5).toString())+1;
                int confirm = JOptionPane.showConfirmDialog(null, 
                "¿Estás seguro de que deseas devolver este libro?", 
                "Confirmar Ediicion", 
                JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){

                         JOptionPane.showMessageDialog(null,"El libro "+vista.txtTitulo.getText()+" del la persona \n"+
                                 " "+vista.tablaDevoluciones.getValueAt(vista.tablaDevoluciones.getSelectedRow(), 2)+" "+
                                 vista.tablaDevoluciones.getValueAt(vista.tablaDevoluciones.getSelectedRow(), 3)+" "+
                                 vista.tablaDevoluciones.getValueAt(vista.tablaDevoluciones.getSelectedRow(), 4)+" gracias");
                         devolver();
                         limpiarTabla();
                         valoresLista(vista.tablaDevoluciones);
                    
                }else {
                    // Si el usuario selecciona "No"
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                }
            }

         }
    }
                public void limpiarTabla(){
            for(int i=0;i<vista.tablaDevoluciones.getRowCount();i++){
                tablad.removeRow(i);
                i=i-1;
            }
        }
     public void valoresLista(JTable tabla) {
        tablad=(DefaultTableModel)tabla.getModel();
        tablad.setRowCount(0);
        List<modeloDevoluciones>datosTabla=modelo.listaPrestamos();
        Object [] objeto=new Object[11];
        for(int i=0;i<datosTabla.size();i++){
            objeto[0]=datosTabla.get(i).getIdP();
            objeto[1]=datosTabla.get(i).getIdU();
            objeto[2]=datosTabla.get(i).getNombre();
            objeto[3]=datosTabla.get(i).getApellidoP();
            objeto[4]=datosTabla.get(i).getApellidoM();
            objeto[5]=datosTabla.get(i).getIdL();
            objeto[6]=datosTabla.get(i).getTitulo();
            objeto[7]=datosTabla.get(i).getFechaP();
            objeto[8]=datosTabla.get(i).getFechaD();
            tablad.addRow(objeto);
        }
        vista.tablaDevoluciones.setModel(tablad);  
    }
     
     public void devolver(){
        try {
            int idL=Integer.parseInt(vista.tablaDevoluciones.getValueAt(vista.tablaDevoluciones.getSelectedRow(), 5).toString());
            int numC=modelo.obtenerNumeroCopias(idL)+1;
            modelo.eliminarPrestamo(Integer.parseInt(vista.txtId.getText()));
            modelo.actualizarNumeroCopias(idL, numC);
        } catch (SQLException ex) {
            Logger.getLogger(controladorDevoluciones.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
