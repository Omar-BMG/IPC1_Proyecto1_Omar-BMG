
package archivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.Math.sqrt;
import java.util.ArrayList;


/**
 *
 * @author Omar
 */
public class ManejoArchivotxtPlanoPatron {
    
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
            archivo.write(contenido + ",<br>");
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
    
    //Función que retorna un arreglo de dos dimensiones que representa la matriz.html leída
    public int [][] obtenerMatriz (String filePath){
        int N = 0;
        N = obtenerLongitudMatriz(filePath); //Esta variable representa el tamaño de filas y columnas que tendrá la matriz
        String[] matrizLeida = new String[N]; //La matriz leída tendrá tamaño N*N
        
        int filasColumnasCuadradas = (int) sqrt(N-1);
        
        int [][] matrizCompletada = new int [filasColumnasCuadradas][filasColumnasCuadradas]; //Creamos la matriz bidimensional que se llenará y se retornará, N-1 para no tomar en cuenta el espacio en blanco
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String linea;
            
            //Primero obtenemos la matriz plana, es decir, no bidimensional
            while ((linea = br.readLine()) != null) {              
                matrizLeida = linea.split(","); //Llenamos la matrizLeida
                
            }
            //Recorremos la matrizLeida para limpiar todos los saltos de línea
            for(int i = 0; i < matrizLeida.length; i++) {
                matrizLeida[i] = matrizLeida[i].replaceAll("<br>", ""); //Eliminamos los saltos de línea que habían en el html
                
            }
            
            matrizCompletada = convertirMatriz(matrizLeida, (filasColumnasCuadradas)); //Llamamos a la función encargada de convertir la matriz leída a la matriz bidimensional, le enviamos el N-1 para no tomar en cuenta el último espacioen blanco
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return matrizCompletada;
        
    }
    
    //Funcion que retorna el tamaño de filas y columnas de la matriz que se está leyendo
    private int obtenerLongitudMatriz(String filePath) {
        int N = 0; //Representa el tamaño de las filas y las columnas de la matriz
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String linea;
                 
            while ((linea = br.readLine()) != null) {              
                String [] matrizLongitud = linea.split(","); //Llenamos la matrizLeida
                N = matrizLongitud.length;
            }
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return N;
    }
    
    //Función que convertirá la matriz leída a la matriz bidimensional que necesitamos
    private int [][] convertirMatriz (String [] matrizLeida, int N){
        int [][] matrizResultado = new int [N][N]; //Creamos la matriz que retornaremos
        int [] matrizLeidaEntera = new int [N*N]; //Creamos la matriz que servirá para volver entera la matrizLeida
        
        for (int i = 0; i < matrizLeida.length; i++) { //Ciclo for para llenar la matriz entera
            if (!(matrizLeida[i].equals(""))){
            matrizLeidaEntera[i] =  Integer.parseInt(matrizLeida[i]);
            }
        }
        
        int contador = 0;
        while (contador < matrizLeidaEntera.length) {    
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrizResultado[i][j] = matrizLeidaEntera[contador];
                    contador++;
                }
            }
        }
        return matrizResultado;
    }
   
    //Funcion que retorna el tamaño REAL de filas y columnas de la matriz que se está leyendo
    public int obtenerLongitudMatrizReal(String filePath) {
        int N = 0; //Representa el tamaño de las filas y las columnas de la matriz
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String linea;
                 
            
            while ((linea = br.readLine()) != null) {              
                String [] matrizLongitud = linea.split(","); //Llenamos la matrizLeida
                N = (int) sqrt((matrizLongitud.length)-1);
            }
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return (N);
    }
    
    public void agregarContenidoReporte(String contenido, String rutaReporte){
        try {
            FileWriter archivo = new FileWriter(rutaReporte, true); // Si no existe el archivo, lo crea, si ya existe, lo sobreescribe.
            archivo.write(contenido + "<br>");
            archivo.close(); //Cerramos el archivo, ya ha agregado el contenido.
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void agregarMatrizReporte(String contenido, String rutaReporte){
        try {
            FileWriter archivo = new FileWriter(rutaReporte, true); // Si no existe el archivo, lo crea, si ya existe, lo sobreescribe.
            archivo.write(contenido);
            archivo.close(); //Cerramos el archivo, ya ha agregado el contenido.
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
