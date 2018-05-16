
package mx.inmobiliaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.inmobiliaria.database.DataBase;
import mx.inmobiliaria.domain.Casa;
import mx.inmobiliaria.domain.Departamento;
import mx.inmobiliaria.domain.Hogar;
import mx.inmobiliaria.domain.TipoAdquisicion;

public class DepartamentoDAO implements IDepartamentoDAO {
    
    private Connection conexionDB;
    private String consultaSQL;
    private PreparedStatement sentenciaSQL;

    @Override
    public boolean guardarDepartamento(Departamento departamento) {
        boolean guardadoRealizado = true;
        consultaSQL = "INSERT INTO departamento (Id_Cliente,Precio,Ubicacion,Habitaciones,Baños,Metros_Cuadrados,Piso_En_Edificio,Detalles_Extras,Tipo_Adquisicion) values (?,?,?,?,?,?,?,?,?)";
        conexionDB = DataBase.getDataBaseConnection();
        try{
            sentenciaSQL = conexionDB.prepareStatement(consultaSQL);
            sentenciaSQL.setInt(1, departamento.getIdDueño());
            sentenciaSQL.setFloat(2, departamento.getPrecio());
            sentenciaSQL.setString(3, departamento.getUbicacion());
            sentenciaSQL.setInt(4, departamento.getHabitaciones());
            sentenciaSQL.setInt(5, departamento.getBaños());
            sentenciaSQL.setInt(6, departamento.getMetrosCuadrados());
            sentenciaSQL.setInt(7, departamento.getPisoEnEdificio());
            sentenciaSQL.setString(8, departamento.getDetallesExtras());
            sentenciaSQL.setString(9, departamento.getTipoAdquisicion().name());
            sentenciaSQL.execute();
        } 
        catch (SQLException ex) {
            guardadoRealizado = false;
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DataBase.closeConnection();
        }
        return guardadoRealizado;
    }

    @Override
    public Departamento obtenerDepartamento(int idHogar, int idCliente) {
        Departamento departamento = null;
        consultaSQL = "SELECT * FROM departamento WHERE Id_Hogar=? and Id_Cliente=?";
        conexionDB = DataBase.getDataBaseConnection();
        try{
            sentenciaSQL = conexionDB.prepareStatement(consultaSQL);
            sentenciaSQL.setInt(1, 1);
            sentenciaSQL.setInt(2, 1);
            ResultSet resultadoConsulta = sentenciaSQL.executeQuery();
            while (resultadoConsulta.next()){
                float precio = resultadoConsulta.getFloat("Precio");
                String ubicacion = resultadoConsulta.getString("Ubicacion");
                int habitaciones = resultadoConsulta.getInt("Habitaciones");
                int baños = resultadoConsulta.getInt("Baños");
                int metrosCuadrados = resultadoConsulta.getInt("Metros_Cuadrados");
                int pisoEnEdificio = resultadoConsulta.getInt("Piso_En_Edificio");
                String detallesExtras = resultadoConsulta.getString("Detalles_Extras");
                TipoAdquisicion tipoAdquisicion = TipoAdquisicion.valueOf(resultadoConsulta.getString("Tipo_Adquisicion"));
                
                Hogar detallesGenerales = new Hogar(idCliente, precio, ubicacion, habitaciones, baños, metrosCuadrados, detallesExtras, tipoAdquisicion);
                departamento = new Departamento(detallesGenerales, pisoEnEdificio);                  
            }
        } 
        catch (SQLException ex) {
            departamento = null;
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DataBase.closeConnection();
        }
        return departamento;
    }
        
}
