
package archivo;


import ipc_quimik.Investigador;
import java.util.List; //Cuidado con importar la lista de awt, debe ser de java.util
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public class ArchivoBinarioInvestigador {
    
    public void agregarContenido (String ruta_archivo, Investigador investigador) {
        try {
           //Obtenemos el listado de investidadores en caso ya exista un archivo binario
           List<Investigador> listado_investigadores = this.obtenerContenido(ruta_archivo);
           listado_investigadores.add(investigador);
           
           FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
           ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
           salidaObjeto.writeObject(listado_investigadores);
           salidaArchivo.close();
           salidaObjeto.close();
            
        } catch (Exception e) {
            System.out.println("Error al agregar contenido: "+e.getMessage());
        }
    } 
    
    public ArrayList<Investigador> obtenerContenido(String ruta_archivo){
        //Creamos un ArrayList donde se almacenaran los investigadores obtenidos del binario
        ArrayList<Investigador> listaInvestigadoresActual = new ArrayList<>();
        try {
            //Verificamos si el archivo existe
            File archivo = new File(ruta_archivo); //Creamos el archivo enviando la ruta del existente al constructor
            if(archivo.exists()) {
                FileInputStream entradaArchivo = new FileInputStream(ruta_archivo); //Ubicamos el archivo
                ObjectInputStream entradaObjeto = new ObjectInputStream(entradaArchivo); //Leemos los objetos
                //Por medio de un casteo, convertimos los Objetos leidos a una lista dinámica de tipo Investigador
                listaInvestigadoresActual = (ArrayList<Investigador>)entradaObjeto.readObject(); 
            }
            
        } catch (Exception e) {
            System.out.println("Error al obtener el contenido: "+e.getMessage());
        }
        
        return listaInvestigadoresActual;
    }
    
    public void modificarContenido (String ruta_archivo, String codigo, Investigador investigador){
        try {
           //Obtenemos el listado de investidadores del archivo binario existente
           List<Investigador> listado_investigadores = this.obtenerContenido(ruta_archivo);
           //Para filtrar la modificación a realizar, recorremos con un For y comparamos el codigo recibido por parámetro
           for (Investigador invest : listado_investigadores) {
               if(invest.getCodigo().equals(codigo)) {
                   invest.setNombre(investigador.getNombre());
                   invest.setGenero(investigador.getGenero());
                   invest.setContrasenia(investigador.getContrasenia());
               }
           }
           //Luego de haber modificado la lista con el ciclo for, a continuación realiza la sobrescritura en el binario
           
           FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
           ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
           salidaObjeto.writeObject(listado_investigadores);
           salidaArchivo.close();
           salidaObjeto.close();
            
        } catch (Exception e) {
            System.out.println("Error al agregar contenido: "+e.getMessage());
        }
    }
    
    public void eliminarContenido(String ruta_archivo, String codigo){
        try {
           //Obtenemos el listado de investidadores del archivo binario existente
           List<Investigador> listado_investigadores = this.obtenerContenido(ruta_archivo);
           //Recorremos el listado de investigadores hasta obtener el que coincida con el código y lo eliminamos.
           for (int i = 0; i<listado_investigadores.size(); i++) {
               if(listado_investigadores.get(i).getCodigo().equals(codigo)) {
                   listado_investigadores.remove(i);
               }
           }
           //Luego de haber modificado la lista con el ciclo for, a continuación realiza la sobrescritura en el binario
           
           FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
           ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
           salidaObjeto.writeObject(listado_investigadores);
           salidaArchivo.close();
           salidaObjeto.close();
            
        } catch (Exception e) {
            System.out.println("Error al agregar contenido: "+e.getMessage());
        }
    }
    
    public void modificarCantidadExp (String ruta_archivo, String codigo){
        try {
           //Obtenemos el listado de investidadores del archivo binario existente
           List<Investigador> listado_investigadores = this.obtenerContenido(ruta_archivo);
           //Para filtrar la modificación a realizar, recorremos con un For y comparamos el codigo recibido por parámetro
           for (Investigador tempInvest : listado_investigadores) {
               if(tempInvest.getCodigo().equals(codigo)) {
                   tempInvest.setExperimentos(tempInvest.getExperimentos()+1); //Añadimos la cantidad de experimentos que ya tenía más uno
               }
           }
           //Luego de haber modificado la lista con el ciclo for, a continuación realiza la sobrescritura en el binario
           
           FileOutputStream salidaArchivo = new FileOutputStream(ruta_archivo);
           ObjectOutputStream salidaObjeto = new ObjectOutputStream(salidaArchivo);
           salidaObjeto.writeObject(listado_investigadores);
           salidaArchivo.close();
           salidaObjeto.close();
            
        } catch (Exception e) {
            System.out.println("Error al agregar contenido: "+e.getMessage());
        }
    }
        
} 
