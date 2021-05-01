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
        //encontrarCorrelativo();
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
    
    
//    // Metodo para habilitar y desabilitar botones
//    private void habilitarBotones (boolean guardar, boolean editar, boolean eliminar,
//                                    boolean limpiar){
//    this.BtnGuardar.setEnabled(guardar);
//    this.BtnEditar.setEnabled(editar);
//    this.BtnEliminar.setEnabled(eliminar);
//    this.BtnLimpiar.setEnabled(limpiar);
//    }
//    
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
//    
//    
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
        //habilitarBotones(true, false, false,true);
        limpiarTextField();
    }

    // Metodo para actualizar un registro de ela tabla paciente
    
    private void actualizarPaciente() {
        if (!validarTextField()) {
            JOptionPane.showMessageDialog(null, "Tiene que ingrewsar numero de identidad ", "Control",
                    JOptionPane.INFORMATION_MESSAGE);
            this.jTFNumeroidentidad.requestFocus();
        } else {
            try {
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
                cl.setIdSexo(Integer.valueOf(this.jTFIdsexo.getText().trim()));
                cdc.actualizarPaciente(cl);

                JOptionPane.showMessageDialog(null, "Actualizado exitosamente ", "Control",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al actualizar registro" + ex);
                this.jTFPrimernombre.requestFocus();

            }
        }
    }
//  
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
//    
    // Metodo para llamar el metodo de actualizar registro de la tabla
    private void editar()throws SQLException{
        actualizarPaciente();
        poblarTablaPaciente();
    //habilitarBotones(true, false, false,true);
        limpiarTextField();
   //encontrarCorrelativo();
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
                //habilitarBotones(true, false, false, true);
                limpiarTextField();
                //encontrarCorrelativo();
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
        Regresar = new javax.swing.JButton();
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
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblPaciente = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        BtnGuardar = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setFocusCycleRoot(false);

        jPanel1.setBackground(new java.awt.Color(79, 203, 146));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("S I S T E M A    C L I N I C O    S I M E C");

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
                .addContainerGap()
                .addComponent(Regresar)
                .addGap(282, 282, 282)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Regresar)
                    .addComponent(jLabel1))
                .addGap(34, 34, 34))
        );

        jPanel2.setBackground(new java.awt.Color(79, 203, 146));

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

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("DATOS DE PACIENTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFSegundoapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFPrimernombre, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFNumeroidentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFSegundonombre, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFPrimerapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTFAntecedentes, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(43, 43, 43)
                                .addComponent(jTFFechanacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTFTiposangre, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                    .addComponent(jTFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(96, 96, 96)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jTFEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(38, 38, 38)
                        .addComponent(jTFPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCiudadprocedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFIdsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTFEmail)
                                .addGap(55, 55, 55)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTFPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTFEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTFCiudadprocedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTFIdsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTFNumeroidentidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTFSegundoapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTFPrimernombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFSegundonombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jTFPrimerapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(458, 458, 458))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFAntecedentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jTFFechanacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFTiposangre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3.setBackground(new java.awt.Color(79, 198, 203));
        jPanel3.setToolTipText("");

        jTblPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Identidad", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Antecedentes Familiares", "Fecha de Nacimiento", "Tipo de Sangre", "Direccion", "Telefono Celular", "Peso", "Estatura", "Ciudad Procedencia", "Email", "Sexo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblPaciente.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTblPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblPacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblPaciente);
        if (jTblPaciente.getColumnModel().getColumnCount() > 0) {
            jTblPaciente.getColumnModel().getColumn(0).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(1).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(2).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(3).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(4).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(5).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(5).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(6).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(6).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(7).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(7).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(8).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(8).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(9).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(9).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(10).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(10).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(11).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(11).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(12).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(12).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(13).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(13).setPreferredWidth(200);
            jTblPaciente.getColumnModel().getColumn(14).setResizable(false);
            jTblPaciente.getColumnModel().getColumn(14).setPreferredWidth(200);
        }

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setText("DATOS ALMACENADOS DE PACIENTES");

        BtnGuardar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnEditar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BtnEditar.setText("Editar");
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnEliminar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnLimpiar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(jLabel17))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEliminar)
                    .addComponent(BtnEditar)
                    .addComponent(BtnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
//    habilitarBotones(false, true,  true, true);
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

    private void RegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegresarMouseClicked
        try {
            JFraPrincipal principal = new JFraPrincipal();
            principal.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JFraHistoriaClinica.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }//GEN-LAST:event_RegresarMouseClicked

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
    private javax.swing.JButton Regresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel3;
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
