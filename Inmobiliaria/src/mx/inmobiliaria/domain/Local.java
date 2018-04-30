
package mx.inmobiliaria.domain;

public class Local {
    
    private String dueño;
    private float precio;
    private String ubicacion;
    private int metrosCuadrados;
    private String detallesExtras;
    private TipoLocal tipoDeLocal;

    public Local(String dueño, float precio, String ubicacion, int metrosCuadrados, String detallesExtras, TipoLocal tipoDeLocal) {
        this.dueño = dueño;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.metrosCuadrados = metrosCuadrados;
        this.detallesExtras = detallesExtras;
        this.tipoDeLocal = tipoDeLocal;
    }

    public String getDueño() {
        return dueño;
    }

    public float getPrecio() {
        return precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public String getDetallesExtras() {
        return detallesExtras;
    }

    public TipoLocal getTipoDeLocal() {
        return tipoDeLocal;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setMetrosCuadrados(int metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }

    public void setDetallesExtras(String detallesExtras) {
        this.detallesExtras = detallesExtras;
    }

    public void setTipoDeLocal(TipoLocal tipoDeLocal) {
        this.tipoDeLocal = tipoDeLocal;
    }
        
}
