
package mx.inmobiliaria.domain;


public class MetodoDePago {
    
    String tipoTarjeta;
    long numeroTarjeta;
    
    public MetodoDePago(String tipoTarjeta, long numeroTarjeta){
        this.tipoTarjeta = tipoTarjeta;
        this.numeroTarjeta = numeroTarjeta;
    }

    public MetodoDePago() {
        
    }
    
    public String getTipoTarjeta(){
        return tipoTarjeta;
    }
    
    public long getNumeroTarjeta(){
        return numeroTarjeta;
    }
    
    public void setTipoTarjeta(String tipoTarjeta){
        this.tipoTarjeta = tipoTarjeta;
    }
    
    public void setTipoNumero(long numeroTarjeta){
        this.numeroTarjeta = numeroTarjeta;
    }
}
