/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLHistoriaClinica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Admi
 */
public class CDHistoriaClinica {
    //Declarar variables de coneccion de consultas
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

     
    public CDHistoriaClinica() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    //Metodo para insertar una Historia Clinica
    public void insertarHistoriaClinica (CLHistoriaClinica cl) throws SQLException {
    
         String sql = "{CALL sp_insertarHistoriaMedica(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumeroIdentidad());
            ps.setString(2, cl.getFechaCreacion());
            ps.setString(3,cl.getCardiobasculares());
            ps.setString(4, cl.getPulmonares());
            ps.setString(5, cl.getDigestivo());
            ps.setString(6, cl.getDiavetes());
            ps.setString(7, cl.getRenales());
            ps.setString(8, cl.getQuirurgicos());
            ps.setString(9, cl.getAlergicos());
            ps.setString(10, cl.getTransfusiones());   
            ps.setString(11, cl.getMedicamentos());  
            ps.setString(12, cl.getObservaciones());  
            ps.setInt(13, cl.getIdUsuario());  
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    }
    
    //Metodo para actualizar Historia Clinica
    public void actualizarHistoriaMedica(CLHistoriaClinica cl) {
      
       String sql = "{CALL sp_actualizarHistoriaMedica(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumeroIdentidad());
            ps.setString(2, cl.getFechaCreacion());
            ps.setString(3,cl.getCardiobasculares());
            ps.setString(4, cl.getPulmonares());
            ps.setString(5, cl.getDigestivo());
            ps.setString(6, cl.getDiavetes());
            ps.setString(7, cl.getRenales());
            ps.setString(8, cl.getQuirurgicos());
            ps.setString(9, cl.getAlergicos());
            ps.setString(10, cl.getTransfusiones());   
            ps.setString(11, cl.getMedicamentos());  
            ps.setString(12, cl.getObservaciones());  
            ps.setInt(13, cl.getIdUsuario());  
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
    
    //Metodo para eliminar Historia Clinica
    
    public void eliminarHistoria(CLHistoriaClinica cl) throws  SQLException{
        String sql = "{CALL sp_eliminarHistoriaClinica(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumeroIdentidad());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
    
    
    
    
}
