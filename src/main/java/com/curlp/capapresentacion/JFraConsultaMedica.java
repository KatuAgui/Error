/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDConsultaMedica;
import com.curlp.capalogica.CLConsultaMedica;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
    private void poblarTablaConsultas () throws SQLException {
        limpiarTabla();
        CDConsultaMedica cm = new CDConsultaMedica();
        List<CLConsultaMedica> myList = cm.obtenerListaConsultaMedica();
        DefaultTableModel temp = (DefaultTableModel) this.jTblConsultasMedicas.getModel();
        
        myList.stream().map((CLConsultaMedica cd)-> {
            Object[] fila = new Object[20];
            fila[0] = cd.getIdConsultasMedicas();
            fila[1] = cd.getNumeroIdentidad();
            fila[2] = cd.getPrimerNombre();
            fila[3] = cd.getSegundoNombre();
            fila[4] = cd.getPrimerApellido();
            fila[5] = cd.getSegundoApellido();
            fila[6] = cd.getAntecentesFamiliares();
            fila[7] = cd.getDireccion();
            fila[8] = cd.getTelefonoCelular();
            fila[9] = cd.getPeso();
            fila[10] = cd.getEstatura();
            fila[11] = cd.getSexo();
            fila[12] = cd.getFechaIngreso();
            fila[13] = cd.getObservaciones();
            fila[14] = cd.getRecetasMedicas();
            fila[15] = cd.getNombreUsuario();
            fila[16] = cd.getUsuarioPrimerNombre(); 
            fila[17] = cd.getUsuarioPrimerApellido();
            fila[18] = cd.getCargo();
            fila[19] = cd.getIdUsuario();
            return fila;
        }).forEachOrdered(temp::addRow);
    
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblConsultasMedicas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jBtnMostrarConsultas = new javax.swing.JButton();
        jBtnAgregarNuevaConsulta = new javax.swing.JButton();
        jBtnEditarInformacion = new javax.swing.JButton();
        jBtnEliminarConsulta = new javax.swing.JButton();
        jBtnBusqedaFiltrada = new javax.swing.JButton();
        jTFNumeroIdentidadBusquedaFiltrada = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(1, 1, 1));

        jLabel1.setText("Consultas Medicas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(415, 415, 415)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Consultas"));

        jTblConsultasMedicas.setForeground(new java.awt.Color(19, 50, 103));
        jTblConsultasMedicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Consulta Medica", "Numero Identidad", "Primer Nombre", "Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Antecedentes Familiares", "Direccion", "Telefono Celular", "Peso", "Estatura", "Sexo", "Fecha de Ingreso", "Observaciones", "Recetas Medicas", "Usuario", "1er Nombre Usuario", "1er Apellido Usuario", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTblConsultasMedicas);
        jTblConsultasMedicas.getAccessibleContext().setAccessibleParent(jTblConsultasMedicas);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(252, 199, 7));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles"));

        jBtnMostrarConsultas.setText("Mostrar Consultas");

        jBtnAgregarNuevaConsulta.setText("Agregar Nueva Consulta");

        jBtnEditarInformacion.setText("Editar Informacion");

        jBtnEliminarConsulta.setText("Eliminar Consulta");

        jBtnBusqedaFiltrada.setText("Busqueda Filtrada");

        jTFNumeroIdentidadBusquedaFiltrada.setEnabled(false);

        jLabel2.setForeground(new java.awt.Color(1, 1, 1));
        jLabel2.setText("Numero de Identidad Para Busqeda Filtrada");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnMostrarConsultas)
                        .addGap(37, 37, 37)
                        .addComponent(jBtnAgregarNuevaConsulta)
                        .addGap(46, 46, 46)
                        .addComponent(jBtnEditarInformacion)
                        .addGap(53, 53, 53)
                        .addComponent(jBtnEliminarConsulta)
                        .addGap(50, 50, 50)
                        .addComponent(jBtnBusqedaFiltrada))
                    .addComponent(jTFNumeroIdentidadBusquedaFiltrada, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(369, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnMostrarConsultas)
                    .addComponent(jBtnAgregarNuevaConsulta)
                    .addComponent(jBtnEditarInformacion)
                    .addComponent(jBtnEliminarConsulta)
                    .addComponent(jBtnBusqedaFiltrada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNumeroIdentidadBusquedaFiltrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JButton jBtnAgregarNuevaConsulta;
    private javax.swing.JButton jBtnBusqedaFiltrada;
    private javax.swing.JButton jBtnEditarInformacion;
    private javax.swing.JButton jBtnEliminarConsulta;
    private javax.swing.JButton jBtnMostrarConsultas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFNumeroIdentidadBusquedaFiltrada;
    private javax.swing.JTable jTblConsultasMedicas;
    // End of variables declaration//GEN-END:variables
}
