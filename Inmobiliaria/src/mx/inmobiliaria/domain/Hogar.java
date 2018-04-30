
package mx.inmobiliaria.domain;

public class Hogar {
    private String dueño;
    private float precio;
    private String ubicacion;
    private int metrosCuadrados;
    private int habitaciones;
    private int baños;
    private String detallesExtras;

    public Hogar(String dueño, float precio, String ubicacion, int metrosCuadrados, int habitaciones, int baños, String detallesExtras) {
        this.dueño = dueño;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.metrosCuadrados = metrosCuadrados;
        this.habitaciones = habitaciones;
        this.baños = baños;
        this.detallesExtras = detallesExtras;
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

    public int getHabitaciones() {
        return habitaciones;
    }

    public int getBaños() {
        return baños;
    }

    public String getDetallesExtras() {
        return detallesExtras;
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

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setBaños(int baños) {
        this.baños = baños;
    }

    public void setDetallesExtras(String detallesExtras) {
        this.detallesExtras = detallesExtras;
    }
    
}
