/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author nacho
 */
public class Conectar {
    
    Connection cn;
    Statement st;
    
    public Connection conexion(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn =DriverManager.getConnection("jdbc:mysql://localhost/escuela","root","2303");
            System.out.println("conectado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      
        return cn;     
    
    
    }
    
}
