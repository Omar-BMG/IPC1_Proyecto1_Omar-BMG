
package ipc_quimik;

import java.io.Serializable;

/**
 *
 * @author Omar
 */
public class Muestra implements Serializable{
    String codigo;
    String descripcion;
    String estado;
    
    
    //Constructor
    public Muestra(String codigo, String descripcion, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
    }
    
    //Getters
    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }
    
    //Setters

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
