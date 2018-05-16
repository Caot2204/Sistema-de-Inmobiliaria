
package mx.inmobiliaria.domain;

import java.io.File;

public class Local {
    
    private int idDueño;
    private float precio;
    private String ubicacion;
    private int metrosCuadrados;
    private String detallesExtras;
    private TipoLocal tipoDeLocal;
    private TipoAdquisicion tipoAdquisicion;
    private File[] imagenes;

    public Local(int idDueño, float precio, String ubicacion, int metrosCuadrados, String detallesExtras, TipoLocal tipoDeLocal, TipoAdquisicion tipoAdquisicion, File[] imagenes) {
        this.idDueño = idDueño;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.metrosCuadrados = metrosCuadrados;
        this.detallesExtras = detallesExtras;
        this.tipoDeLocal = tipoDeLocal;
        this.tipoAdquisicion = tipoAdquisicion;
        this.imagenes = imagenes;
    }

    public int getIdDueño() {
        return idDueño;
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

    public TipoAdquisicion getTipoAdquisicion() {
        return tipoAdquisicion;
    }

    public File[] getImagenes() {
        return imagenes;
    }

    public void setIdDueño(int idDueño) {
        this.idDueño = idDueño;
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

    public void setTipoAdquisicion(TipoAdquisicion tipoAdquisicion) {
        this.tipoAdquisicion = tipoAdquisicion;
    }

    public void setImagenes(File[] imagenes) {
        this.imagenes = imagenes;
    }
        
}
