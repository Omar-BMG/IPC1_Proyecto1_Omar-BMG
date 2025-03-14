
package interfaces;

import archivo.ArchivoBinarioPatron;
import java.awt.Color;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class EliminarPatron extends javax.swing.JFrame {
    Administrador menuAdmin;
    /**
     * Creates new form EliminarPatron
     */
    public EliminarPatron(Administrador menuAdmin) {
        initComponents();
        this.getContentPane().setBackground(Color.BLACK);
        this.menuAdmin = menuAdmin;
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
        txtCodigoEliminarPatron = new javax.swing.JTextField();
        btnEliminarPatron = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Patrón");
        setResizable(false);

        labelTitulo.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Eliminar Patrón");

        labelCodigo.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        labelCodigo.setForeground(new java.awt.Color(255, 255, 255));
        labelCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCodigo.setText("Código:");

        btnEliminarPatron.setBackground(new java.awt.Color(153, 153, 153));
        btnEliminarPatron.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnEliminarPatron.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarPatron.setText("Eliminar");
        btnEliminarPatron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPatronActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodigoEliminarPatron, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btnEliminarPatron, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigoEliminarPatron, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarPatron, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnEliminarPatronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPatronActionPerformed
        //Instanciamos nuestro manejador de archivos binarios de patrones
        ArchivoBinarioPatron archivo = new ArchivoBinarioPatron();
        //Definimos la ruta del archivo del patrón
        String filePath = ("Patrón_"+txtCodigoEliminarPatron.getText()+".html");
        //Validamos que el campo de texto no esté vacío
        if(txtCodigoEliminarPatron.getText().length() != 0) {
            //Lamamos al procedimiento eliminarContenido definido en el binario y le enviamos el código ingresdo en el campo de texto
            archivo.eliminarContenido("patrones.bin", txtCodigoEliminarPatron.getText());
            eliminarArchivoPatron(filePath); //También eliminamos el archivo html asociado
            this.menuAdmin.actualizarTablaPatrones();
            txtCodigoEliminarPatron.setText("");
        }
    }//GEN-LAST:event_btnEliminarPatronActionPerformed
    
    private void eliminarArchivoPatron(String filePath) {
        try{
            File archivo = new File(filePath); //Instanciamos el archivo enviandole al constructor la dirección deseada
            if(archivo.delete()){ //El .delete nos devuelve un booleano en caso se elimine el archivo deseado
                JOptionPane.showMessageDialog(this, "Patrón eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el archivo");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarPatron;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTextField txtCodigoEliminarPatron;
    // End of variables declaration//GEN-END:variables
}
