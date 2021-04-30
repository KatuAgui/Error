/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admi
 */
public class Conexion {
    private static String url = "jdbc:mysql://localhost:3306/sistemaclinicooficial?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static String user = "root";
    private static String clave = "nA6U;l.D)";
    
    public static Connection conectar() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //obtener la cadena de conexion
            return DriverManager.getConnection(url, user, clave);
            
        } catch (ClassNotFoundException e){
            throw new SQLException(e.getMessage());
    
    }
    }
    
}
