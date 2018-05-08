
package mx.inmobiliaria.dao;

import mx.inmobiliaria.domain.Local;

public interface ILocalDAO {
    boolean guardarLocal(Local local);
    Local obtenerDatosLocal(int idHogar, int idCliente);
}
