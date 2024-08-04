/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author ederd
 */
public class Utilerias {

     public  boolean validarLongitudNumero(String numero, int maxLongitud) {
        String numeroStr = numero;
        return numeroStr.length() <= maxLongitud;
    } 
    public  boolean mayorEdad(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            return false;
        }
        // Obtener la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        // Crear un calendario con la fecha de nacimiento
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);
        // Calcular la diferencia en años
        int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        // Ajustar si la fecha de cumpleaños aún no ha ocurrido este año
        if (fechaActual.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH) ||
            (fechaActual.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH) && fechaActual.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        // Verificar si la persona es mayor de edad
        return edad >= 18;
    }
      public  boolean validarContrasena(String contrasena) {
        String regexContrasena = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%#*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return contrasena.matches(regexContrasena);
    }
        public static int edad(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            return 0;
        }
        // Obtener la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        // Crear un calendario con la fecha de nacimiento
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);
        // Calcular la diferencia en años
        int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        // Ajustar si la fecha de cumpleaños aún no ha ocurrido este año
        if (fechaActual.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH) ||
            (fechaActual.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH) && fechaActual.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        // Verificar si la persona es mayor de edad
        return edad;
    }
      
        
  public  void mostrarMensaje(JLabel lblValidacion, String mensaje, int duracion) {
        lblValidacion.setText(mensaje);
        lblValidacion.setVisible(true);

        // Crear un Timer para ocultar la etiqueta después de la duración especificada
        Timer timer = new Timer(duracion, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblValidacion.setVisible(false);
            }
        });
        timer.setRepeats(false); // No repetir el Timer
        timer.start(); // Iniciar el Timer
    }
        
    
}
