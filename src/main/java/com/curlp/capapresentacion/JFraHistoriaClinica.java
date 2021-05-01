/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDHistoriaClinica;
import com.curlp.capalogica.CLHistoriaClinica;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hnunez
 */
public class JFraHistoriaClinica extends javax.swing.JFrame {

    /**
     * Creates new form JFraHistoriaClinica
     */
    public JFraHistoriaClinica() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setLocationRelativeTo(null);
    }
    
    //Metodo par limpiar la tabla
    private void limpiarTabla() {
        
        DefaultTableModel dtm = (DefaultTableModel) this.jTblHistoria.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    
    }
    
    //Metodo para poblar la tabla
    private void poblarTablaHistoriaClinica() throws SQLException {
        limpiarTabla();
        
        CDHistoriaClinica cdh = new CDHistoriaClinica();
        List<CLHistoriaClinica> miLista = cdh.obtenerListaHistoriaClinica();
        DefaultTableModel temp = (DefaultTableModel) this.jTblHistoria.getModel();
        
        miLista.stream().map((CLHistoriaClinica cl) -> {
            Object[] fila = new Object[13];
            fila[0] = cl.getNumeroIdentidad();
            fila[1] = cl.getFechaCreacion();
            fila[2] = cl.getCardiobasculares();
            fila[3] = cl.getPulmonares();
            fila[4] = cl.getDigestivo();
            fila[5] = cl.getDiavetes();
            fila[6] = cl.getRenales();
            fila[7] = cl.getQuirurgicos();
            fila[8] = cl.getAlergicos();
            fila[9] = cl.getTransfusiones();
            fila[10] = cl.getMedicamentos();
            fila[11] = cl.getObservaciones();
            fila[12] = cl.getIdUsuario();
            
            return fila;
        }).forEachOrdered(temp::addRow);
    }
    
    //Metodo para habilitar y deshabilitar botones
    private void habilitarBotones(boolean guardar, boolean editar, boolean eliminar, boolean buscar){
    
            this.jBtnGuardar.setEnabled(guardar);
            this.jBtnEditar.setEnabled(editar);
            this.jBtnEliminar.setEnabled(eliminar);
            this.jBtnBuscar.setEnabled(buscar);
    }
    
    //Metodo Limpiar las TextField
    private void limpiarTextField(){
        this.jTFIdentidad.setText("");
        this.jTFFEcha.setText("");
        this.jTFUsuario.setText("");
        this.jTFObservaciones.setText("");
        this.jTFMedicamentos.setText("");
    }
    //Metodo para validar la TextField
    private boolean validarTextField(){
        boolean estado;
        
        estado = !this.jBtnBuscar.getText().equals("");
        return estado;
    }
    
    //Metodo para insertar Historias Clinicas
    private void insertarHistoriaClinica(){
        if(!validarTextField ()){
            JOptionPane.showMessageDialog(null, "Ingrese Numero de Identidad", "SIMEC", JOptionPane.INFORMATION_MESSAGE);
            this.jTFIdentidad.requestFocus();
        }else {
            try {
                CDHistoriaClinica cdh = new CDHistoriaClinica();
                CLHistoriaClinica clh = new CLHistoriaClinica();
                
                clh.setNumeroIdentidad(this.jTFIdentidadNuevo.getText().trim());
                clh.setFechaCreacion(this.jTFFEcha.getText().trim());
                clh.setIdUsuario(Integer.parseInt(this.jTFUsuario.getText().trim()));
                clh.setObservaciones(this.jTFObservaciones.getText().trim());
                clh.setMedicamentos(this.jTFMedicamentos.getText().trim());
                clh.setCardiobasculares(this.jRBCardiobascular.getText());
                clh.setPulmonares(this.jRBPulmonar.getText());
                clh.setDigestivo(this.jRBDigestivo.getText());
                clh.setDiavetes(this.jRBDiabetes.getText());
                clh.setRenales(this.jRBRenales.getText());
                clh.setQuirurgicos(this.jRBQuirurgicos.getText());
                clh.setAlergicos(this.jRBAlergicos.getText());
                clh.setTransfusiones(this.jRBTransfusiones.getText()); 
                
                cdh.insertarHistoriaClinica(clh);
                
                JOptionPane.showMessageDialog(null, "REgistro Almacenado Satisfactoriamente", "SIMEC", JOptionPane.INFORMATION_MESSAGE);
                
            }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al Almacenar el registro" + ex);
            this.jTFIdentidad.requestFocus();
            }
        
        }
    }
    
   //Metodo llamado a insertar Historia Clinica
    private void guardar() throws SQLException {
        insertarHistoriaClinica();
        poblarTablaHistoriaClinica();
        this.jTFIdentidadNuevo.requestFocus();
        habilitarBotones(true, false, false, false);
        
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
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblHistoria = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jBtnEditar = new javax.swing.JButton();
        jBtnBuscar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTFIdentidad = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jTFFEcha = new javax.swing.JTextField();
        jTFIdentidadNuevo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFUsuario = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTFObservaciones = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jRBCardiobascular = new javax.swing.JRadioButton();
        jRBPulmonar = new javax.swing.JRadioButton();
        jRBDigestivo = new javax.swing.JRadioButton();
        jRBDiabetes = new javax.swing.JRadioButton();
        jRBRenales = new javax.swing.JRadioButton();
        jRBQuirurgicos = new javax.swing.JRadioButton();
        jRBAlergicos = new javax.swing.JRadioButton();
        jRBTransfusiones = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTFMedicamentos = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jBtnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(79, 198, 203));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SISTEMA MEDICO CLINICO SIMEC");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(183, 183, 183)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historia Clinica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jTblHistoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero ID", "Fecha Creacion", "Cardiobasculares", "Pulmonares", "Digestivo", "Diabetes", "Renales", "Quirurgicos", "Alergicos", "Transfuciones", "Medicamentos", "Observaciones", "Id Usuario"
            }
        ));
        jTblHistoria.setShowGrid(true);
        jScrollPane1.setViewportView(jTblHistoria);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(79, 198, 203));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Controles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jBtnEditar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnEditar.setText("Editar");

        jBtnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnBuscar.setText("Buscar");

        jBtnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnEliminar.setText("Eliminar");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Ingrese Numero de Identidad");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTFIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(79, 203, 146));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cargar Historia Clinica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Numero Identidad");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Creacion");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Usuario");

        jTFUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFUsuarioActionPerformed(evt);
            }
        });

        jTFObservaciones.setColumns(20);
        jTFObservaciones.setRows(5);
        jScrollPane2.setViewportView(jTFObservaciones);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Observaciones");

        jPanel5.setBackground(new java.awt.Color(79, 203, 146));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Antecedentes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jRBCardiobascular.setBackground(new java.awt.Color(79, 203, 146));
        jRBCardiobascular.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBCardiobascular.setText("CardioBasculares");
        jRBCardiobascular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBCardiobascularActionPerformed(evt);
            }
        });

        jRBPulmonar.setBackground(new java.awt.Color(79, 203, 146));
        jRBPulmonar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBPulmonar.setText("Pulmonares");
        jRBPulmonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBPulmonarActionPerformed(evt);
            }
        });

        jRBDigestivo.setBackground(new java.awt.Color(79, 203, 146));
        jRBDigestivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBDigestivo.setText("Digestivo");

        jRBDiabetes.setBackground(new java.awt.Color(79, 203, 146));
        jRBDiabetes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBDiabetes.setText("Diabetes");

        jRBRenales.setBackground(new java.awt.Color(79, 203, 146));
        jRBRenales.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBRenales.setText("Renales");

        jRBQuirurgicos.setBackground(new java.awt.Color(79, 203, 146));
        jRBQuirurgicos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBQuirurgicos.setText("Quirurgicos");

        jRBAlergicos.setBackground(new java.awt.Color(79, 203, 146));
        jRBAlergicos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBAlergicos.setText("Alergicos");

        jRBTransfusiones.setBackground(new java.awt.Color(79, 203, 146));
        jRBTransfusiones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRBTransfusiones.setText("Transfusiones");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRBRenales)
                    .addComponent(jRBQuirurgicos)
                    .addComponent(jRBAlergicos)
                    .addComponent(jRBTransfusiones)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jRBCardiobascular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRBPulmonar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRBDigestivo)
                    .addComponent(jRBDiabetes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRBCardiobascular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBPulmonar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBDigestivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBDiabetes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBRenales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBQuirurgicos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBAlergicos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBTransfusiones)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTFMedicamentos.setColumns(20);
        jTFMedicamentos.setRows(5);
        jScrollPane3.setViewportView(jTFMedicamentos);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Medicamentos");

        jBtnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnGuardar.setText("Agregar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFFEcha, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6)
                        .addComponent(jLabel4)
                        .addComponent(jTFIdentidadNuevo)
                        .addComponent(jTFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFIdentidadNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFFEcha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        this.dispose();
    }//GEN-LAST:event_jLabel3MousePressed

    private void jTFUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFUsuarioActionPerformed

    private void jRBCardiobascularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBCardiobascularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBCardiobascularActionPerformed

    private void jRBPulmonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBPulmonarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBPulmonarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Almacenar el registro" + ex);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(JFraHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraHistoriaClinica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFraHistoriaClinica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBuscar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRBAlergicos;
    private javax.swing.JRadioButton jRBCardiobascular;
    private javax.swing.JRadioButton jRBDiabetes;
    private javax.swing.JRadioButton jRBDigestivo;
    private javax.swing.JRadioButton jRBPulmonar;
    private javax.swing.JRadioButton jRBQuirurgicos;
    private javax.swing.JRadioButton jRBRenales;
    private javax.swing.JRadioButton jRBTransfusiones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFFEcha;
    private javax.swing.JTextField jTFIdentidad;
    private javax.swing.JTextField jTFIdentidadNuevo;
    private javax.swing.JTextArea jTFMedicamentos;
    private javax.swing.JTextArea jTFObservaciones;
    private javax.swing.JTextField jTFUsuario;
    private javax.swing.JTable jTblHistoria;
    // End of variables declaration//GEN-END:variables
}
