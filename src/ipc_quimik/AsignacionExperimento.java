
package ipc_quimik;

import java.io.Serializable;

/**
 *
 * @author Omar
 */
public class AsignacionExperimento implements Serializable{
    String codigoMuestraAsignada;
    String codigoInvestigadorAsignado;

    public AsignacionExperimento(String codigoMuestraAsignada, String codigoInvestigadorAsignado) {
        this.codigoMuestraAsignada = codigoMuestraAsignada;
        this.codigoInvestigadorAsignado = codigoInvestigadorAsignado;
    }
    
    //Getters

    public String getCodigoMuestraAsignada() {
        return codigoMuestraAsignada;
    }

    public String getCodigoInvestigadorAsignado() {
        return codigoInvestigadorAsignado;
    }
    
    //Setters

    public void setCodigoMuestraAsignada(String codigoMuestraAsignada) {
        this.codigoMuestraAsignada = codigoMuestraAsignada;
    }

    public void setCodigoInvestigadorAsignado(String codigoInvestigadorAsignado) {
        this.codigoInvestigadorAsignado = codigoInvestigadorAsignado;
    }
    
}
