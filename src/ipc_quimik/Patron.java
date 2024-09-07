
package ipc_quimik;

import java.io.Serializable;

/**
 *
 * @author 162D6LA
 */
public class Patron implements Serializable{
    String codigo;
    String nombre;
    
    //Constructor

    public Patron(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    //Getters

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }
    
    //Setters

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
