
package mx.inmobiliaria.dao;

import mx.inmobiliaria.domain.Departamento;

public interface IDepartamentoDAO {
    public boolean guardarDepartamento(Departamento departamento);
    Departamento obtenerDepartamento(int idHogar, int idCliente); 
}
