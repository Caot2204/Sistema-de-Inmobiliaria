
package mx.inmobiliaria.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.inmobiliaria.database.DataBase;
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
        FileInputStream inputStreamA = null;
        FileInputStream inputStreamB = null;
        FileInputStream inputStreamC = null;
        FileInputStream inputStreamD = null;
        consultaSQL = "INSERT INTO departamento (Id_Cliente,Precio,Ubicacion,Habitaciones,Baños,Metros_Cuadrados,Piso_En_Edificio,Detalles_Extras,Tipo_Adquisicion,Imagen_A,Imagen_B,Imagen_C,Imagen_D) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            if (departamento.getImagenes()[0] != null) {
                inputStreamA = new FileInputStream(departamento.getImagenes()[0]);
                sentenciaSQL.setBinaryStream(10, inputStreamA, departamento.getImagenes()[0].length());
            }
            else {
                sentenciaSQL.setBinaryStream(10, null);
            }
            
            if (departamento.getImagenes()[1] != null) {
                inputStreamB = new FileInputStream(departamento.getImagenes()[1]);
                sentenciaSQL.setBinaryStream(11, inputStreamB, departamento.getImagenes()[1].length());
            }
            else {
                sentenciaSQL.setBinaryStream(11, null);
            }
            
            if (departamento.getImagenes()[2] != null) {
                inputStreamC = new FileInputStream(departamento.getImagenes()[2]);
                sentenciaSQL.setBinaryStream(12, inputStreamC, departamento.getImagenes()[2].length());
            }
            else {
                sentenciaSQL.setBinaryStream(12, null);
            }
            
            if (departamento.getImagenes()[3] != null) {
                inputStreamD = new FileInputStream(departamento.getImagenes()[3]);
                sentenciaSQL.setBinaryStream(13, inputStreamD, departamento.getImagenes()[3].length());
            }
            else {
                sentenciaSQL.setBinaryStream(13, null);
            }
            sentenciaSQL.execute();
        } 
        catch (SQLException ex) {
            guardadoRealizado = false;
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
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
                File[] imagenes = null;
                
                Hogar detallesGenerales = new Hogar(idCliente, precio, ubicacion, habitaciones, baños, metrosCuadrados, detallesExtras, tipoAdquisicion, imagenes);
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
