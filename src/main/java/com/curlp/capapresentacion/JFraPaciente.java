/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDPaciente;
import com.curlp.capalogica.CLPaciente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nesto
 */
public class JFraPaciente extends javax.swing.JFrame {

    /**
     * Creates new form JFraPaciente
     */
    public JFraPaciente() throws SQLException{
        initComponents();
        poblarTablaPaciente();
        encontrarCorrelativo();
        this.jTFNumeroidentidad.requestFocus();
        this.setLocationRelativeTo(null);
    }

    // Metodo  para limpiar los datos de la tabla   
    private void limpiarTabla(){
        DefaultTableModel dtm = (DefaultTableModel) this.jTblPaciente.getModel();
    
        while (dtm.getRowCount() >0){
        dtm.removeRow(0);
        }
    }
    
    // Metodo para poblar de datos la Tabla
    private void poblarTablaPaciente () throws SQLException{
    
        limpiarTabla();
        
        CDPaciente cdc = new CDPaciente();
        List<CLPaciente> miLista = cdc.obtenerListaPaciente();
        DefaultTableModel temp = (DefaultTableModel) this.jTblPaciente.getModel();
        
        miLista.stream().map((CLPaciente cl)->{
            Object [] fila = new Object [15];
            fila [0] = cl.getNumeroIdentidad();
            fila [1] = cl.getPrimerNombre();
            fila [2] = cl.getSegundoNombre();
            fila [3] = cl.getPrimerApellido();
            fila [4] = cl.getSegundoApellido();
            fila [5] = cl.getAntecedentesFamiliares();
            fila [6] = cl.getFechaNacimiento();
            fila [7] = cl.getTipoSangre();
            fila [8] = cl.getDireccion();
            fila [9] = cl.getTelefonoCelular();
            fila [10] = cl.getPeso();
            fila [11] = cl.getEstatura();
            fila [12] = cl.getCiudadProcedencia();
            fila [13] = cl.getEmail();
            fila [14] = cl.getIdSexo();
            return fila;
        }).forEachOrdered(temp::addRow);
    }
    
    // metodo para encontrar el correlativo del  identidad 
    
    private void encontrarCorrelativo () throws SQLException{
        CDPaciente cdc = new CDPaciente();
        CLPaciente cl = new CLPaciente();
        
        cl.setNumeroIdentidad(cdc.autoIncrementarCiudad());
        this.jTFNumeroidentidad.setText(String.valueOf(cl.getNumeroIdentidad()));
    }
    
    // Metodo para habilitar y desabilitar botones
    private void habilitarBotones (boolean guardar, boolean editar, boolean eliminar,
                                    boolean limpiar){
    this.BtnGuardar.setEnabled(guardar);
    this.BtnEditar.setEnabled(editar);
    this.BtnEliminar.setEnabled(eliminar);
    this.BtnLimpiar.setEnabled(limpiar);
    }
    
    //Metodos para limpiar las TextField
    private void limpiarTextField(){
        this.jTFPrimernombre.setText("");
        this.jTFSegundonombre.setText("");
        this.jTFPrimerapellido.setText("");
        this.jTFSegundoapellido.setText("");
        this.jTFAntecedentes.setText("");
        this.jTFFechanacimiento.setText("");
        this.jTFTiposangre.setText("");
        this.jTFDireccion.setText("");
        this.jTFTelefono.setText("");
        this.jTFPeso.setText("");
        this.jTFEstatura.setText("");
        this.jTFCiudadprocedencia.setText("");
        this.jTFEmail.setText("");
        this.jTFIdsexo.setText("");
        this.jTFNumeroidentidad.requestFocus();
    }
    
    
    //Metodo para validala TextFiel
    private boolean validarTextField(){
        boolean estado;
        
        estado = !this.jTFNumeroidentidad.getText().equals("");
        return estado;
    }
    
