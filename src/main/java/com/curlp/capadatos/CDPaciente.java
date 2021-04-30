/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.curlp.capadatos;

import com.curlp.capalogica.CLPaciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class CDPaciente {

private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDPaciente() throws SQLException {
        this.cn = Conexion.conectar();
    }
    public void insertarPaciente(CLPaciente cl) throws SQLException{
        String sql = "{CALL sp_insertarPaciente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumeroIdentidad());
            ps.setString(2, cl.getPrimerNombre());
            ps.setString(3,cl.getSegundoNombre());
            ps.setString(4, cl.getPrimerApellido());
            ps.setString(5, cl.getSegundoApellido());
            ps.setString(6, cl.getAntecedentesFamiliares());
            ps.setString(7, cl.getFechaNacimiento());
            ps.setString(8, cl.getTipoSangre());
            ps.setString(9, cl.getDireccion());
            ps.setString(10, cl.getTelefonoCelular());
            ps.setDouble(11, cl.getPeso());
            ps.setDouble(12, cl.getEstatura());
            ps.setString(13, cl.getCiudadProcedencia());
            ps.setString(14, cl.getEmail());
            ps.setInt(15, cl.getIdSexo());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    }
    
    public void actualizarPaciente(CLPaciente cl) {
        String sql = "{CALL sp_actualizarPaciente(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumeroIdentidad());
            ps.setString(2, cl.getPrimerNombre());
            ps.setString(3,cl.getSegundoNombre());
            ps.setString(4, cl.getPrimerApellido());
            ps.setString(5, cl.getSegundoApellido());
            ps.setString(6, cl.getAntecedentesFamiliares());
            ps.setString(7, cl.getFechaNacimiento());
            ps.setString(8, cl.getTipoSangre());
            ps.setString(9, cl.getDireccion());
            ps.setString(10, cl.getTelefonoCelular());
            ps.setDouble(11, cl.getPeso());
            ps.setDouble(12, cl.getEstatura());
            ps.setString(13, cl.getCiudadProcedencia());
            ps.setString(14, cl.getEmail());
            ps.setInt(15, cl.getIdSexo());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
    
    public void eliminarPaciente(CLPaciente cl) throws SQLException{
        String sql = "{CALL sp_eliminarPaciente(?)}";
        
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumeroIdentidad());
            ps.execute();
           
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
    
    }
    
 //Metodo para poblar tabla
    public List<CLPaciente> obtenerListaPaciente() throws SQLException{
        String sql = "{CALL sp_mostrarPacientes()}";
        List<CLPaciente> miLista = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();
            while(rs.next()) {
                CLPaciente cl = new CLPaciente();
                cl.setNumeroIdentidad(rs.getString("numeroIdentidad"));
                cl.setPrimerNombre(rs.getString("pacientePrimerNombre"));
                cl.setSegundoNombre(rs.getString("pacienteSegundoNombre"));
                cl.setPrimerApellido(rs.getString("pacientePrimerApellido"));
                cl.setSegundoApellido(rs.getString("pacienteSegundoApellido"));
                cl.setAntecedentesFamiliares(rs.getString("pacienteAntecedentesFamiliares"));
                cl.setFechaNacimiento(rs.getString("pacienteFechaNacimiento"));
                cl.setTipoSangre(rs.getString("pacienteTipoSangre"));
                cl.setDireccion(rs.getString("pacienteDireccion"));
                cl.setTelefonoCelular(rs.getString("pacienteTelefonoCelular"));
                cl.setPeso(rs.getDouble("pacientePeso"));
                cl.setEstatura(rs.getDouble("pacienteEstatura"));
                cl.setCiudadProcedencia(rs.getString("pacienteCiudadProcedencia"));
                cl.setEmail(rs.getString("pacienteEmail"));
                cl.setSexo(rs.getString("sexo"));
                miLista.add(cl);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        
        }
        return miLista;
        
    }
    //Metodo comboPacientes
   public List<String> cargarComboPacientes() throws SQLException{
        String sql = "{CALL sp_mostrarPaciente()}";
        
        List<String> miLista = null;
   
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while (rs.next()){
                miLista.add(rs.getString("numeroIdentidad"));
            
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
            
            
        }
        return miLista;
    }    

    public String autoIncrementarCiudad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
