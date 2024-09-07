
package ipc_quimik;

/**
 *
 * @author Omar
 */
//Esta clase contendrá las funciones con los procedimientos necesarios para el analisis de experimentos de la ventana investigador
public class AlgoritmoAnalisis {
    
    public int [][] ObtenerMatrizTemp1(int [][] matrizMuestra, int N) {
        //Creamos la matriz temporal 1
        int [][] matrizTemporal1 = new int[N][N];
        //Le asignamos valores, la matrizTemporal1 será igual a la matrizMuestra por 3.
        for (int i = 0; i < N; i++) { //Recorremos las filas
            for (int j = 0; j < N; j++) { //Recorremos las columnas
                matrizTemporal1[i][j] = matrizMuestra[i][j]*3;
            }
        }
        return matrizTemporal1;
    }
    
    public int [][] ObtenerMatrizTemp2(int [][] matrizMuestra, int N) {
        //Creamos la matriz temporal 2
        int [][] matrizTemporal2 = new int[N][N];
        //Le asignamos valores, la matrizTemporal2 será igual a la matrizMuestra por 7.
        for (int i = 0; i < N; i++) { //Recorremos las filas
            for (int j = 0; j < N; j++) { //Recorremos las columnas
                matrizTemporal2[i][j] = matrizMuestra[i][j]*7;
            }
        }
        return matrizTemporal2;
    }
    
    public int[][] ObtenerMatrizTemp3(int [][] matrizTemp1, int[][] matrizTemp2, int N) {
        //Creamos la matriz temporal 3
        int [][] matrizTemporal3 = new int[N][N];
        //Le asignamos valores, la matrizTemporal3 será igual a la multiplicacion de la Temporal 1 y 2
        for (int i = 0; i < N; i++) { //Filas
            for (int j = 0; j < N; j++) { //Columnas
                for (int m = 0; m < N; m++) { //Filas o columnas
                    matrizTemporal3[i][j] += matrizTemp1[i][m]*matrizTemp2[m][j];
                }
            }
        }
        return matrizTemporal3;
    }
    
    public int [][] ObtenerMatrizResultante(int [][] matrizTemporal3, int N) {
        //Creamos la matriz resultante
        int [][] matrizResultante = new int[N][N];
        //Le asignamos valores, la matrizResultante será igual a la división modular de 2 con cada valor de la misma.
        for (int i = 0; i < N; i++) { //Recorremos las filas
            for (int j = 0; j < N; j++) { //Recorremos las columnas
                matrizResultante[i][j] = matrizTemporal3[i][j]%2;
            }
        }
        return matrizResultante;
    }
    
    public boolean CompararMatrices(int [][] matrizResultante, int [][] matrizPatron, int maxSizeResultante, int maxSizePatron) {
        boolean resultado = true;
        //Validamos que el tamaño de las matrices cuadradas sea el mismo
        if(maxSizeResultante == maxSizePatron) { //Sólo si las matrices tienen el mismo tamaño ejecuta esto
            
            //Comparamos cada elemento de la matrizResultante y la matriz Patron
            for (int i = 0; i < maxSizePatron; i++) { //Recorremos las filas
                for (int j = 0; j < maxSizePatron; j++) { //Recorremos las columnas
                    if (matrizResultante[i][j] != matrizPatron[i][j]){
                        resultado = false;
                    }
                }
            }
        } else {
            resultado = false;
        }
        
        return resultado;
    }
    
}