    //Metodo para insertar un paciente en la tabla
    private void insertarPaciente(){
    if (!validarTextField()){
    JOptionPane.showMessageDialog(null, "Tiene que ingrewsar numero de identidad ","Control",
                        JOptionPane.INFORMATION_MESSAGE);
       this.jTFNumeroidentidad.requestFocus();            
        }else{
        try{
            CDPaciente cdc = new CDPaciente();
            CLPaciente cl = new CLPaciente();
            
           cl.setNumeroIdentidad(this.jTFNumeroidentidad.getText().trim());
            cl.setPrimerNombre(this.jTFPrimernombre.getText().trim());
            cl.setSegundoNombre(this.jTFSegundonombre.getText().trim());
            cl.setPrimerApellido(this.jTFPrimerapellido.getText().trim());
            cl.setSegundoApellido(this.jTFSegundoapellido.getText().trim());
            cl.setAntecedentesFamiliares(this.jTFAntecedentes.getText().trim());
            cl.setFechaNacimiento(this.jTFFechanacimiento.getText().trim());
            cl.setTipoSangre(this.jTFTiposangre.getText().trim());
            cl.setDireccion(this.jTFDireccion.getText().trim());
            cl.setTelefonoCelular(this.jTFTelefono.getText().trim());
            cl.setPeso(Double.parseDouble(this.jTFPeso.getText().trim()));
            cl.setEstatura(Double.parseDouble(this.jTFEstatura.getText().trim()));
            cl.setCiudadProcedencia(this.jTFCiudadprocedencia.getText().trim());
            cl.setEmail(this.jTFEmail.getText().trim());
            cl.setIdSexo(Integer.parseInt(this.jTFIdsexo.getText().trim()));
            
            cdc.insertarPaciente(cl);
             JOptionPane.showMessageDialog(null, "Registro almacenado exitosamente ","Control",
                        JOptionPane.INFORMATION_MESSAGE);
            
            
        } catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "error" +   ex );
       this.jTFPrimernombre.requestFocus();  
            
    }
    
    }}
    
    // Metodo para llamr el metodo de insertar paciente 
    private void guardar()throws SQLException{
    insertarPaciente();
    poblarTablaPaciente();
    habilitarBotones(true, false, false,true);
   limpiarTextField();
   encontrarCorrelativo();
    }
    
    // Metodo para actualizar un registro de ela tabla paciente
    
  private void actualizarPaciente(){
    if (!validarTextField()){
    JOptionPane.showMessageDialog(null, "Tiene que ingrewsar numero de identidad ","Control",
                        JOptionPane.INFORMATION_MESSAGE);
       this.jTFNumeroidentidad.requestFocus();            
        }else{
        try{
            CDPaciente cdc = new CDPaciente();
            CLPaciente cl = new CLPaciente();
            
            cl.setNumeroIdentidad(this.jTFNumeroidentidad.getText().trim());
            cl.setPrimerNombre(this.jTFPrimernombre.getText().trim());
            cl.setSegundoNombre(this.jTFSegundonombre.getText().trim());
            cl.setPrimerApellido(this.jTFPrimerapellido.getText().trim());
            cl.setSegundoApellido(this.jTFSegundoapellido.getText().trim());
            cl.setAntecedentesFamiliares(this.jTFAntecedentes.getText().trim());
            cl.setFechaNacimiento(this.jTFFechanacimiento.getText().trim());
            cl.setTipoSangre(this.jTFTiposangre.getText().trim());
            cl.setDireccion(this.jTFDireccion.getText().trim());
            
            cl.setTelefonoCelular(this.jTFTelefono.getText().trim());
            cl.setPeso(Double.parseDouble(this.jTFPeso.getText().trim()));
            cl.setEstatura(Double.parseDouble(this.jTFEstatura.getText().trim()));
            cl.setCiudadProcedencia(this.jTFCiudadprocedencia.getText().trim());
            cl.setEmail(this.jTFEmail.getText().trim());
            cl.setIdSexo(Integer.parseInt(this.jTFIdsexo.getText().trim()));
            
            
            
            cdc.actualizarPaciente(cl);
             JOptionPane.showMessageDialog(null, "Actualizado exitosamente ","Control",
                        JOptionPane.INFORMATION_MESSAGE);
            
            
        } catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "error al actualizar registro" +   ex );
       this.jTFPrimernombre.requestFocus();  
            
    }
    }}
  
     // Metodo para seleccionar  los datos de la fila y asi poder modificarlos 
     private void filaSeleccionada(){
        if (this.jTblPaciente.getSelectedRow() != -1){
            this.jTFNumeroidentidad.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),0)));
            this.jTFPrimernombre.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),1)));
            this.jTFSegundonombre.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),2)));
            this.jTFPrimerapellido.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),3)));
            this.jTFSegundoapellido.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),4)));
            this.jTFAntecedentes.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),5)));
            this.jTFFechanacimiento.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),6)));
            this.jTFTiposangre.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),7)));
            this.jTFDireccion.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),8)));
            this.jTFTelefono.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),9)));
            this.jTFPeso.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),10)));
            this.jTFEstatura.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),11)));
            this.jTFCiudadprocedencia.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),12)));
            this.jTFEmail.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),13)));
            this.jTFIdsexo.setText(String.valueOf(this.jTblPaciente.getValueAt(this.jTblPaciente.getSelectedRow(),14)));
        } 
     }
    
    // Metodo para llamar el metodo de actualizar registro de la tabla
    private void editar()throws SQLException{
    actualizarPaciente();
    poblarTablaPaciente();
    habilitarBotones(true, false, false,true);
   limpiarTextField();
   encontrarCorrelativo();
    } 
    
   // Metodo para eliminar
    private void eliminarPaciente(){

        try {
            CDPaciente cdc = new CDPaciente();
            CLPaciente cl = new CLPaciente();
            cl.setNumeroIdentidad(this.jTFNumeroidentidad.getText().trim());
            cdc.eliminarPaciente(cl);
            
            JOptionPane.showMessageDialog(null, "Eliminado exitosamente ", "Control",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al eliminar registro" + ex);
            this.jTFPrimernombre.requestFocus();

        }

    }
    private void eliminar () throws SQLException{
        int resp = JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminar","control",
                                                 JOptionPane.YES_NO_OPTION);
       
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarPaciente();
                poblarTablaPaciente();
                habilitarBotones(true, false, false, true);
                limpiarTextField();
                encontrarCorrelativo();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error " + ex);
            }
        }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTFNumeroidentidad = new javax.swing.JTextField();
        jTFPrimernombre = new javax.swing.JTextField();
        jTFSegundonombre = new javax.swing.JTextField();
        jTFPrimerapellido = new javax.swing.JTextField();
        jTFSegundoapellido = new javax.swing.JTextField();
        jTFAntecedentes = new javax.swing.JTextField();
        jTFFechanacimiento = new javax.swing.JTextField();
        jTFTiposangre = new javax.swing.JTextField();
        jTFDireccion = new javax.swing.JTextField();
        jTFTelefono = new javax.swing.JTextField();
        jTFPeso = new javax.swing.JTextField();
        jTFEstatura = new javax.swing.JTextField();
        jTFCiudadprocedencia = new javax.swing.JTextField();
        jTFEmail = new javax.swing.JTextField();
        jTFIdsexo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblPaciente = new javax.swing.JTable();
        BtnEditar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("B A S E    D E   D A T O S    S I M E C");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(34, 34, 34))
        );

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel2.setText("Numero Identidad");

        jLabel3.setText("Primer Nombre");

        jLabel4.setText("Segundo Nombre");

        jLabel5.setText("Primer Apellido");

        jLabel6.setText("Segundo Apellido");

        jLabel7.setText("Antecedentes Familiares");

        jLabel8.setText("Fecha Nacimiento");

        jLabel9.setText("Tipo de Sangre");

        jLabel10.setText("Direccion");

        jLabel11.setText("Telefono Celular");

        jLabel12.setText("Peso");

        jLabel13.setText("Estatura");

        jLabel14.setText("Ciudad Procedencia");

        jLabel15.setText("Email");

        jLabel16.setText("Id Sexo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFNumeroidentidad)
                            .addComponent(jTFPrimernombre)
                            .addComponent(jTFSegundonombre)
                            .addComponent(jTFPrimerapellido)
                            .addComponent(jTFSegundoapellido, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFAntecedentes, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(jTFFechanacimiento)
                            .addComponent(jTFTiposangre)
                            .addComponent(jTFDireccion)
                            .addComponent(jTFTelefono)
                            .addComponent(jTFPeso)
                            .addComponent(jTFEstatura)
                            .addComponent(jTFCiudadprocedencia)
                            .addComponent(jTFEmail)
                            .addComponent(jTFIdsexo))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFNumeroidentidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFPrimernombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFSegundonombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFPrimerapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFSegundoapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTFAntecedentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTFFechanacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTFTiposangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTFPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTFEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTFCiudadprocedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTFIdsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTblPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Identidad", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Antecedentes Familiares", "Fecha de Nacimiento", "Tipo de Sangre", "Direccion", "Telefono Celular", "Peso", "Estatura", "Ciudad Procedencia", "Email", "Id Sexo"
            }
        ));
        jTblPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblPacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblPaciente);

        BtnEditar.setText("Editar");
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnGuardar.setText("Guardar");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1745, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(BtnGuardar)
                        .addGap(77, 77, 77)
                        .addComponent(BtnEditar)
                        .addGap(90, 90, 90)
                        .addComponent(BtnEliminar)
                        .addGap(105, 105, 105)
                        .addComponent(BtnLimpiar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnGuardar)
                            .addComponent(BtnEditar)
                            .addComponent(BtnEliminar)
                            .addComponent(BtnLimpiar)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(244, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR" +   ex );
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void jTblPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblPacienteMouseClicked
    filaSeleccionada();
    habilitarBotones(false, true,  true, true);
    }//GEN-LAST:event_jTblPacienteMouseClicked

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        try {
            editar();
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "ERROR" +   ex );
        }
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        try {
            eliminar();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "ERROR" +   ex );
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
    limpiarTextField();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(JFraPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraPaciente().setVisible(true);
                } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null, "error" +   ex );
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFAntecedentes;
    private javax.swing.JTextField jTFCiudadprocedencia;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFEmail;
    private javax.swing.JTextField jTFEstatura;
    private javax.swing.JTextField jTFFechanacimiento;
    private javax.swing.JTextField jTFIdsexo;
    private javax.swing.JTextField jTFNumeroidentidad;
    private javax.swing.JTextField jTFPeso;
    private javax.swing.JTextField jTFPrimerapellido;
    private javax.swing.JTextField jTFPrimernombre;
    private javax.swing.JTextField jTFSegundoapellido;
    private javax.swing.JTextField jTFSegundonombre;
    private javax.swing.JTextField jTFTelefono;
    private javax.swing.JTextField jTFTiposangre;
    private javax.swing.JTable jTblPaciente;
    // End of variables declaration//GEN-END:variables
}
