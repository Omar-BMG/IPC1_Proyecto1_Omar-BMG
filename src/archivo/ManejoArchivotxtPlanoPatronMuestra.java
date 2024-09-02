
package archivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


/**
 *
 * @author Omar
 */
public class ManejoArchivotxtPlanoPatronMuestra {
    
    public void crearArchivo(String newRuta_archivo) {
        try {
            FileWriter archivo = new FileWriter(newRuta_archivo);
            archivo.close(); //El archivo se ha creado correctamente
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void agregarContenido(String contenido, String newRuta_archivo){
        try {
            FileWriter archivo = new FileWriter(newRuta_archivo, true); // Si no existe el archivo, lo crea, si ya existe, lo sobreescribe.
            archivo.write(contenido + "\n");
            archivo.close(); //Cerramos el archivo, ya ha agregado el contenido.
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void leerCSVpatron(String filePath, String newRuta_archivo){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String linea;
            
            while ((linea = br.readLine()) != null) {              
                String contenido = linea;
                agregarContenido(contenido, newRuta_archivo);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
