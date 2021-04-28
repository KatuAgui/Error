/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLEmpleado;
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
public class CDEmpleado {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDEmpleado()  throws SQLException{
        this.cn = Conexion.conectar();
    }
    
     //Metodo para insertar empleado en la tabla
    public void insertarEmpleado(CLEmpleado cl) throws SQLException{
        String sql = "{CALL sp_insertarEmpleado(?,?,?,?,?,?,?,?)}";
        
         try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getPrimerNombre());
            ps.setString(2, cl.getSegundoNombre());
            ps.setString(3, cl.getPrimerApellido());
            ps.setString(4, cl.getSegundoApellido());
            ps.setString(5, cl.getDireccion());
            ps.setString(6, cl.getTelefonoCelular());
            ps.setInt(7, cl.getIdCargo());
            ps.setInt(8, cl.getIdEstado());
            ps.execute();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }      
    }
    
    //Metodo para actualizar empleado en la tabla
    public void actualizarEmpleado(CLEmpleado cl) {
        String sql = "{CALL sp_actualizarEmpleado(?,?,?,?,?,?,?,?)}";
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getPrimerNombre());
            ps.setString(2, cl.getSegundoNombre());
            ps.setString(3, cl.getPrimerApellido());
            ps.setString(4, cl.getSegundoApellido());
            ps.setString(5, cl.getDireccion());
            ps.setString(6, cl.getTelefonoCelular());
            ps.setInt(7, cl.getIdCargo());
            ps.setInt(8, cl.getIdEstado());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
    // Metodo para eliminar cita medica
    public void eliminarEmpleado(CLEmpleado cl) throws SQLException{
        String sql = "{CALL sp_eliminarEmpleado(?)}";
        
        try{
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdEmpleado());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
    // Metodo para autoincrementar empleado ID
    public int autoIncrementarEmpleado() throws SQLException{
        int idEmpleado = 0;
        String sql = "{CALL sp_autoIncrementarEmpleadoId()}";
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            idEmpleado = rs.getInt("idEmpleado");
            if(idEmpleado == 0) {
                idEmpleado = 1;
            }
                        
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
        return idEmpleado;
    }
    //Metodo para poblar tabla
    public List<CLEmpleado> obtenerListaEmpleados() throws SQLException{
        String sql = "{CALL sp_mostrarEmpleados()}";
        List<CLEmpleado> miLista = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();
            while(rs.next()) {
                CLEmpleado cl = new CLEmpleado();
                cl.setPrimerNombre(rs.getString("primerNombre"));
                cl.setSegundoNombre(rs.getString("segundoNombre"));
                cl.setPrimerApellido(rs.getString("primerApellido"));
                cl.setPrimerApellido(rs.getString("segundoApellido"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setTelefonoCelular(rs.getString("telefonoCelular"));
                cl.setIdCargo(rs.getInt("idCargo"));
                cl.setIdEstado(rs.getInt("idEstado"));
                miLista.add(cl);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
        return miLista;   
    }
    
}
