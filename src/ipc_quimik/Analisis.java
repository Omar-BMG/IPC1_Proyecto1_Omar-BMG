
package ipc_quimik;

import java.io.Serializable;

/**
 *
 * @author Omar
 */
public class Analisis implements Serializable{
    int NoAnalsis;
    String codigoInvestigador;
    String Muestra;
    String Patron;
    String Fecha;
    String Hora;
    String Resultado;
    String rutaReporte;

    public Analisis(int NoAnalsis, String codigoInvestigador, String Muestra, String Patron, String Fecha, String Hora, String Resultado, String rutaReporte) {
        this.NoAnalsis = NoAnalsis;
        this.codigoInvestigador = codigoInvestigador;
        this.Muestra = Muestra;
        this.Patron = Patron;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.Resultado = Resultado;
        this.rutaReporte = rutaReporte;
    }
     //Getters

    public String getCodigoInvestigador() {
        return codigoInvestigador;
    }
    
    public int getNoAnalsis() {
        return NoAnalsis;
    }

    public String getMuestra() {
        return Muestra;
    }

    public String getPatron() {
        return Patron;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public String getResultado() {
        return Resultado;
    }

    public String getRutaReporte() {
        return rutaReporte;
    }
    
    
    
    //Setters

    public void setNoAnalsis(int NoAnalsis) {
        this.NoAnalsis = NoAnalsis;
    }

    public void setCodigoInvestigador(String codigoInvestigador) {
        this.codigoInvestigador = codigoInvestigador;
    }
    

    public void setMuestra(String Muestra) {
        this.Muestra = Muestra;
    }

    public void setPatron(String Patron) {
        this.Patron = Patron;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public void setResultado(String Resultado) {
        this.Resultado = Resultado;
    }

    public void setRutaReporte(String rutaReporte) {
        this.rutaReporte = rutaReporte;
    }
    
    
    
    
}
