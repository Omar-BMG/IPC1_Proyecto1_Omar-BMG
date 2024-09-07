
package archivo;

import ipc_quimik.Patron;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Omar
 */
public class ArchivoBinarioPatron {
    public void agregarContenido(String ruta_archivo, Patron patron) {
        try{
            //Obtenemos el listado de patrones en caso ya exista un archivo binario
            List<Patron> listado_patrones = this.obtenerContenido(ruta_archivo);
            listado_patrones.add(patron);
           
            FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
            salidaObjeto.writeObject(listado_patrones);
            salidaArchivo.close();
            salidaObjeto.close();
            
            
        } catch(Exception e) {
            System.out.println("Error al agregar contenido: " + e.getMessage());
        }
    }
    
    public ArrayList<Patron> obtenerContenido(String ruta_archivo){
        //Creamos un ArrayList donde se almacenaran los patrones obtenidos del binario
        ArrayList<Patron> listaPatronesActual = new ArrayList<>();
        try {
            //Verificamos si el archivo existe
            File archivo = new File(ruta_archivo); //Creamos el archivo enviando la ruta del existente al constructor
            if(archivo.exists()) {
                FileInputStream entradaArchivo = new FileInputStream(ruta_archivo); //Ubicamos el archivo
                ObjectInputStream entradaObjeto = new ObjectInputStream(entradaArchivo); //Leemos los objetos
                //Por medio de un casteo, convertimos los Objetos leidos a una lista dinámica de tipo Muestra
                listaPatronesActual = (ArrayList<Patron>)entradaObjeto.readObject(); 
            }
            
        } catch (Exception e) {
            System.out.println("Error al obtener el contenido: "+e.getMessage());
        }
        
        return listaPatronesActual;
    }
    
    public void eliminarContenido(String ruta_archivo, String codigo){
        try {
           //Obtenemos el listado de patrones del archivo binario existente
           List<Patron> listado_patrones = this.obtenerContenido(ruta_archivo);
           //Recorremos el listado de investigadores hasta obtener el que coincida con el código y lo eliminamos.
           for (int i = 0; i<listado_patrones.size(); i++) {
               if(listado_patrones.get(i).getCodigo().equals(codigo)) {
                   listado_patrones.remove(i);
               }
           }
           //Luego de haber modificado la lista con el ciclo for, a continuación realiza la sobrescritura en el binario
           
           FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
           ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
           salidaObjeto.writeObject(listado_patrones);
           salidaArchivo.close();
           salidaObjeto.close();
            
        } catch (Exception e) {
            System.out.println("Error al agregar contenido: "+e.getMessage());
        }
        
    }
}
