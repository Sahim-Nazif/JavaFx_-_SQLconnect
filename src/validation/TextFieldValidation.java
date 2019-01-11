/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author nazif
 */
public class TextFieldValidation {
    public static boolean isTextFieldNotEmpty(TextField tf){
       boolean b=false;
       if (tf.getText().length() !=0 ||!tf.getText().isEmpty())
       b=true;
       return b;
       
    }
  
    public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String errorMessage){
       boolean b=true;
       String msg=null;
       tf.getStyleClass().remove("error");
       if (!isTextFieldNotEmpty(tf)){
           
           b=false;
           msg=errorMessage;
           tf.getStyleClass().add("error");
       }
       lb.setText(msg);
       return b;
    }
    public static boolean istextFieldTypeNumber(TextField tf){
        boolean b=false;
        
    if (tf.getText().matches("([0-9]+(\\.[0-9]+)?)+"))
        b=true;
     return b;
    }
    
    public static boolean istextFieldTypeNumber(TextField tf, Label lb, String errorMessage){
        boolean b=true;
        String msg=null;
        tf.getStyleClass().remove("error");
        if (!istextFieldTypeNumber(tf)){
             b=true;
             msg=errorMessage;
           tf.getStyleClass().add("error");
        }
       
      lb.setText(msg);
       return b;
     }
}
