
package ipc_quimik;

import java.io.Serializable;

/**
 *
 * @author Omar
 */
public class Investigador implements Serializable{
    String codigo;
    String nombre;
    String genero;
    String contrasenia;
    int experimentos;

    //Contructor

    public Investigador(String codigo, String nombre, String genero, String contrasenia, int experimentos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.contrasenia = contrasenia;
        this.experimentos = experimentos;
    }

    public Investigador() {
    }

    

    
    
    
    //Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public int getExperimentos() {
        return experimentos;
    }
    
    //Setters

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setExperimentos(int experimentos) {
        this.experimentos = experimentos;
    }
    
    
}
