
package mx.inmobiliaria.dao;

import java.io.File;
import mx.inmobiliaria.domain.Casa;
import mx.inmobiliaria.domain.Departamento;
import mx.inmobiliaria.domain.Hogar;
import mx.inmobiliaria.domain.Local;
import mx.inmobiliaria.domain.TipoAdquisicion;
import mx.inmobiliaria.domain.TipoLocal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public final class SuitePruebasRecuperarInmuebles {
    
    private Departamento departamento;
    private final DepartamentoDAO departamentoDAO;
    
    private Casa casa;
    private final CasaDAO casaDAO;
    
    private Local local;
    private final LocalDAO localDAO;
    
    
    public SuitePruebasRecuperarInmuebles(){
        inicializarCasa();
        inicializarDepartamento();
        inicializarLocal();
        
        casaDAO = new CasaDAO();
        departamentoDAO = new DepartamentoDAO();
        localDAO = new LocalDAO();
    }
    
    public void inicializarDepartamento(){
        int idDueño = 1;
        float precio = 1500;
        String ubicacion = "Revolución, Xalapa Enriquez, Ver.";
        int habitaciones = 2;
        int baños = 1;
        int metrosCuadrados = 80;
        int pisoEnEdificio = 5;
        String detallesExtras = "Bella vista a los lagos.";
        TipoAdquisicion tipoAdquisicion = TipoAdquisicion.renta;
        File[] imagenes = new File[4];
                
        Hogar detallesGenerales = new Hogar(idDueño, precio, ubicacion, habitaciones, baños, metrosCuadrados, detallesExtras, tipoAdquisicion, imagenes);
        departamento = new Departamento(detallesGenerales, pisoEnEdificio);
    }
    
    public void inicializarCasa(){
        int idDueño = 1;
        float precio = 2000000;
        String ubicacion = "Revolución, Xalapa Enriquez, Ver.";
        int habitaciones = 3;
        int baños = 2;
        int metrosCuadrados = 450;
        int pisosDeCasa = 2;
        boolean garaje = false;
        int numeroAutos = 0;
        boolean patioServicio = true;
        int metrosPatio = 0;
        String detallesExtras = "Patio de servicio de 30 m2.";
        TipoAdquisicion tipoAdquisicion = TipoAdquisicion.venta;
        File[] imagenes = new File[4];
                
        Hogar detallesGenerales = new Hogar(idDueño, precio, ubicacion, habitaciones, baños, metrosCuadrados, detallesExtras, tipoAdquisicion, imagenes);
        casa = new Casa(detallesGenerales, pisosDeCasa, patioServicio, metrosPatio, garaje, numeroAutos);
    }
    
    public void inicializarLocal(){
        int idDueño = 1;
        float precio = 2000000;
        String ubicacion = "Revolución, Xalapa Enriquez, Ver.";
        int metrosCuadrados = 450;
        String detallesExtras = "Oficina con un baño.";
        TipoLocal tipoInmueble = TipoLocal.oficina;
        TipoAdquisicion tipoAdquisicion = TipoAdquisicion.venta;
        File[] imagenes = new File[4];
        
        local = new Local(idDueño, precio, ubicacion, metrosCuadrados, detallesExtras, tipoInmueble, tipoAdquisicion, imagenes);        
    }
    
    @Test
    public void recuperarCasa(){        
        Casa casaObtenida = casaDAO.obtenerDatosCasa(1, 1);
        
        assertEquals("Prueba de precio", this.casa.getPrecio(), casaObtenida.getPrecio(),0);
        assertEquals("Prueba de ubicacion", this.casa.getUbicacion(), casaObtenida.getUbicacion());
        assertEquals("Prueba de habitaciones", this.casa.getHabitaciones(), casaObtenida.getHabitaciones());
        assertEquals("Prueba de baños", this.casa.getBaños(), casaObtenida.getBaños());
        assertEquals("Prueba de metros cuadrados", this.casa.getMetrosCuadrados(), casaObtenida.getMetrosCuadrados());
        assertEquals("Prueba de pisos de casa", this.casa.getPisosCasa(), casaObtenida.getPisosCasa());
        assertEquals("Prueba de garaje", this.casa.tieneGaraje(), casaObtenida.tieneGaraje());
        assertEquals("Prueba de autos", this.casa.getNumeroAutos(), casaObtenida.getNumeroAutos());
        assertEquals("Prueba de patioServicio", this.casa.tienePatioServicio(), casaObtenida.tienePatioServicio());
        assertEquals("Prueba de metros patio", this.casa.getMetrosPatio(), casaObtenida.getMetrosPatio());
        assertEquals("Prueba de detalles extras", this.casa.getDetallesExtras(), casaObtenida.getDetallesExtras());
        assertEquals("Prueba de tipo adquisicion", this.casa.getTipoAdquisicion(), casaObtenida.getTipoAdquisicion());        
    }
    
    @Test
    public void recuperarDepartamento(){        
        Departamento departamentoObtenido = departamentoDAO.obtenerDepartamento(1, 1);
        
        assertEquals("Prueba de precio", this.departamento.getPrecio(), departamentoObtenido.getPrecio(),0);
        assertEquals("Prueba de ubicacion", this.departamento.getUbicacion(), departamentoObtenido.getUbicacion());
        assertEquals("Prueba de habitaciones", this.departamento.getHabitaciones(), departamentoObtenido.getHabitaciones());
        assertEquals("Prueba de baños", this.departamento.getBaños(), departamentoObtenido.getBaños());
        assertEquals("Prueba de metros cuadrados", this.departamento.getMetrosCuadrados(), departamentoObtenido.getMetrosCuadrados());
        assertEquals("Prueba de pisos de casa", this.departamento.getPisoEnEdificio(), departamentoObtenido.getPisoEnEdificio());
        assertEquals("Prueba de detalles extras", this.departamento.getDetallesExtras(), departamentoObtenido.getDetallesExtras());
        assertEquals("Prueba de tipo adquisicion", this.departamento.getTipoAdquisicion(), departamentoObtenido.getTipoAdquisicion());        
    }
    
    @Test
    public void recuperarLocal(){        
        Local localObtenido = localDAO.obtenerDatosLocal(1, 1);
        
        assertEquals("Prueba de precio", this.local.getPrecio(), localObtenido.getPrecio(),0);
        assertEquals("Prueba de ubicacion", this.local.getUbicacion(), localObtenido.getUbicacion());
        assertEquals("Prueba de metros cuadrados", this.local.getMetrosCuadrados(), localObtenido.getMetrosCuadrados());
        assertEquals("Prueba de detalles extras", this.local.getDetallesExtras(), localObtenido.getDetallesExtras());
        assertEquals("Prueba de tipo local", this.local.getTipoDeLocal(), localObtenido.getTipoDeLocal());        
        assertEquals("Prueba de tipo adquisicion", this.local.getTipoAdquisicion(), localObtenido.getTipoAdquisicion());        
    }
    
}
