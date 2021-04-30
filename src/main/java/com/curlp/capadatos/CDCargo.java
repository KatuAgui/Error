/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLCargo;
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
public class CDCargo {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDCargo() throws SQLException {
        this.cn = Conexion.conectar();
    }
     //Metodo para insertar un nueva cargo en la tabla
    public void insertarCargo(CLCargo cl) throws SQLException{
        String sql = "{CALL sp_insertarCargo(?)}";
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getCargo());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    }
    //Metodo para actualizar cargo
    public void actualizarCargo(CLCargo cl) {
        String sql = "{CALL sp_actualizarCargo(?,?)}";
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getCargo());
            ps.setInt(2, cl.getIdCargo());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
     // Metodo para eliminar cargo
    public void eliminarCargo(CLCargo cl) throws SQLException{
        String sql = "{CALL sp_eliminarCargo(?)}";
        
        try{
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdCargo());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
    // Metodo para autoincrementar id cargo
    public int autoIncrementarCargoId() throws SQLException{
        int idCargo = 0;
        String sql = "{CALL sp_autoIncrementarCargoId()}";
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            idCargo = rs.getInt("idCargo");
            if(idCargo == 0) {
                idCargo = 1;
            }
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
        return idCargo;
        
    }
    //Metodo para poblar tabla
    public List<CLCargo> obtenerListaCargo() throws SQLException{
        String sql = "{CALL sp_mostrarCargo()}";
        List<CLCargo> miLista = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();
            while(rs.next()) {
                CLCargo cl = new CLCargo();
                cl.setIdCargo(rs.getInt("idCargo"));
                cl.setCargo(rs.getString("cargo"));
                miLista.add(cl);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
        return miLista;
        
    }
    // Metood que nos permite llenar el combox de cargo
    public List<String> cargarComboCargo() throws SQLException{
        String sql = "{CALL sp_mostrarCargo()}";
        
        List<String> miLista = null;
   
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while (rs.next()){
                miLista.add(rs.getString("cargo"));
            
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
            
            
        }
        return miLista;
    }
    //Metodo para insertar un nueva cargo en la tabla
    

   
    
    

}
