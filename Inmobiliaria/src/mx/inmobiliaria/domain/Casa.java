
package mx.inmobiliaria.domain;

public class Casa extends Hogar {
    
    private int pisosCasa;
    private boolean patioServicio;
    private boolean garaje;
    
    public Casa(Hogar detallesGenerales, int pisosCasa, boolean patioServicio, boolean garaje) {
        super(detallesGenerales.getIdDueño(),
              detallesGenerales.getPrecio(),
              detallesGenerales.getUbicacion(),
              detallesGenerales.getHabitaciones(),
              detallesGenerales.getBaños(),
              detallesGenerales.getMetrosCuadrados(),
              detallesGenerales.getDetallesExtras(),
              detallesGenerales.getTipoAdquisicion());
        this.pisosCasa = pisosCasa;
        this.patioServicio = patioServicio;
        this.garaje = garaje;
    }

    public int getPisosCasa() {
        return pisosCasa;
    }

    public boolean tienePatioServicio() {
        return patioServicio;
    }

    public boolean tieneGaraje() {
        return garaje;
    }

    public void setPisosCasa(int pisosCasa) {
        this.pisosCasa = pisosCasa;
    }

    public void setPatioServicio(boolean patioServicio) {
        this.patioServicio = patioServicio;
    }

    public void setGaraje(boolean garaje) {
        this.garaje = garaje;
    }
            
}
