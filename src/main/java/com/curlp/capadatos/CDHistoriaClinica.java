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
import java.util.ArrayList;
import java.util.List;
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
    
         String sql = "{CALL sp_insertarHistoriaClinica(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        
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
      
       String sql = "{CALL sp_actualizarHistoriaClinica(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        
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
    
    //Metodo para poblar la tabla Historia Clinica
    public List<CLHistoriaClinica> obtenerListaHistoriaClinica() throws SQLException{
        String sql = "{CALL sp_mostrarHistoriaClinica()}";
        List<CLHistoriaClinica> miLista = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();
            while(rs.next()) {
                CLHistoriaClinica cl = new CLHistoriaClinica();
                
                cl.setNumeroIdentidad(rs.getString("numeroIdentidad"));
                cl.setFechaCreacion(rs.getString("fechaCreacion"));
                cl.setCardiobasculares(rs.getString("cardiobasculares"));
                cl.setPulmonares(rs.getString("pulmonares"));
                cl.setDigestivo(rs.getString("digestivo"));
                cl.setDiavetes(rs.getString("diavetes"));
                cl.setRenales(rs.getString("renales"));
                cl.setQuirurgicos(rs.getString("quirurgicos"));
                cl.setAlergicos(rs.getString("alergicos"));
                cl.setTransfusiones(rs.getString("transfusiones"));
                cl.setMedicamentos(rs.getString("medicamentos"));
                cl.setObservaciones(rs.getString("Observaciones"));
                cl.setIdUsuario(rs.getInt("idUsuario"));
                miLista.add(cl);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
        return miLista;   
    }
       
}
