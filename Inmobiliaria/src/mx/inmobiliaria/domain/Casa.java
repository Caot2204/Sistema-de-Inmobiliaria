
package mx.inmobiliaria.domain;

public class Casa extends Hogar {
    
    private int pisosCasa;
    private boolean patioServicio;
    private int metrosPatio;
    private boolean garaje;
    private int numeroAutos;
    
    public Casa(Hogar detallesGenerales, int pisosCasa, boolean patioServicio, int metrosPatio, boolean garaje, int numeroAutos) {
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
        this.metrosPatio = metrosPatio;
        this.garaje = garaje;
        this.numeroAutos = numeroAutos;
    }

    public int getPisosCasa() {
        return pisosCasa;
    }

    public boolean tienePatioServicio() {
        return patioServicio;
    }

    public int getMetrosPatio() {
        return metrosPatio;
    }

    public boolean tieneGaraje() {
        return garaje;
    }

    public int getNumeroAutos() {
        return numeroAutos;
    }

    public void setPisosCasa(int pisosCasa) {
        this.pisosCasa = pisosCasa;
    }

    public void setPatioServicio(boolean patioServicio) {
        this.patioServicio = patioServicio;
    }

    public void setMetrosPatio(int metrosPatio) {
        this.metrosPatio = metrosPatio;
    }

    public void setGaraje(boolean garaje) {
        this.garaje = garaje;
    }

    public void setNumeroAutos(int numeroAutos) {
        this.numeroAutos = numeroAutos;
    }
            
}
