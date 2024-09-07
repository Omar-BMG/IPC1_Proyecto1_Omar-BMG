
package archivo;

import ipc_quimik.AsignacionExperimento;
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
public class ArchivoBinarioAsignacionExp {
    public void agregarContenido(String ruta_archivo, AsignacionExperimento asignacion) {
        try{
            //Obtenemos el listado de asignaciones en caso ya exista un archivo binario
            List<AsignacionExperimento> listado_asignaciones = this.obtenerContenido(ruta_archivo);
            listado_asignaciones.add(asignacion);
           
            FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
            salidaObjeto.writeObject(listado_asignaciones);
            salidaArchivo.close();
            salidaObjeto.close();
            
            
        } catch(Exception e) {
            System.out.println("Error al agregar contenido: " + e.getMessage());
        }
    }
    
    public ArrayList<AsignacionExperimento> obtenerContenido(String ruta_archivo){
        //Creamos un ArrayList donde se almacenaran las asignaciones obtenidas del binario
        ArrayList<AsignacionExperimento> listaAsignacionesActual = new ArrayList<>();
        try {
            //Verificamos si el archivo existe
            File archivo = new File(ruta_archivo); //Creamos el archivo enviando la ruta del existente al constructor
            if(archivo.exists()) {
                FileInputStream entradaArchivo = new FileInputStream(ruta_archivo); //Ubicamos el archivo
                ObjectInputStream entradaObjeto = new ObjectInputStream(entradaArchivo); //Leemos los objetos
                //Por medio de un casteo, convertimos los Objetos leidos a una lista dinámica de tipo AsignacionExperimento
                listaAsignacionesActual = (ArrayList<AsignacionExperimento>)entradaObjeto.readObject(); 
            }
            
        } catch (Exception e) {
            System.out.println("Error al obtener el contenido: "+e.getMessage());
        }
        
        return listaAsignacionesActual;
    }
    
    public void eliminarContenido(String ruta_archivo, String codigo){
        try {
           //Obtenemos el listado de asignaciones del archivo binario existente
           List<AsignacionExperimento> listado_asignaciones = this.obtenerContenido(ruta_archivo);
           //Recorremos el listado de asignaciones hasta obtener el que coincida con el código y lo eliminamos.
           for (int i = 0; i<listado_asignaciones.size(); i++) {
               if(listado_asignaciones.get(i).getCodigoMuestraAsignada().equals(codigo)) {
                   listado_asignaciones.remove(i);
               }
           }
           //Luego de haber modificado la lista con el ciclo for, a continuación realiza la sobrescritura en el binario
           
           FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
           ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
           salidaObjeto.writeObject(listado_asignaciones);
           salidaArchivo.close();
           salidaObjeto.close();
            
        } catch (Exception e) {
            System.out.println("Error al agregar contenido: "+e.getMessage());
        }
        
    }
    
    
}
