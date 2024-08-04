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

/**
 *
 * @author ederd
 */
public class modeloLibro {
    Login login=new Login();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    private int id,numeroCopias;
    private String titulo,autor,fechaPublicacion,editorial,isbn;
    public modeloLibro(){
        
    }
    public modeloLibro(int id, String isbn, int numeroCopias, String titulo, String autor, String fechaPublicacion, String editorial) {
        this.id = id;
        this.isbn = isbn;
        this.numeroCopias = numeroCopias;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumeroCopias() {
        return numeroCopias;
    }

    public void setNumeroCopias(int numeroCopias) {
        this.numeroCopias = numeroCopias;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
        public List listaLibro(){
        List<modeloLibro>datosLibros=new ArrayList();
        String sql="SELECT * FROM libro";
        try{
            con=login.baseDatos.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                modeloLibro p=new modeloLibro();
                p.setId(rs.getInt(1));
                p.setTitulo(rs.getString(2));
                p.setAutor(rs.getString(3));
                p.setFechaPublicacion(rs.getString(4));
                p.setIsbn(rs.getString(5));
                p.setEditorial(rs.getString(6));
                p.setNumeroCopias(rs.getInt(7));
                datosLibros.add(p);    
            }
        }catch(Exception e){
                     e.printStackTrace();
        }
        return datosLibros;
    }
        
    public boolean registrarLibro(){
    ps = null;
    boolean success = false;
         try {
            con.setAutoCommit(false);  // Desactivar el auto-commit para manejar la transacción manualmente

            String sql = "INSERT INTO libro (Título, Autor, Fecha_Publicación, ISBN, Editorial, Número_Copias) VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            // Establecer los valores en el PreparedStatement
            ps.setString(1, getTitulo());
            ps.setString(2, getAutor());
            ps.setString(3, getFechaPublicacion());
            ps.setString(4, getIsbn());
            ps.setString(5, getEditorial());
            ps.setInt(6, getNumeroCopias());

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
    
    public boolean actualizarLibro(int id){
    ps = null;
    boolean success = false;
    try {
            con.setAutoCommit(false);  // Desactivar el auto-commit para manejar la transacción manualmente

            String sql = "UPDATE libro SET Título = ?, Autor = ?, Fecha_Publicación = ?, ISBN = ?, Editorial = ?, Número_Copias = ? WHERE ID_Libro = ?";
            ps = con.prepareStatement(sql);

            // Establecer los valores en el PreparedStatement
            ps.setString(1, getTitulo());
            ps.setString(2, getAutor());
            ps.setString(3, getFechaPublicacion());
            ps.setString(4, getIsbn());
            ps.setString(5, getEditorial());
            ps.setInt(6, getNumeroCopias());
            ps.setInt(7, id);  // ID del libro a actualizar

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
    
    public boolean eliminarLibro(int id){
        ps = null;
        boolean success = false;
                try {
            con.setAutoCommit(false);  // Desactivar el auto-commit para manejar la transacción manualmente

            String sql = "DELETE FROM libro WHERE ID_Libro = ?";
            ps = con.prepareStatement(sql);

            // Establecer el ID del libro a eliminar
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
    
     public List<modeloLibro> buscarLibros(String busqueda) {
        List<modeloLibro> datosLibros = new ArrayList<>();
         ps = null;
         rs = null;

        try {
            con=login.baseDatos.getConnection();
            
            // Construir la consulta SQL
            String sql = "SELECT * FROM libro WHERE "
                    + "LOWER(Título) LIKE LOWER(?) OR "
                    + "LOWER(Autor) LIKE LOWER(?) OR "
                    + "LOWER(ISBN) LIKE LOWER(?) OR "
                    + "LOWER(Editorial) LIKE LOWER(?)";
            
            ps = con.prepareStatement(sql);

            // Establecer los parámetros en el PreparedStatement
                String query = "%" + busqueda + "%";
                            for (int i = 1; i <= 4; i++) {
                                ps.setString(i, query);
                            }

            // Ejecutar la consulta
            rs = ps.executeQuery();

            while (rs.next()) {
                modeloLibro p = new modeloLibro();
                p.setId(rs.getInt("ID_Libro"));
                p.setTitulo(rs.getString("Título"));
                p.setAutor(rs.getString("Autor"));
                p.setFechaPublicacion(rs.getString("Fecha_Publicación"));
                p.setIsbn(rs.getString("ISBN"));
                p.setEditorial(rs.getString("Editorial"));
                p.setNumeroCopias(rs.getInt("Número_Copias"));
                datosLibros.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();  // Asegurarse de cerrar el ResultSet
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();  // Asegurarse de cerrar el PreparedStatement
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return datosLibros;
    }
    
}
