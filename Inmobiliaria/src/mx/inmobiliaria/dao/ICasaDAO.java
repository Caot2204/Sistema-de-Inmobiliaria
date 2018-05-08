
package mx.inmobiliaria.dao;

import mx.inmobiliaria.domain.Casa;

public interface ICasaDAO {
    boolean guardarDatosCasa(Casa casa);
    Casa obtenerDatosCasa(int idHogar, int idCliente);
}
