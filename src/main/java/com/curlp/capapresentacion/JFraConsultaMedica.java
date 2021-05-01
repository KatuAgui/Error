/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDConsultaMedica;
import com.curlp.capalogica.CLConsultaMedica;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asmodeus9563
 */
public class JFraConsultaMedica extends javax.swing.JFrame {

    /**
     * Creates new form JFraConsultaMedica
     */
    public JFraConsultaMedica() throws SQLException{
        initComponents();
        poblarTablaConsultas();
        this.setLocationRelativeTo(null);
    }

    
    private void limpiarTabla () { 
        DefaultTableModel dtm =  (DefaultTableModel) this.jTblConsultasMedicas.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    
    private void limpiarControles(){
        jTFNumeroIdentidadBusquedaFiltrada.setText(null);
    }
    private void limpiarCamposRellenar(){
        this.jTFFechaIngreso.setText(null);
        this.jTFNumeroIdentidad.setText(null);
        this.jTFObservaciones.setText(null);
        this.jTFRecetasMedicas.setText(null);
        this.jTFIngresadoPor.setText(null);
    }
        
    private void poblarTablaConsultas () throws SQLException {
        limpiarTabla();
        CDConsultaMedica cm = new CDConsultaMedica();
        List<CLConsultaMedica> myList = cm.obtenerListaConsultaMedica();
        DefaultTableModel temp = (DefaultTableModel) this.jTblConsultasMedicas.getModel();
        
        myList.stream().map((CLConsultaMedica cd)-> {
            Object[] fila = new Object[19];
            fila[0] = cd.getNumeroIdentidad();
            fila[1] = cd.getPrimerNombre();
            fila[2] = cd.getSegundoNombre();
            fila[3] = cd.getPrimerApellido();
            fila[4] = cd.getSegundoApellido();
            fila[5] = cd.getAntecentesFamiliares();
            fila[6] = cd.getDireccion();
            fila[7] = cd.getTelefonoCelular();
            fila[8] = cd.getPeso();
            fila[9] = cd.getEstatura();
            fila[10] = cd.getSexo();
            fila[11] = cd.getFechaIngreso();
            fila[12] = cd.getObservaciones();
            fila[13] = cd.getRecetasMedicas();
            fila[14] = cd.getNombreUsuario();
            fila[15] = cd.getUsuarioPrimerNombre(); 
            fila[16] = cd.getUsuarioPrimerApellido();
            fila[17] = cd.getCargo();
            fila[18] = cd.getIdUsuario();
            return fila;
        }).forEachOrdered(temp::addRow);
    
    }
    
    private void busquedaFiltrada() throws SQLException{
        limpiarTabla();
        CDConsultaMedica cdcm = new CDConsultaMedica();
        CLConsultaMedica cl = new CLConsultaMedica();
         try {

            cl.setNumeroIdentidad(this.jTFNumeroIdentidadBusquedaFiltrada.getText().trim());
            cdcm.obtenerListaConsultaMedicaX();
            poblarTablaConsultas();
            
            JOptionPane.showMessageDialog(null, "Encontrado Exitosamente ", "SIMEC",1);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al buscar el registro: " + ex);

        }
        
    }
    
    private boolean validarTFFechaIngreso(){
        boolean estado;
        estado = this.jTFFechaIngreso.getText().equals("");
        return estado;
    }
    
    private boolean validarTFObservaciones(){
        boolean estado;
        estado = this.jTFObservaciones.getText().equals("");
        return estado;
    }
    
    private boolean validarTFRecetasMedicas(){
        boolean estado;
        estado = this.jTFRecetasMedicas.getText().equals("");
        return estado;
    }
    
    private boolean validarTFNumeroIdentidad(){
        boolean estado;
        estado = this.jTFNumeroIdentidad.getText().equals("");
        return estado;
    }
    
    private boolean validarTFIngresadoPor(){
        boolean estado;
        estado = this.jTFIngresadoPor.getText().equals("");
        return estado;
    }
    
    private void insertarConsultaMedica(){
        if (validarTFFechaIngreso()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la fecha de ingreso","SIMEC",1);
            this.jTFFechaIngreso.requestFocus();
        }else if (validarTFObservaciones()){
            JOptionPane.showMessageDialog(null, "Debe ingresar Observaciones","SIMEC",1);
            this.jTFObservaciones.requestFocus();
        }else if (validarTFRecetasMedicas()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar las recetas medicas","SIMEC",1);
            this.jTFRecetasMedicas.requestFocus();
        }else if (validarTFNumeroIdentidad()){
            JOptionPane.showMessageDialog(null, "Debe ingresar el Numero de Identidad","SIMEC",1);
            this.jTFNumeroIdentidad.requestFocus();
        }else if (validarTFIngresadoPor()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el usuario que ingreso la consulta","SIMEC",1);
            this.jTFIngresadoPor.requestFocus();    
        }else{
            try {
                CDConsultaMedica cdcm = new CDConsultaMedica();
                CLConsultaMedica cl = new CLConsultaMedica();
                cl.setFechaIngreso(this.jTFFechaIngreso.getText().trim());
                cl.setObservaciones(this.jTFObservaciones.getText().trim());
                cl.setRecetasMedicas(this.jTFRecetasMedicas.getText().trim());
                cl.setNumeroIdentidad(this.jTFNumeroIdentidad.getText().trim());
                cl.setIdUsuario(Integer.valueOf(this.jTFIngresadoPor.getText().trim()));
                cdcm.insertarConsulta(cl);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al almacenar el registro: " + e);
            }
        }
    }
    
    private void editarConsultaMedica () throws SQLException{
        CDConsultaMedica cdcm = new CDConsultaMedica();
        CLConsultaMedica cl = new CLConsultaMedica();
        
        try {
                cl.setFechaIngreso(this.jTFFechaIngreso.getText().trim());
                cl.setObservaciones(this.jTFObservaciones.getText().trim());
                cl.setRecetasMedicas(this.jTFRecetasMedicas.getText().trim());
                cl.setNumeroIdentidad(this.jTFNumeroIdentidad.getText().trim());
                cl.setIdUsuario(Integer.valueOf(this.jTFIngresadoPor.getText().trim()));
                cdcm.actualizarConsulta(cl);
                JOptionPane.showMessageDialog(null, "Actualizado exitosamente ", "SIMEC",1);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al almacenar el registro: " + e);
            }
    }
    
    private void eliminarConsultaMedica() throws SQLException{
        CDConsultaMedica cdcm = new CDConsultaMedica();
        CLConsultaMedica cl = new CLConsultaMedica();
        try {

            cl.setNumeroIdentidad(this.jTFNumeroIdentidadBusquedaFiltrada.getText().trim());
            cdcm.eliminarConsulta(cl);
            
            JOptionPane.showMessageDialog(null, "Eliminado exitosamente ", "Control",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al eliminar registro" + ex);

        }
    }
    
    private void eliminar () throws SQLException{
        int resp = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminar","control",
                                                 JOptionPane.YES_NO_OPTION);
       
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarConsultaMedica();
                poblarTablaConsultas();
                limpiarCamposRellenar();
                limpiarControles();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error " + ex);
            }
        }
    }
    
    private void filaSelecionada (){
        if (this.jTblConsultasMedicas.getSelectedRow() != -1){
            this.jTFFechaIngreso.setText(String.valueOf(this.jTblConsultasMedicas.getValueAt(this.jTblConsultasMedicas.getSelectedRow(), 11)));
            this.jTFObservaciones.setText(String.valueOf(this.jTblConsultasMedicas.getValueAt(this.jTblConsultasMedicas.getSelectedRow(), 12)));
            this.jTFRecetasMedicas.setText(String.valueOf(this.jTblConsultasMedicas.getValueAt(this.jTblConsultasMedicas.getSelectedRow(), 13)));
            this.jTFNumeroIdentidad.setText(String.valueOf(this.jTblConsultasMedicas.getValueAt(this.jTblConsultasMedicas.getSelectedRow(), 0)));
            this.jTFNumeroIdentidadBusquedaFiltrada.setText(String.valueOf(this.jTblConsultasMedicas.getValueAt(this.jTblConsultasMedicas.getSelectedRow(), 0)));
            this.jTFIngresadoPor.setText(String.valueOf(this.jTblConsultasMedicas.getValueAt(this.jTblConsultasMedicas.getSelectedRow(), 18)));
        }
    }

    public JFraConsultaMedica(JButton Regresar, JButton jBtnActivarBusqedaFiltrada, JButton jBtnAgregarNuevaConsulta, JButton jBtnBuscar, JButton jBtnEditarConsulta, JButton jBtnEliminarConsulta, JButton jBtnLimpiarCampos, JButton jBtnLimpiarControles, JButton jBtnOk, JComboBox<String> jCBIngresadoPorUsuario, JLabel jLabel1, JLabel jLabel2, JLabel jLabel3, JLabel jLabel4, JLabel jLabel5, JLabel jLabel6, JLabel jLabel7, JLabel jLabel8, JPanel jPanel1, JPanel jPanel2, JPanel jPanel3, JPanel jPanel4, JScrollPane jScrollPane1, JTextField jTFFechaIngreso, JTextField jTFNumeroIdentidad, JTextField jTFNumeroIdentidadBusquedaFiltrada, JTextField jTFObservaciones, JTextField jTFRecetasMedicas, JTable jTblConsultasMedicas) throws HeadlessException {
        this.Regresar = Regresar;
        this.jBtnActivarBusqedaFiltrada = jBtnActivarBusqedaFiltrada;
        this.jBtnAgregarNuevaConsulta = jBtnAgregarNuevaConsulta;
        this.jBtnBuscar = jBtnBuscar;
        this.jBtnEditarConsulta = jBtnEditarConsulta;
        this.jBtnEliminarConsulta = jBtnEliminarConsulta;
        this.jBtnLimpiarCampos = jBtnLimpiarCampos;
        this.jBtnLimpiarControles = jBtnLimpiarControles;
        this.jLabel1 = jLabel1;
        this.jLabel2 = jLabel2;
        this.jLabel3 = jLabel3;
        this.jLabel4 = jLabel4;
        this.jLabel5 = jLabel5;
        this.jLabel6 = jLabel6;
        this.jLabel7 = jLabel7;
        this.jLabel8 = jLabel8;
        this.jPanel1 = jPanel1;
        this.jPanel3 = jPanel3;
        this.jPanel4 = jPanel4;
        this.jScrollPane1 = jScrollPane1;
        this.jTFFechaIngreso = jTFFechaIngreso;
        this.jTFNumeroIdentidad = jTFNumeroIdentidad;
        this.jTFNumeroIdentidadBusquedaFiltrada = jTFNumeroIdentidadBusquedaFiltrada;
        this.jTFObservaciones = jTFObservaciones;
        this.jTFRecetasMedicas = jTFRecetasMedicas;
        this.jTblConsultasMedicas = jTblConsultasMedicas;
    }
    
    private void guardar () throws SQLException{
        insertarConsultaMedica();
        poblarTablaConsultas();
    }
    
    private void editar () throws SQLException {
        editarConsultaMedica();
        poblarTablaConsultas();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTFFechaIngreso = new javax.swing.JTextField();
        jTFRecetasMedicas = new javax.swing.JTextField();
        jTFObservaciones = new javax.swing.JTextField();
        jTFNumeroIdentidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jBtnLimpiarCampos = new javax.swing.JButton();
        jTFIngresadoPor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jBtnAgregarNuevaConsulta = new javax.swing.JButton();
        jBtnEditarConsulta = new javax.swing.JButton();
        jBtnEliminarConsulta = new javax.swing.JButton();
        jBtnActivarBusqedaFiltrada = new javax.swing.JButton();
        jTFNumeroIdentidadBusquedaFiltrada = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jBtnBuscar = new javax.swing.JButton();
        jBtnLimpiarControles = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblConsultasMedicas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1293, 633));

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setPreferredSize(new java.awt.Dimension(1293, 633));

        jPanel1.setBackground(new java.awt.Color(79, 203, 146));
        jPanel1.setForeground(new java.awt.Color(1, 1, 1));

        jLabel8.setForeground(new java.awt.Color(1, 1, 1));
        jLabel8.setText("Consultas Medicas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 1, 1));
        jLabel1.setText("SISTEMA CLINICO SIMEC");

        Regresar.setBackground(new java.awt.Color(204, 255, 204));
        Regresar.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 12)); // NOI18N
        Regresar.setText("<----------");
        Regresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegresarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addComponent(Regresar))
                .addGap(154, 154, 154)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(Regresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(79, 203, 146));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Consulta Medica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(1, 1, 1))); // NOI18N

        jTFNumeroIdentidad.setActionCommand("<Not Set>");

        jLabel3.setForeground(new java.awt.Color(1, 1, 1));
        jLabel3.setText("Fecha de Ingreso");

        jLabel4.setForeground(new java.awt.Color(1, 1, 1));
        jLabel4.setText("Observaciones");

        jLabel5.setForeground(new java.awt.Color(1, 1, 1));
        jLabel5.setText("Recetas Medicas");

        jLabel6.setForeground(new java.awt.Color(1, 1, 1));
        jLabel6.setText("# de Identidad");

        jLabel7.setForeground(new java.awt.Color(1, 1, 1));
        jLabel7.setText("Actualizado por: ");

        jBtnLimpiarCampos.setText("Limpiar");
        jBtnLimpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarCamposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jTFIngresadoPor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jBtnLimpiarCampos)
                                .addGap(89, 89, 89))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(40, 40, 40)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFRecetasMedicas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFNumeroIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFRecetasMedicas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNumeroIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnLimpiarCampos)
                    .addComponent(jTFIngresadoPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBackground(new java.awt.Color(79, 203, 146));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Controles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(1, 1, 1))); // NOI18N

        jBtnAgregarNuevaConsulta.setText("Agregar Nueva Consulta");
        jBtnAgregarNuevaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarNuevaConsultaActionPerformed(evt);
            }
        });

        jBtnEditarConsulta.setText("Editar Consulta");
        jBtnEditarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarConsultaActionPerformed(evt);
            }
        });

        jBtnEliminarConsulta.setText("Eliminar Consulta");
        jBtnEliminarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarConsultaActionPerformed(evt);
            }
        });

        jBtnActivarBusqedaFiltrada.setText("Activar Busqueda Filtrada");
        jBtnActivarBusqedaFiltrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActivarBusqedaFiltradaActionPerformed(evt);
            }
        });

        jTFNumeroIdentidadBusquedaFiltrada.setEnabled(false);

        jLabel2.setForeground(new java.awt.Color(1, 1, 1));
        jLabel2.setText("Ingrese # de Identidad Para Buscar o Eliminar una consulta");

        jBtnBuscar.setText("Buscar");
        jBtnBuscar.setEnabled(false);
        jBtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarActionPerformed(evt);
            }
        });

        jBtnLimpiarControles.setText("Limpiar");
        jBtnLimpiarControles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarControlesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnAgregarNuevaConsulta)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnEditarConsulta)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnEliminarConsulta)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnLimpiarControles))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTFNumeroIdentidadBusquedaFiltrada, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnActivarBusqedaFiltrada))
                    .addComponent(jLabel2))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAgregarNuevaConsulta)
                    .addComponent(jBtnEditarConsulta)
                    .addComponent(jBtnEliminarConsulta)
                    .addComponent(jBtnLimpiarControles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFNumeroIdentidadBusquedaFiltrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnActivarBusqedaFiltrada)
                    .addComponent(jBtnBuscar))
                .addGap(17, 17, 17))
        );

        jPanel2.setBackground(new java.awt.Color(79, 203, 146));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultas Medicas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(1, 1, 1))); // NOI18N

        jTblConsultasMedicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Identidad", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Antecedentes Familiares", "Direccion", "Telefono Celular", "Peso", "Estatura", "Sexo", "Fecha de Ingreso", "Observaciones", "Recetas Medicas", "Nombre Usuario", "1er Nombre Usuario", "2do Nombre Usuario", "Cargo", "Id Usuario"
            }
        ));
        jTblConsultasMedicas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTblConsultasMedicas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblConsultasMedicasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblConsultasMedicas);
        if (jTblConsultasMedicas.getColumnModel().getColumnCount() > 0) {
            jTblConsultasMedicas.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(7).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(8).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(9).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(10).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(11).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(12).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(13).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(14).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(15).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(16).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(17).setPreferredWidth(200);
            jTblConsultasMedicas.getColumnModel().getColumn(18).setPreferredWidth(200);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegresarMouseClicked
        try {
            JFraPrincipal principal = new JFraPrincipal();
            principal.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JFraHistoriaClinica.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        this.dispose();
    }//GEN-LAST:event_RegresarMouseClicked

    private void jBtnAgregarNuevaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarNuevaConsultaActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            Logger.getLogger(JFraConsultaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnAgregarNuevaConsultaActionPerformed

    private void jBtnActivarBusqedaFiltradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActivarBusqedaFiltradaActionPerformed
        this.jBtnBuscar.setEnabled(true);
        this.jTFNumeroIdentidadBusquedaFiltrada.setEnabled(true);
    }//GEN-LAST:event_jBtnActivarBusqedaFiltradaActionPerformed

    private void jBtnLimpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarCamposActionPerformed
        limpiarCamposRellenar();
    }//GEN-LAST:event_jBtnLimpiarCamposActionPerformed

    private void jBtnLimpiarControlesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarControlesActionPerformed
        limpiarControles();
    }//GEN-LAST:event_jBtnLimpiarControlesActionPerformed

    private void jTblConsultasMedicasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblConsultasMedicasMouseClicked
        filaSelecionada();
    }//GEN-LAST:event_jTblConsultasMedicasMouseClicked

    private void jBtnEditarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarConsultaActionPerformed
       
        try {
            editar();
        } catch (SQLException ex) {
            Logger.getLogger(JFraConsultaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnEditarConsultaActionPerformed

    private void jBtnEliminarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarConsultaActionPerformed
        try {
            eliminar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR" +   ex );
        }
    }//GEN-LAST:event_jBtnEliminarConsultaActionPerformed

    private void jBtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarActionPerformed
        try {
            busquedaFiltrada();
        } catch (SQLException ex) {
            Logger.getLogger(JFraConsultaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFraConsultaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraConsultaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraConsultaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraConsultaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraConsultaMedica().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraConsultaMedica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Regresar;
    private javax.swing.JButton jBtnActivarBusqedaFiltrada;
    private javax.swing.JButton jBtnAgregarNuevaConsulta;
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnEditarConsulta;
    private javax.swing.JButton jBtnEliminarConsulta;
    private javax.swing.JButton jBtnLimpiarCampos;
    private javax.swing.JButton jBtnLimpiarControles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFFechaIngreso;
    private javax.swing.JTextField jTFIngresadoPor;
    private javax.swing.JTextField jTFNumeroIdentidad;
    private javax.swing.JTextField jTFNumeroIdentidadBusquedaFiltrada;
    private javax.swing.JTextField jTFObservaciones;
    private javax.swing.JTextField jTFRecetasMedicas;
    private javax.swing.JTable jTblConsultasMedicas;
    // End of variables declaration//GEN-END:variables
}
