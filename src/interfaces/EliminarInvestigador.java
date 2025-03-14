
package interfaces;

import archivo.ArchivoBinarioInvestigador;
import java.awt.Color;

/**
 *
 * @author Omar
 */
public class EliminarInvestigador extends javax.swing.JFrame {
    Administrador ventanaAdmin;
    /**
     * Creates new form EliminarInvestigador
     */
    public EliminarInvestigador(Administrador ventanaAdmin) {
        this.ventanaAdmin = ventanaAdmin;
        initComponents();
        this.getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        txtCodigoEliminarInvestigador = new javax.swing.JTextField();
        btnEliminarInvestigador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        labelTitulo.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Eliminar Investigador");

        labelCodigo.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        labelCodigo.setForeground(new java.awt.Color(255, 255, 255));
        labelCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCodigo.setText("Código:");

        btnEliminarInvestigador.setBackground(new java.awt.Color(153, 153, 153));
        btnEliminarInvestigador.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnEliminarInvestigador.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarInvestigador.setText("Eliminar");
        btnEliminarInvestigador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInvestigadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarInvestigador, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoEliminarInvestigador, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigoEliminarInvestigador, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarInvestigador, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarInvestigadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInvestigadorActionPerformed
        //Instanciamos nuestro manejador de archivos binarios de investigadores
        ArchivoBinarioInvestigador archivo = new ArchivoBinarioInvestigador();
        //Validamos que el campo de texto no esté vacío
        if(txtCodigoEliminarInvestigador.getText().length() != 0) {
            //Lamamos al procedimiento eliminarContenido definido en el binario y le enviamos el código ingresdo en el campo de texto
            archivo.eliminarContenido("investigadores.bin", txtCodigoEliminarInvestigador.getText());
            this.ventanaAdmin.actualizarTabla();
            this.ventanaAdmin.eliminarContenidoComboboxInvestigador(txtCodigoEliminarInvestigador.getText());
            txtCodigoEliminarInvestigador.setText("");
        }
    }//GEN-LAST:event_btnEliminarInvestigadorActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarInvestigador;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTextField txtCodigoEliminarInvestigador;
    // End of variables declaration//GEN-END:variables
}
