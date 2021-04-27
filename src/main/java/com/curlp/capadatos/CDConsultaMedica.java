/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLConsultaMedica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admi
 */
public class CDConsultaMedica {
    
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;

    public CDConsultaMedica() throws SQLException{
        this.cn = Conexion.conectar();
    }
    
    // metodo para insertar una ConsultaMedica
    public void insertarConsulta(CLConsultaMedica cl) throws SQLException{
  
        String sql = "{CALL sp_insertarConsultaMedica(?,?,?,?,?)}";
        
        try {
           ps = cn.prepareCall(sql);
           ps.setString(1, cl.getFechaIngreso());
           ps.setString(2, cl.getObservaciones());
           ps.setString(3, cl.getRecetasMedicas());
           ps.setString(4, cl.getNumeroIdentidad());
           ps.setInt(5, cl.getIdUsuario());
           ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }  
    }
    
    public void actualizarConsulta(CLConsultaMedica cl) throws SQLException{
        String sql = "{CALL sp_actualizarConsultaMedica(?,?,?,?,?)}";
        
        try {
           ps = cn.prepareCall(sql);
           ps.setString(1, cl.getFechaIngreso());
           ps.setString(2, cl.getObservaciones());
           ps.setString(3, cl.getRecetasMedicas());
           ps.setString(4, cl.getNumeroIdentidad());
           ps.setInt(5, cl.getIdUsuario());
           ps.execute();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
    
    public void eliminarConsulta(CLConsultaMedica cl) throws  SQLException{
        String sql = "{CALL sp_eliminarConsulta(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumeroIdentidad());
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
    
    public void mostrarConsulta(CLConsultaMedica cl) throws  SQLException{
        String sql = "{CALL sp_mostrarConsulta()}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    
    }
}
