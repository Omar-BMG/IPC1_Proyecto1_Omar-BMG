
package archivo;

import ipc_quimik.Analisis;
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
public class ArchivoBinarioAnalisisExp {
    public void agregarContenido(String ruta_archivo, Analisis analisis) {
        try{
            //Obtenemos el listado de analisis en caso ya exista un archivo binario
            List<Analisis> listado_analisis = this.obtenerContenido(ruta_archivo);
            listado_analisis.add(analisis);
           
            FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
            salidaObjeto.writeObject(listado_analisis);
            salidaArchivo.close();
            salidaObjeto.close();
            
            
        } catch(Exception e) {
            System.out.println("Error al agregar contenido: " + e.getMessage());
        }
    }
    
    public ArrayList<Analisis> obtenerContenido(String ruta_archivo){
        //Creamos un ArrayList donde se almacenaran los analisis obtenidos del binario
        ArrayList<Analisis> listaAnalisisActual = new ArrayList<>();
        try {
            //Verificamos si el archivo existe
            File archivo = new File(ruta_archivo); //Creamos el archivo enviando la ruta del existente al constructor
            if(archivo.exists()) {
                FileInputStream entradaArchivo = new FileInputStream(ruta_archivo); //Ubicamos el archivo
                ObjectInputStream entradaObjeto = new ObjectInputStream(entradaArchivo); //Leemos los objetos
                //Por medio de un casteo, convertimos los Objetos leidos a una lista dinámica de tipo Analisis
                listaAnalisisActual = (ArrayList<Analisis>)entradaObjeto.readObject(); 
            }
            
        } catch (Exception e) {
            System.out.println("Error al obtener el contenido: "+e.getMessage());
        }
        
        return listaAnalisisActual;
    }
}
