/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLCitaMedica;
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
public class CDCitaMedica {
    // Declarar variables de conexion y consulta
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDCitaMedica() throws SQLException {
        this.cn = Conexion.conectar();
        
    }
    //Metodo para insertar una nueva cita medica en la tabla
    public void insertarCitaMedica(CLCitaMedica cl) throws SQLException{
        String sql = "{CALL sp_insertarCitaMedica(?,?,?,?,?,?)}";
        
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getObservaciones());
            ps.setString(2, cl.getFecha());
            ps.setString(3,cl.getHoraInicio());
            ps.setString(4, cl.getHoraFinal());
            ps.setString(5, cl.getNumeroIdentidad());
            ps.setInt(6, cl.getIdUsuario());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
    //Metodo para actualizar cita Medica en la tabla
    public void actualizarCitaMedica(CLCitaMedica cl) {
        String sql = "{CALL sp_actualizarCitaMedica(?,?,?,?,?,?)}";
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getObservaciones());
            ps.setString(2, cl.getFecha());
            ps.setString(3,cl.getHoraInicio());
            ps.setString(4, cl.getHoraFinal());
            ps.setInt(5, cl.getIdUsuario());
            ps.setString(6, cl.getNumeroIdentidad());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
    // Metodo para eliminar cita medica
    public void eliminarCitaMedica(CLCitaMedica cl) throws SQLException{
        String sql = "{CALL sp_eliminarCitaMedica(?)}";
        
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumeroIdentidad());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
    // Metodo para autoincrementar cita Medica ID
    public int autoIncrementarCitaId() throws SQLException{
        int idCitaMedica = 0;
        String sql = "{CALL sp_autoIncrementarCitaMedicaId()}";
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            idCitaMedica = rs.getInt("idCitaMedica");
            if(idCitaMedica == 0) {
                idCitaMedica = 1;
            }
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
        return idCitaMedica;
        
    }
    //Metodo para poblar tabla
    public List<CLCitaMedica> obtenerListaCitaMedica() throws SQLException{
        String sql = "{CALL sp_mostrarCitaMedic()}";
        List<CLCitaMedica> miLista = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();
            while(rs.next()) {
                CLCitaMedica cl = new CLCitaMedica();
                cl.setIdCitaMedica(rs.getInt("idCitaMedica"));
                cl.setObservaciones(rs.getString("citaMedicaObservaciones"));
                cl.setFecha(rs.getString("citaMedicaFecha"));
                cl.setHoraInicio(rs.getString("citaMedicaHoraInicio"));
                cl.setHoraFinal(rs.getString("citaMedicaHoraFinal"));
                cl.setIdUsuario(rs.getInt("idUsuario"));
                cl.setNombreUsuario(rs.getString("nombreUsuario"));
                cl.setNombreEmpleado(rs.getString("nombreEmpleado"));
                cl.setNumeroIdentidad(rs.getString("citaMedicaNumeroIdentidad"));
                cl.setNombrePaciente(rs.getString("nombrepaciente"));
                miLista.add(cl);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
        return miLista;
        
    }

   
    
}
