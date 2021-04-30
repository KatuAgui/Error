/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDCitaMedica;
import com.curlp.capalogica.CLCitaMedica;
import java.awt.Image;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admi
 */
public class JFraCitaMedica extends javax.swing.JFrame {

    /**
     * Creates new form JFraCitaMedica
     */
    public JFraCitaMedica() throws SQLException {
        initComponents();
        encontrarCorrelativo();
        this.setLocationRelativeTo(null);
        
    }
    //Metodo para limpiar tabla
    private void limpiarTabla () { 
        DefaultTableModel dtm =  (DefaultTableModel) this.jTblCitaMedica.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
    //Metodo para poblar tabla
    private void poblarTablaCitas() throws SQLException {
        limpiarTabla();
        CDCitaMedica cm = new CDCitaMedica();
        List<CLCitaMedica> myList = cm.obtenerListaCitaMedica();
        DefaultTableModel temp = (DefaultTableModel) this.jTblCitaMedica.getModel();
        
        myList.stream().map((CLCitaMedica cd) -> {
            Object[] fila = new Object[9];
            fila[0] = cd.getIdCitaMedica();
            fila[1] = cd.getObservaciones();
            fila[2] = cd.getFecha();
            fila[3] = cd.getHoraInicio();
            fila[4] = cd.getHoraFinal();
            fila[5] = cd.getIdUsuario();
            fila[6] = cd.getNombreUsuario();
            fila[7] = cd.getNumeroIdentidad();
            fila[8] = cd.getNombrePaciente();
            return fila;
        }).forEachOrdered(temp::addRow);
    
    }
    private void encontrarCorrelativo() throws SQLException{
        CDCitaMedica cdc = new CDCitaMedica();
        CLCitaMedica clc = new CLCitaMedica();
        clc.setIdCitaMedica(cdc.autoIncrementarCitaId());
        this.jTFIdCita.setText(String.valueOf(clc.getIdCitaMedica()));
    }
    private void limpiarTextField(){
        this.jTFIdCita.setText("");
        this.jTFObservaciones.setText("");
        this.jTFFecha.setText("");
        this.jTFHoraInicio.setText("");
        this.jTFHoraFinal.setText("");
    }
    private void limpiarIdentidad(){
        this.jTFIdentidadFiltrarBorrar.setText("");
    }
    //validad textFiels
    private boolean validadTexFielO(){
        boolean o;
        o =!this.jTFObservaciones.getText().equals("");
        return o;
    }
    private boolean validadTexFielF(){
        boolean f;
        f =!this.jTFFecha.getText().equals("");
        
        return f;
    }
    private boolean validadTexFielHi(){
        boolean hi;
        hi =!this.jTFHoraInicio.getText().equals("");
        
        return hi;
    }
    private boolean validadTexFielHf(){
        boolean hf;
        hf = !this.jTFHoraFinal.getText().equals("");
        return hf;
    }
    private void ingresarCita(){
        if(!validadTexFielO()) {
            JOptionPane.showMessageDialog(null, "Tiene que ingresar las observaciones","SistemaClinico",JOptionPane.INFORMATION_MESSAGE);
            this.jTFObservaciones.requestFocus();
        }else{
            if(!validadTexFielF()) {JOptionPane.showMessageDialog(null, "Tiene que ingresar la fecha de creacion","SistemaClinico",JOptionPane.INFORMATION_MESSAGE);
            this.jTFFecha.requestFocus();
            }else{
                if(!validadTexFielHi()) {
                    JOptionPane.showMessageDialog(null, "Tiene que ingresar la Hora de inicio","SistemaClinico",JOptionPane.INFORMATION_MESSAGE);
                    this.jTFHoraInicio.requestFocus();
            }else{
                if(!validadTexFielHf()) {
                    JOptionPane.showMessageDialog(null, "Tiene que ingresar la Hora final de la cita","SistemaClinico",JOptionPane.INFORMATION_MESSAGE);
                    this.jTFHoraFinal.requestFocus();
                }else{
                    try{
                        CDCitaMedica cdc = new CDCitaMedica();
                        CLCitaMedica clc = new CLCitaMedica();
                        clc.setObservaciones(this.jTFObservaciones.getText().trim());
                        clc.setFecha(this.jTFFecha.getText().trim());
                        clc.setHoraInicio(this.jTFHoraInicio.getText().trim());
                        clc.setHoraFinal(this.jTFHoraFinal.getText().trim());
                        clc.getIdUsuario();
                        clc.getNumeroIdentidad();
                        cdc.insertarCitaMedica(clc);
                        JOptionPane.showMessageDialog(null, "Ingresado correctamente","SistemaClinico",JOptionPane.INFORMATION_MESSAGE);
                        
                    }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al almacenar registro","SistemaClinico",JOptionPane.INFORMATION_MESSAGE);
                        this.jTFObservaciones.requestFocus();
                    }
                
                }
                }
            }
        
        }
    }
    public void guardar() throws SQLException{
        ingresarCita();
        poblarTablaCitas();
        limpiarTextField();
        encontrarCorrelativo();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTFObservaciones = new javax.swing.JTextField();
        jTFFecha = new javax.swing.JTextField();
        jTFHoraInicio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFHoraFinal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jCBIdentidades = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jCBIdUsuarios = new javax.swing.JComboBox<>();
        jBtnIngresar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jBtnEditar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTFIdCita = new javax.swing.JTextField();
        jBtnLimpiar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTFIdentidadFiltrarBorrar = new javax.swing.JTextField();
        jBtnFiltrarPor = new javax.swing.JButton();
        jBthMostrar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblCitaMedica = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel4.setBackground(new java.awt.Color(79, 203, 146));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("SISTEMA CLINICO SIMEC");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel2.setText("CITA MEDICA");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel9.setText("X");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel2)
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(jLabel9))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(79, 203, 146));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingreso de una nueva cita medica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel3.setText("Observaciones");

        jLabel4.setText("Fecha de Ingreso:");

        jLabel5.setText("Hora de Inicio:");

        jTFFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFFechaActionPerformed(evt);
            }
        });

        jLabel6.setText("Hora Final de la cita:");

        jLabel7.setText("Numero de identidad ");

        jCBIdentidades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Usuario :");

        jCBIdUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jBtnIngresar.setBackground(new java.awt.Color(79, 198, 203));
        jBtnIngresar.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 12)); // NOI18N
        jBtnIngresar.setText("Ingresar");
        jBtnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIngresarActionPerformed(evt);
            }
        });

        jLabel11.setText("del paciente:");

        jBtnEditar.setBackground(new java.awt.Color(79, 198, 203));
        jBtnEditar.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 12)); // NOI18N
        jBtnEditar.setText("Editar");

        jLabel13.setText("Id cita Medica");

        jTFIdCita.setEditable(false);

        jBtnLimpiar.setBackground(new java.awt.Color(79, 198, 203));
        jBtnLimpiar.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 12)); // NOI18N
        jBtnLimpiar.setText("Limpiar");

        jBtnEliminar.setBackground(new java.awt.Color(79, 198, 203));
        jBtnEliminar.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 12)); // NOI18N
        jBtnEliminar.setText("Eliminar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(76, 76, 76)
                        .addComponent(jCBIdUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel3)))
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGap(27, 27, 27)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTFFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addComponent(jTFIdCita, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTFObservaciones, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTFHoraInicio)
                                .addComponent(jTFHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBIdentidades, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel11)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(jBtnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTFIdCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTFObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFHoraInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFHoraFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCBIdentidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jCBIdUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel5.setBackground(new java.awt.Color(79, 203, 146));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mas Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel12.setText("Numero de identidad ");

        jBtnFiltrarPor.setBackground(new java.awt.Color(79, 198, 203));
        jBtnFiltrarPor.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 14)); // NOI18N
        jBtnFiltrarPor.setText("Filtrar por :");

        jBthMostrar.setBackground(new java.awt.Color(79, 198, 203));
        jBthMostrar.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 14)); // NOI18N
        jBthMostrar.setText("Mostrar todo");
        jBthMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBthMostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(23, 23, 23)
                        .addComponent(jTFIdentidadFiltrarBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jBtnFiltrarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBthMostrar)
                        .addGap(32, 32, 32))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTFIdentidadFiltrarBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnFiltrarPor, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jBthMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(79, 203, 146));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Citas Medicas Actuales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jTblCitaMedica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cita Medica", "Observaciones", "Fecha", "Hora Inicio", "Hora Final", "Id Usuario", "Nombre Usuario", "Numero de identidad", "Nombre del paciente"
            }
        ));
        jScrollPane1.setViewportView(jTblCitaMedica);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFFechaActionPerformed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        this.dispose();
    }//GEN-LAST:event_jLabel9MousePressed

    private void jBtnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIngresarActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            Logger.getLogger(JFraCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnIngresarActionPerformed

    private void jBthMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBthMostrarActionPerformed
        try {
            poblarTablaCitas();
        } catch (SQLException ex) {
            Logger.getLogger(JFraCargo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBthMostrarActionPerformed

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
            java.util.logging.Logger.getLogger(JFraCitaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraCitaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraCitaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraCitaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFraCitaMedica().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraCitaMedica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBthMostrar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnFiltrarPor;
    private javax.swing.JButton jBtnIngresar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JComboBox<String> jCBIdUsuarios;
    private javax.swing.JComboBox<String> jCBIdentidades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFFecha;
    private javax.swing.JTextField jTFHoraFinal;
    private javax.swing.JTextField jTFHoraInicio;
    private javax.swing.JTextField jTFIdCita;
    private javax.swing.JTextField jTFIdentidadFiltrarBorrar;
    private javax.swing.JTextField jTFObservaciones;
    private javax.swing.JTable jTblCitaMedica;
    // End of variables declaration//GEN-END:variables
}
