
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


public final class SuitePruebasAlmacenarInmuebles {
    
    private Departamento departamento;
    private final DepartamentoDAO departamentoDAO;
    
    private Casa casa;
    private final CasaDAO casaDAO;
    
    private Local local;
    private final LocalDAO localDAO;
    
    
    public SuitePruebasAlmacenarInmuebles(){
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
    public void almacenarCasa(){
        boolean valorEsperado = true;
        boolean valorObtenido = casaDAO.guardarDatosCasa(casa);
        
        assertEquals("Prueba de guardar datos de casa", valorEsperado, valorObtenido);
    }
    
    @Test
    public void almacenarDepartamento() {
        boolean valorEsperado = true;
        boolean valorObtenido = departamentoDAO.guardarDepartamento(departamento);
        
        assertEquals("Prueba de guardar datos de departamento", valorEsperado, valorObtenido);
    }
    
    @Test
    public void almacenarLocal(){
        boolean valorEsperado = true;
        boolean valorObtenido = localDAO.guardarLocal(local);
        
        assertEquals("Prueba de guardar datos de local", valorEsperado, valorObtenido);
    }
    
}
