package dba;


import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** 
 *
 * @author nazif
 */
public class DBConnection {
    public static Connection gameConnection(){
        Connection con=null;
                
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            //in the url replace the host name with sql developer host name
            String url="jdbc:oracle:thin:@Your host name goes here:1521:SQLD";
            //type your username with your password
            con=DriverManager.getConnection(url,"Your username goes here","type Password");
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
        
    }
    
}
 