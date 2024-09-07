
package archivo;

import ipc_quimik.Muestra;
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
public class ArchivoBinarioMuestra {
    public void agregarContenido(String ruta_archivo, Muestra muestra) {
        try{
            //Obtenemos el listado de muestras en caso ya exista un archivo binario
            List<Muestra> listado_muestras = this.obtenerContenido(ruta_archivo);
            listado_muestras.add(muestra);
           
            FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
            salidaObjeto.writeObject(listado_muestras);
            salidaArchivo.close();
            salidaObjeto.close();
            
            
        } catch(Exception e) {
            System.out.println("Error al agregar contenido: " + e.getMessage());
        }
    }
    
    public ArrayList<Muestra> obtenerContenido(String ruta_archivo){
        //Creamos un ArrayList donde se almacenaran las muestras obtenidas del binario
        ArrayList<Muestra> listaMuestrasActual = new ArrayList<>();
        try {
            //Verificamos si el archivo existe
            File archivo = new File(ruta_archivo); //Creamos el archivo enviando la ruta del existente al constructor
            if(archivo.exists()) {
                FileInputStream entradaArchivo = new FileInputStream(ruta_archivo); //Ubicamos el archivo
                ObjectInputStream entradaObjeto = new ObjectInputStream(entradaArchivo); //Leemos los objetos
                //Por medio de un casteo, convertimos los Objetos leidos a una lista dinámica de tipo Muestra
                listaMuestrasActual = (ArrayList<Muestra>)entradaObjeto.readObject(); 
            }
            
        } catch (Exception e) {
            System.out.println("Error al obtener el contenido: "+e.getMessage());
        }
        
        return listaMuestrasActual;
    }
    
    public void modificarEstadoMuestra (String ruta_archivo, String codigo, String nuevoEstado){
        try {
           //Obtenemos el listado de investidadores del archivo binario existente
           List<Muestra> listado_muestras = this.obtenerContenido(ruta_archivo);
           //Para filtrar la modificación a realizar, recorremos con un For y comparamos el codigo recibido por parámetro
           for (Muestra tempMuestra : listado_muestras) {
               if(tempMuestra.getCodigo().equals(codigo)) {
                   tempMuestra.setEstado(nuevoEstado);
               }
           }
           //Luego de haber modificado la lista con el ciclo for, a continuación realiza la sobrescritura en el binario
           
           FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
           ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
           salidaObjeto.writeObject(listado_muestras);
           salidaArchivo.close();
           salidaObjeto.close();
            
        } catch (Exception e) {
            System.out.println("Error al agregar contenido: "+e.getMessage());
        }
    }
}
