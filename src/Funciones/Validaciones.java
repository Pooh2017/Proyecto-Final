/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author ederd
 */
public class Validaciones {
    
        public void getValidacionLetras(JTextField txtopcion,JLabel lblWarning){
        String text= txtopcion.getText();
        // Eliminar cualquier caracter que no sea una letra
        if (!text.matches("[a-zA-Z\\s]*")) {
            // Mostrar mensaje de advertencia
            txtopcion.setBackground(Color.red);
            lblWarning.setBackground(Color.red);
            lblWarning.setText("Solo se permiten letras");
            lblWarning.setVisible(true);

            // Eliminar cualquier caracter que no sea una letra o un espacio
            text = text.replaceAll("[^a-zA-Z\\s]", "");
            // Establecer el texto filtrado de vuelta al JTextField
            txtopcion.setText(text);

            // Crear un temporizador para ocultar el mensaje después de 2 segundos
            Timer timer = new Timer(2000, e -> lblWarning.setVisible(false));
            Timer timer2=new Timer(2000,e-> txtopcion.setBackground(Color.white));
            timer.setRepeats(false);
            timer.start();
            timer2.setRepeats(false);
            timer2.start();
        } else {
            lblWarning.setVisible(false);
        }
    }
            public  boolean validarLongitudNumero(String numero, int maxLongitud) {
        String numeroStr = numero;
        return numeroStr.length() <= maxLongitud;
    }  
        
            public void validarNumeros(JTextField txtopcion,JLabel lblWarning){
                String text2= txtopcion.getText();
        // Eliminar cualquier caracter que no sea una letra
        if (!text2.matches("[0-9]*")) {
            // Mostrar mensaje de advertencia
            txtopcion.setBackground(Color.red);
            lblWarning.setBackground(Color.red);
            lblWarning.setText("Solo se permiten números");
            lblWarning.setVisible(true);

            // Eliminar cualquier caracter que no sea una letra o un espacio
            text2 = text2.replaceAll("[^0-9]", "");
            // Establecer el texto filtrado de vuelta al JTextField
            txtopcion.setText(text2);

            // Crear un temporizador para ocultar el mensaje después de 2 segundos
            Timer timer = new Timer(2000, e -> lblWarning.setVisible(false));
            Timer timer2=new Timer(2000,e-> txtopcion.setBackground(Color.white));
            timer.setRepeats(false);
            timer.start();
            timer2.setRepeats(false);
            timer2.start();
        } else {
            lblWarning.setVisible(false);
        }
    }
            
            public void validacionTelefono(JTextField txtNumeroControl,JTextField txtopcion,JLabel lblWarning){
                validarNumeros( txtopcion, lblWarning);
              String text2= txtNumeroControl.getText();
                 if(validarLongitudNumero(txtNumeroControl.getText(), 10)){

                }else
                {
                    lblWarning.setBackground(Color.red);
                    lblWarning.setText("Solo se permiten 10 números");
                    lblWarning.setVisible(true);
                    txtNumeroControl.setBackground(Color.red);
                    Timer timer=new Timer(2000,e-> txtNumeroControl.setBackground(Color.white));
                    Timer timer2 = new Timer(2000, e -> lblWarning.setVisible(false));
                    timer.setRepeats(false);
                    timer.start();
                    timer2.setRepeats(false);
                    timer2.start();
                     txtNumeroControl.setText(text2.substring(0, 10));

                }
            }
     public  boolean validarCorreo(String correo) {
        String regexCorreo = "^[a-zA-Z0-9áéíóúÁÉÍÓÚ._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return correo.matches(regexCorreo);
    }
}
