
package ipc_quimik;

import archivo.ArchivoBinarioAnalisisExp;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Omar
 */
public class RenderBotonVerResultados extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener{
    private JButton button;
    private int fila;
    private JTable table;

    public RenderBotonVerResultados() {
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
            int columnaEspecifica = 0; //Obtendremos el valor de la columna 0, que es el código del analisis
            Object valor = table.getValueAt(fila, columnaEspecifica); //Obtenemos el valor del código
            int valorInt = (int) valor;
            String rutaPatron = ""; //En la variable rutaPatron guardaremos la dirección de los analisis
            
            //Ahora instanciamos el archivo binario de los analisis y almacenamos la lista de analisis actuales en un arrayList
            ArchivoBinarioAnalisisExp archivoAnalisis = new ArchivoBinarioAnalisisExp();
            ArrayList<Analisis> listadoAnalisis = archivoAnalisis.obtenerContenido("analisis.bin");
            
            //Recorremos el listadoAnalisis en busca de la coincidencia
            for(Analisis tempAnalisis : listadoAnalisis) {
                if(tempAnalisis.getNoAnalsis() == valorInt){ //El analisis cuyo codigo coincida con el codigo obtenido de la tabla en la posición cero
                    rutaPatron = tempAnalisis.getRutaReporte(); //Obtenemos la ruta del reporte de ese analisis y lo guardamos
                }
            }
            
            
            Desktop.getDesktop().open(new File(rutaPatron)); //abrmos el archivo con la ruta obtenida
        } catch (IOException ex) {
            System.out.println(ex);
        }
        fireEditingStopped(); //Detenemos la edición
    } 
}
