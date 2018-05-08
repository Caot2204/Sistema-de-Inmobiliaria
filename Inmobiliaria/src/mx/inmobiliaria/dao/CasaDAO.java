
package mx.inmobiliaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.inmobiliaria.database.DataBase;
import mx.inmobiliaria.domain.Casa;
import mx.inmobiliaria.domain.Hogar;
import mx.inmobiliaria.domain.TipoAdquisicion;

public class CasaDAO implements ICasaDAO{
    
    private Connection conexionDB;
    private String consultaSQL;
    private PreparedStatement sentenciaSQL;

    @Override
    public boolean guardarDatosCasa(Casa casa) {
        boolean guardadoRealizado = true;
        consultaSQL = "INSERT INTO casa (Id_Cliente,Precio,Ubicacion,Habitaciones,Baños,Metros_Cuadrados,Pisos_Casa,Garaje,Patio_Servicio,Detalles_Extras,Tipo_Adquisicion) values (?,?,?,?,?,?,?,?,?,?,?)";
        conexionDB = DataBase.getDataBaseConnection();
        try{
            sentenciaSQL = conexionDB.prepareStatement(consultaSQL);
            sentenciaSQL.setInt(1, casa.getIdDueño());
            sentenciaSQL.setDouble(2, casa.getPrecio());
            sentenciaSQL.setString(3, casa.getUbicacion());
            sentenciaSQL.setInt(4, casa.getHabitaciones());
            sentenciaSQL.setInt(5, casa.getBaños());
            sentenciaSQL.setInt(6, casa.getMetrosCuadrados());
            sentenciaSQL.setInt(7, casa.getPisosCasa());
            sentenciaSQL.setBoolean(8, casa.tieneGaraje());
            sentenciaSQL.setBoolean(9, casa.tienePatioServicio());
            sentenciaSQL.setString(10, casa.getDetallesExtras());
            sentenciaSQL.setString(11, casa.getTipoAdquisicion().name());
            sentenciaSQL.execute();
        } 
        catch (SQLException ex) {
            guardadoRealizado = false;
            Logger.getLogger(CasaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DataBase.closeConnection();
        }
        return guardadoRealizado;
    }

    @Override
    public Casa obtenerDatosCasa(int idHogar, int idCliente) {
        Casa casa = null;
        consultaSQL = "SELECT * FROM casa where Id_Hogar=? and Id_Cliente=?";
        conexionDB = DataBase.getDataBaseConnection();
        try {
            sentenciaSQL = conexionDB.prepareStatement(consultaSQL);
            sentenciaSQL.setInt(1, idHogar);
            sentenciaSQL.setInt(2, idCliente);
            ResultSet resultadoConsulta = sentenciaSQL.executeQuery();
            while (resultadoConsulta.next()){
                float precio = resultadoConsulta.getFloat("Precio");
                String ubicacion = resultadoConsulta.getString("Ubicacion");
                int habitaciones = resultadoConsulta.getInt("Habitaciones");
                int baños = resultadoConsulta.getInt("Baños");
                int metrosCuadrados = resultadoConsulta.getInt("Metros_Cuadrados");
                int pisosDeCasa = resultadoConsulta.getInt("Pisos_Casa");
                boolean garaje = resultadoConsulta.getBoolean("Garaje");
                boolean patioServicio = resultadoConsulta.getBoolean("Patio_Servicio");
                String detallesExtras = resultadoConsulta.getString("Detalles_Extras");
                TipoAdquisicion tipoAdquisicion = TipoAdquisicion.valueOf(resultadoConsulta.getString("Tipo_Adquisicion"));
                
                Hogar detallesGenerales = new Hogar(idCliente, precio, ubicacion, habitaciones, baños, metrosCuadrados, detallesExtras, tipoAdquisicion);
                casa = new Casa(detallesGenerales, pisosDeCasa, garaje, patioServicio);
            }
        } 
        catch (SQLException ex) {
            casa = null;
            Logger.getLogger(CasaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DataBase.closeConnection();
        }
        return casa;
    }
    
}
