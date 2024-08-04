/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Funciones.Utilerias;
import Vistas.Libros;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.modeloLibro;
import modelo.modeloUsuario;


/**
 *
 * @author ederd
 */
public class controladorLibro implements ActionListener{
     SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
    Utilerias utilerias=new Utilerias();
    DefaultTableModel tablad=new DefaultTableModel();
    private modeloLibro modelo=new modeloLibro();
    private Libros vista=new Libros();

    public controladorLibro(Libros vista) {
        this.vista=vista;
         this.vista.btnInsertar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
         this.vista.txtBuscador.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
                buscarLibros();
        }
    });
        valoresLista(vista.tablaLibros);
    }
    
        @Override
    public void actionPerformed(ActionEvent e){
         if(e.getSource()==vista.btnInsertar){
             alta();
             limpiarTabla();
             valoresLista(vista.tablaLibros);
         }
         if(e.getSource()==vista.btnEditar){
            int fila=vista.tablaLibros.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                int confirm = JOptionPane.showConfirmDialog(null, 
                "¿Estás seguro de que deseas editar este Libro?", 
                "Confirmar Ediicion", 
                JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null,"El Libro "+vista.txtId.getText()+" fue  editado");
                    editar();
                    limpiarTabla();
                    valoresLista(vista.tablaLibros);
                }else {
                    // Si el usuario selecciona "No"
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                }
            }
         }
         if(e.getSource()==vista.btnEliminar){
                         int fila=vista.tablaLibros.getSelectedRow();
             if(fila==-1){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                 int confirm = JOptionPane.showConfirmDialog(null, 
                "¿Estás seguro de que deseas borrar este Libro?", 
                "Confirmar Borrado", 
                JOptionPane.YES_NO_OPTION);

                // Si el usuario selecciona "Sí"
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null,"El Libro "+vista.txtId.getText()+" fue  elminado");
                    eliminar();
                    limpiarTabla();
                    valoresLista(vista.tablaLibros);
                }else {
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                }

             }
         }
             
    }
    
      public void limpiarTabla(){
            for(int i=0;i<vista.tablaLibros.getRowCount();i++){
                tablad.removeRow(i);
                i=i-1;
            }
        }
    public void valoresLista(JTable tabla){
        tablad=(DefaultTableModel)tabla.getModel();
        tablad.setRowCount(0);
        List<modeloLibro>datosTabla=modelo.listaLibro();
        Object [] objeto=new Object[7];
        for(int i=0;i<datosTabla.size();i++){
            objeto[0]=datosTabla.get(i).getId();
            objeto[1]=datosTabla.get(i).getTitulo();
            objeto[2]=datosTabla.get(i).getAutor();
            objeto[3]=datosTabla.get(i).getFechaPublicacion();
            objeto[4]=datosTabla.get(i).getIsbn();
            objeto[5]=datosTabla.get(i).getEditorial();
            objeto[6]=datosTabla.get(i).getNumeroCopias();
            tablad.addRow(objeto);
        }
        vista.tablaLibros.setModel(tablad);  
    }
    public void alta(){
        modelo.setTitulo(vista.txtTitulo.getText());
        modelo.setAutor(vista.txtAutor.getText());
        modelo.setFechaPublicacion(formatoFecha.format(vista.fechaPublicacion.getDate()));
        modelo.setIsbn(vista.txtIsbn.getText());
        modelo.setEditorial(vista.txtEditorial.getText());
        modelo.setNumeroCopias(Integer.parseInt(vista.txtNúmeroCopias.getText()));
        if(modelo.registrarLibro()){
            JOptionPane.showMessageDialog(null,"Usuario dado de alta");
        }else{
            JOptionPane.showMessageDialog(null,"Error al dar alta");
        }
    }
    
    public void editar(){
        modelo.setTitulo(vista.txtTitulo.getText());
        modelo.setAutor(vista.txtAutor.getText());
        modelo.setFechaPublicacion(formatoFecha.format(vista.fechaPublicacion.getDate()));
        modelo.setIsbn(vista.txtIsbn.getText());
        modelo.setEditorial(vista.txtEditorial.getText());
        modelo.setNumeroCopias(Integer.parseInt(vista.txtNúmeroCopias.getText()));
        modelo.actualizarLibro(Integer.parseInt(vista.txtId.getText()));
    }
    
    public void eliminar(){
        modelo.eliminarLibro(Integer.parseInt(vista.txtId.getText()));
    }
    
    public void buscarLibros(){
        String busqueda = vista.txtBuscador.getText();
        List<modeloLibro> libros = modelo.buscarLibros(busqueda);
        limpiarTabla();
        actualizarTabla(libros);
    }
    public void actualizarTabla(List<modeloLibro> libros){
        DefaultTableModel tablad = (DefaultTableModel) vista.tablaLibros.getModel();
        tablad.setRowCount(0); // Limpiar la tabla antes de agregar los nuevos datos
                for (modeloLibro libro : libros) {
            Object[] rowData = new Object[]{
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getFechaPublicacion(),
                libro.getIsbn(),
                libro.getEditorial(),
                libro.getNumeroCopias(),
            };
            tablad.addRow(rowData);
        }
    }
    
}
