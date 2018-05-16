
package mx.inmobiliaria.domain;

public class Departamento extends Hogar {
    
    private int pisoEnEdificio;
    
    public Departamento(Hogar detallesGenerales, int pisoEnEdificio) {
        super(detallesGenerales.getIdDueño(),
              detallesGenerales.getPrecio(),
              detallesGenerales.getUbicacion(),
              detallesGenerales.getHabitaciones(),
              detallesGenerales.getBaños(),
              detallesGenerales.getMetrosCuadrados(),
              detallesGenerales.getDetallesExtras(),
              detallesGenerales.getTipoAdquisicion(),
              detallesGenerales.getImagenes());
        this.pisoEnEdificio = pisoEnEdificio;        
    }

    public int getPisoEnEdificio() {
        return pisoEnEdificio;
    }

    public void setPisoEnEdificio(int pisoEnEdificio) {
        this.pisoEnEdificio = pisoEnEdificio;
    }
      
}
