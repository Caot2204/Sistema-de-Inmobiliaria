
package mx.inmobiliaria.domain;

import java.io.File;

public class Hogar {
    private int idDueño;
    private float precio;
    private String ubicacion;
    private int metrosCuadrados;
    private int habitaciones;
    private int baños;
    private String detallesExtras;
    private TipoAdquisicion tipoAdquisicion;
    private File[] imagenes;

    public Hogar(int idDueño, float precio, String ubicacion, int habitaciones, int baños, int metrosCuadrados, String detallesExtras, TipoAdquisicion tipoAdquisicion, File[] imagenes) {
        this.idDueño = idDueño;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.metrosCuadrados = metrosCuadrados;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.detallesExtras = detallesExtras;
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

    public int getHabitaciones() {
        return habitaciones;
    }

    public int getBaños() {
        return baños;
    }

    public String getDetallesExtras() {
        return detallesExtras;
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

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setBaños(int baños) {
        this.baños = baños;
    }

    public void setDetallesExtras(String detallesExtras) {
        this.detallesExtras = detallesExtras;
    }

    public void setTipoAdquisicion(TipoAdquisicion tipoAdquisicion) {
        this.tipoAdquisicion = tipoAdquisicion;
    }

    public void setImagenes(File[] imagenes) {
        this.imagenes = imagenes;
    }
    
}
