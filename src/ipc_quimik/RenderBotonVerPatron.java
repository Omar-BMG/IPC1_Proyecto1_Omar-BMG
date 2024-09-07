
package ipc_quimik;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Omar
 */
public class RenderBotonVerPatron extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener{
    private JButton button;
    private int fila;
    private JTable table;

    public RenderBotonVerPatron() {
        button = new JButton();
        button.setText("Ver");
        button.addActionListener(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.table = table;
        fila = row;
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        fila = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return fila;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int columnaEspecifica = 0; //Obtendremos el valor de la columna 0, que es el código del patrón
            Object valor = table.getValueAt(fila, columnaEspecifica); //Obtenemos el valor del código
            String rutaPatron = ("Patrón_"+valor+".html"); //En la variable rutaPatron guardamos la dirección de los patrones
            
            Desktop.getDesktop().open(new File(rutaPatron));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        fireEditingStopped(); //Detenemos la edición
    } 
}
